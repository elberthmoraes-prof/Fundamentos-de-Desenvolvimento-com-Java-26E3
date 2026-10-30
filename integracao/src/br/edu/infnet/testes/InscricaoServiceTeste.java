package br.edu.infnet.testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Evento;
import br.edu.infnet.service.InscricaoService;

@DisplayName("Testes do serviço de inscrições")
public class InscricaoServiceTeste {

	@Test
	@DisplayName("Deve aceitar inscrição em evento com vagas")
	void deveAceitarInscricaoEmEventoComVagas(){
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		InscricaoService service = new InscricaoService(null);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento);
		int qtdeInscritos = evento.getQtdeInscritos();
		
		// ASSERT
		assertAll(
			() -> assertTrue(resultado),
			() -> assertEquals(1, qtdeInscritos)
		);
		
	}
}
