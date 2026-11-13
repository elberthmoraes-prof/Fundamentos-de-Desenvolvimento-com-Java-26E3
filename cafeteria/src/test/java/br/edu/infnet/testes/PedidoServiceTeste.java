package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;
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
}
