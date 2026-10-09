package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Evento;

public class EventoTeste {

	@Test
	@DisplayName("ET-001 - Deve possuir vagas ao criar um evento")
	void devePossuirVagasQuandoNaoHaInscritos() {
		//SETUP: prepara o cenário
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		//EXECUÇÃO: executa o comportamento
		boolean possuiVaga = evento.temVaga();
		
		//ASSERTION: verifica o resultado esperado
		assertTrue(possuiVaga, "Um evento vazio deve possuir vagas disponíveis.");
	}
	
	@Test
	@DisplayName("ET-002 - Evento lotado não deve possuir vagas")
	void naoDevePossuirVagasQuandoEventoEstaLotado() {
		
		//SETUP: prepara o cenário
		Evento evento = new Evento("Java Day", "Infnet", 2);
		evento.inscrever();
		evento.inscrever();
		
		//EXECUÇÃO: executa o comportamento
		boolean possuiVaga = evento.temVaga();
		
		//ASSERTION: verifica o resultado esperado
		assertFalse(possuiVaga, "Um evento não deve possuir vagas disponíveis.");
	}

	@Test
	@DisplayName("ET-003 - Evento com capacidade zero não possui vagas")
	void naoDevePossuirVagasComCapacidadeZero() {
		
		//SETUP: prepara o cenário
		Evento evento = new Evento("Java Day", "Infnet", 0);
		
		//EXECUÇÃO: executa o comportamento
		boolean possuiVaga = evento.temVaga();
		
		//ASSERTION: verifica o resultado esperado
		assertFalse(possuiVaga, "Um evento com capacidade zero não deve possuir vagas disponíveis.");
	}
}