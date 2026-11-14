package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Pedido;

public class PedidoTeste {
	
	private Pedido criarPedido(double valor) {
		return new Pedido(valor);
	}
	
	private void verificarValor(double esperado, double atual) {
		assertEquals(esperado, atual, 0.01);		
	}

	@Test
	@DisplayName("ET-C01 — Cálculo básico")
	void deveCalcularDescontoDezPorCento() {
		
		//ARRANGE
		Pedido pedido = criarPedido(80.0);
		
		//ACT
		double valorFinal = pedido.calcularValorFinal(10.0);
		
		//ASSERT
		verificarValor(72.0, valorFinal);
	}
	
	@Test
	@DisplayName("ET-C02A — Casos de borda: desconto 0%")
	void deveManterValorSemDesconto() {
		
		//ARRANGE
		Pedido pedido = criarPedido(80.0);
		
		//ACT
		double valorFinal = pedido.calcularValorFinal(0.0);
		
		//ASSERT
		verificarValor(80.0, valorFinal);
	}
	
	@Test
	@DisplayName("ET-C02B — Casos de borda: desconto 100%")
	void deveZerarValorComDescontoTotal() {
		
		//ARRANGE
		Pedido pedido = criarPedido(80.0);
		
		//ACT
		double valorFinal = pedido.calcularValorFinal(100.0);
		
		//ASSERT
		verificarValor(0.0, valorFinal);
	}
	
	@Test
	@DisplayName("ET-C02C — Casos de borda: rejeitar valor negativo")
	void deveRejeitarPedidoComValorNegativo() {
		
		assertThrows(IllegalArgumentException.class, () -> criarPedido(-10.0));
	}

	@Test
	@DisplayName("ET-C02D — Casos de borda: rejeitar desconto acima de 100%")
	void deveRejeitarDescontoAcimaCemPorCento() {
	
		assertThrows(IllegalArgumentException.class, () -> criarPedido(80.0).calcularValorFinal(120.0));
	}

	@Test
	@DisplayName("ET-C02E — Casos de borda: rejeitar desconto negativo")
	void deveRejeitarDescontoNegativo() {
	
		assertThrows(IllegalArgumentException.class, () -> criarPedido(80.0).calcularValorFinal(-10.0));
	}
	
	//ET-C09 — Helper decimal: utilizar um método auxiliar para comparar resultados com tolerância de 0,01.
	@Test
	@DisplayName("ET-C09 — Helper decimal: comparação com tolerância")
	void deveCompararValoresComTolerancia() {
		
		//ARRANGE
		double esperado = 72.0;
		double atual = 72.004;
		
		//ASSERT
		verificarValor(esperado, atual);
	}

}