package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.AutorizadorPedido;
import br.edu.infnet.service.HistoricoPedidos;
import br.edu.infnet.service.PedidoService;

public class PedidoServiceTeste {

	@Test
	@DisplayName("ET-C03 — Dummy")
	void deveConcluirPedidoClienteDummy() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		HistoricoPedidos historico = p -> { };
		
		PedidoService service = new PedidoService(historico);
		
		//ACT
		double valorFinal = service.concluirPedido(pedido, cliente, 10.0);
		
		//ASSERT
		assertEquals(72.0,  valorFinal, 0.01);
	}
	
	@Test
	@DisplayName("ET-C05A — Stub: aplicar desconto stub de 10%")
	void deveAplicarDescontoDezPorCento() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(10);
		
		PedidoService service = new PedidoService(historico, politica);
		
		//ACT
		double valorFinal = service.concluirPedido(pedido, cliente);
		int qtde = historico.obterQuantidade();
		
		//ASSERT
		assertAll(
			() -> assertEquals(72.0,  valorFinal, 0.01),
			
			() -> assertEquals(1, qtde)
		);
		
	}

	@Test
	@DisplayName("ET-C05B — Stub: aplicar desconto stub de 25%")
	void deveAplicarDescontoVinteCincoPorCento() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(25);
		
		PedidoService service = new PedidoService(historico, politica);
		
		//ACT
		double valorFinal = service.concluirPedido(pedido, cliente);
		int qtde = historico.obterQuantidade();
		
		//ASSERT
		assertAll(
			() -> assertEquals(60.0,  valorFinal, 0.01),
			
			() -> assertEquals(1, qtde)
		);
	}

	@Test
	@DisplayName("ET-C06A — Spy: verificar a chamada de auditoria")
	void deveAuditarPedidoConcluido() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(10);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		PedidoService service = new PedidoService(historico, politica, auditoria);
		
		//ACT
		double valorFinal = service.concluirPedido(pedido, cliente);
		int qtde = historico.obterQuantidade();
		int qtdeChamados = auditoria.getQtdeChamados();
		
		//ASSERT
		assertAll(
			() -> assertEquals(72.0,  valorFinal, 0.01),
			
			() -> assertEquals(1, qtde),
			
			() -> assertEquals(1, qtdeChamados)
		);
	}
	
	@Test
	@DisplayName("ET-C06B — Spy: não auditar quando o desconto inválido")
	void naoDeveAuditarDescontoInvalido() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(120);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		PedidoService service = new PedidoService(historico, politica, auditoria);
		
		//ACT
		assertThrows(IllegalArgumentException.class, () -> service.concluirPedido(pedido, cliente));

		int qtde = historico.obterQuantidade();
		int qtdeChamados = auditoria.getQtdeChamados();
		
		//ASSERT
		assertAll(
			() -> assertEquals(0, qtde),
			
			() -> assertEquals(0, qtdeChamados)
		);
	}

	@Test
	@DisplayName("ET-C06C — Spy: verificar os argumentos")
	void deveAuditarObjetosCorretos() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(10);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		PedidoService service = new PedidoService(historico, politica, auditoria);
		
		//ACT
		service.concluirPedido(pedido, cliente);

		int qtdeChamados = auditoria.getQtdeChamados();
		
		//ASSERT
		assertAll(		
			() -> assertEquals(1, qtdeChamados),
			
			() -> assertSame(pedido, auditoria.getUltimoPedido()),
			
			() -> assertSame(cliente, auditoria.getUltimoCliente())
		);
	}

	@Test
	@DisplayName("ET-C07A — Mock: autorização concedida")
	void deveConcluirPedidoAutorizado() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(10);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		AutorizadorPedido autorizador = mock(AutorizadorPedido.class);
		when(autorizador.autorizar(pedido, cliente)).thenReturn(true);
		
		PedidoService service = new PedidoService(historico, politica, auditoria, autorizador);
		
		//ACT
		double valorFinal = service.concluirPedido(pedido, cliente);
		int qtde = historico.obterQuantidade();
		int qtdeChamados = auditoria.getQtdeChamados();
		
		//ASSERT
		assertAll(		
			() -> assertEquals(72.0, valorFinal, 0.01),	
			
			() -> assertEquals(1, qtde),
			
			() -> assertEquals(1, qtdeChamados)
		);
	}
	
	@Test
	@DisplayName("ET-C07B — Mock: autorização negada")
	void deveRecusarPedidoNaoAutorizado() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(10);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		AutorizadorPedido autorizador = mock(AutorizadorPedido.class);
		when(autorizador.autorizar(pedido, cliente)).thenReturn(false);
		
		PedidoService service = new PedidoService(historico, politica, auditoria, autorizador);
		
		//ACT
		
		SecurityException exception = 
				assertThrows(SecurityException.class, () -> service.concluirPedido(pedido, cliente));
		
		int qtde = historico.obterQuantidade();
		int qtdeChamados = auditoria.getQtdeChamados();
		
		//ASSERT
		assertAll(		
			() -> assertEquals("Pedido não está autorizado.", exception.getMessage()),
			
			() -> assertEquals(0, qtde),
			
			() -> assertEquals(0, qtdeChamados)
		);
		
		verify(autorizador, times(1)).autorizar(pedido, cliente);
	}

	@Test
	@DisplayName("ET-C07C — Mock: não autorizar desconto inválido")
	void naoDeveAutorizarDescontoInvalido() {

		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(120);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		AutorizadorPedido autorizador = mock(AutorizadorPedido.class);
		
		PedidoService service = new PedidoService(historico, politica, auditoria, autorizador);
		
		//ACT
		assertThrows(IllegalArgumentException.class, () -> service.concluirPedido(pedido, cliente));
		
		int qtde = historico.obterQuantidade();
		int qtdeChamados = auditoria.getQtdeChamados();
		
		//ASSERT
		assertAll(					
			() -> assertEquals(0, qtde),
			
			() -> assertEquals(0, qtdeChamados)
		);
		
		verify(autorizador, never()).autorizar(pedido, cliente);
	}

	//ET-C10 — TDD: desenvolver testes para o teto de R$ 30,00 e implementar a regra seguindo Red, Green e Refactor.
	@Test
	@DisplayName("ET-C10 — TDD: aplicar teto de 30 reais")
	void deveLimitarDescontoAtrintaReais() {

		//ARRANGE
		Pedido pedido = new Pedido(100.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		StubPoliticaDesconto politica = new StubPoliticaDesconto(50);
		
		SpyAuditoriaPedidos auditoria = new SpyAuditoriaPedidos();
		
		AutorizadorPedido autorizador = mock(AutorizadorPedido.class);
		when(autorizador.autorizar(pedido, cliente)).thenReturn(true);
		
		PedidoService service = new PedidoService(historico, politica, auditoria, autorizador);
		
		//ACT
		double valorFinal = service.concluirPedidoComTeto(pedido, cliente);
		
		//ASSERT
		//TODO implementar um método para definição de desconto envolvendo o pedido
		assertEquals(50, valorFinal, 0.01);
	}

}
