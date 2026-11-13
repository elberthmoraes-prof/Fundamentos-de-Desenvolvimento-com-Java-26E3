package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Pedido;

public class PedidoTeste {

	@Test
	@DisplayName("ET-C01 — Cálculo básico")
	void deveCalcularDescontoDezPorCento() {
		
		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		//ACT
		double valorFinal = pedido.calcularValorFinal(10.0);
		
		//ASSERT
		assertEquals(72.0, valorFinal, 0.01);
	}
	
	@Test
	@DisplayName("ET-C02A — Casos de borda: desconto 0%")
	void deveManterValorSemDesconto() {
		
		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		//ACT
		double valorFinal = pedido.calcularValorFinal(0.0);
		
		//ASSERT
		assertEquals(80.0, valorFinal, 0.01);
	}
	
	@Test
	@DisplayName("ET-C02B — Casos de borda: desconto 100%")
	void deveZerarValorComDescontoTotal() {
		
		//ARRANGE
		Pedido pedido = new Pedido(80.0);
		
		//ACT
		double valorFinal = pedido.calcularValorFinal(100.0);
		
		//ASSERT
		assertEquals(0.0, valorFinal, 0.01);
	}
	
	@Test
	@DisplayName("ET-C02C — Casos de borda: rejeitar valor negativo")
	void deveRejeitarPedidoComValorNegativo() {
		
		assertThrows(IllegalArgumentException.class, () -> new Pedido(-10.0));
	}

	@Test
	@DisplayName("ET-C02D — Casos de borda: rejeitar desconto acima de 100%")
	void deveRejeitarDescontoAcimaCemPorCento() {
	
		assertThrows(IllegalArgumentException.class, () -> new Pedido(80.0).calcularValorFinal(120.0));
	}

	@Test
	@DisplayName("ET-C02E — Casos de borda: rejeitar desconto negativo")
	void deveRejeitarDescontoNegativo() {
	
		assertThrows(IllegalArgumentException.class, () -> new Pedido(80.0).calcularValorFinal(-10.0));
	}
}
