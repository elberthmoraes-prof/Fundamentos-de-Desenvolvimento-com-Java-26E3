package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.PedidoService;

public class FakeHistoricoPedidosTeste {

	@Test
	@DisplayName("ET-C04A — Fake: histórico inicia vazio")
	void deveIniciarHistoricoVazio() {
	
		//ARRANGE
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();
		
		//ACT
		int qtde = historico.obterQuantidade();
		
		//ASSERT
		assertEquals(0, qtde);
	}

	@Test
	@DisplayName("ET-C04B — Fake: deve armazenar um pedido")
	void deveArmazenarPedido() {
	
		//ARRANGE
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();

		Pedido pedido = new Pedido(80.0);
		
		//ACT
		historico.registrar(pedido);
		int qtde = historico.obterQuantidade();
		
		//ASSERT
		assertAll(
			() -> assertEquals(1, qtde),
			
			() -> assertSame(pedido, historico.consultarPedidos().get(0))
		);
	}
	
	@Test
	@DisplayName("ET-C04C — Fake: registrar pedido concluído")
	void deveRegistrarPedidoConcluido() {
	
		//ARRANGE
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();

		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		PedidoService service = new PedidoService(historico);
		
		//ACT
		double valorFinal = service.concluirPedido(pedido, cliente, 10);
		int qtde = historico.obterQuantidade();
		
		//ASSERT
		assertAll(
			() -> assertEquals(1, qtde),
			
			() -> assertEquals(72.0, valorFinal, 0.01),
			
			() -> assertSame(pedido, historico.consultarPedidos().get(0))
		);
	}

	//D - Não registra desconto inválido
	@Test
	@DisplayName("ET-C04D — Fake: não registrar desconto inválido")
	void naoDeveRegistrarPedidoComDescontoInvalido() {
	
		//ARRANGE
		FakeHistoricoPedidos historico = new FakeHistoricoPedidos();

		Pedido pedido = new Pedido(80.0);
		
		Cliente cliente = new Cliente("Elberth");
		
		PedidoService service = new PedidoService(historico);
		
		//ACT
		assertThrows(IllegalArgumentException.class, () -> service.concluirPedido(pedido, cliente, 120));

		int qtde = historico.obterQuantidade();
		
		//ASSERT
		assertAll(
			() -> assertEquals(0, qtde)
		);
	}
}
