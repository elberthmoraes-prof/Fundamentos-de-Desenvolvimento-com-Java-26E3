package br.edu.infnet.integracao_api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.integracao_api.model.FakeHistoricoInscricoes;

@DisplayName("Testes do histórico Fake")
public class FakeHistoricoInscricoesTeste {

	@Test
	@DisplayName("ET-19A - Histórico deve iniciar vazio")
	void deveIniciarHistoricoVazio() {

		// ARRANGE
		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		// ACT
		int quantidade = historico.obterQuantidade();
		
		// ASSERT
		assertEquals(0, quantidade);
	}

	@Test
	@DisplayName("ET-19B - Deve armazenar um registro")
	void deveArmazenarRegistro() {

		// ARRANGE
		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		String nomeRegistro = "Inscrição no evento Java Day";
		
		// ACT
		historico.registrar(nomeRegistro);
		int quantidade = historico.obterQuantidade();
		List<String> registros = historico.consultarRegistros();
		
		// ASSERT
		assertAll(
			() -> assertEquals(1, quantidade),
			
			() -> assertEquals(nomeRegistro, registros.get(0))
		);
	}
	
}
