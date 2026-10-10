package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Evento;

public class EventoTeste {

	@Test
	@DisplayName("ET-001 - Deve possuir vagas ao criar um evento")
	void devePossuirVagasQuandoNaoHaInscritos() {
		//A = arrange :: SETUP: prepara o cenário
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		//A = act :: EXECUÇÃO: executa o comportamento
		boolean possuiVaga = evento.temVaga();
		
		//A = assert :: ASSERTION: verifica o resultado esperado
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
	@DisplayName("ET-003 - Não deve permitir capacidade zerada")
	void deveRejeitarCapacidadeZerada() {
		
		//EXECUÇÃO + ASSERTION
		assertThrows(
				IllegalArgumentException.class, 
				() -> new Evento("Java Day", "Infnet", 0), 
				"Uma capacidade negativa deve ser rejeitada"
			);
	}

	@Test
	@DisplayName("ET-004 - Uma inscrição deve aumentar a quantidade de participantes")
	void devIncrementarQuantidadeDeInscritos() {
		
		//SETUP
		Evento evento = new Evento("Java Day", "Infnet", 10);
		int qtdeEsperada = 1;
		
		//EXECUÇÃO
		evento.inscrever();
		int qtdeInscritos = evento.getQtdeInscritos();
		
		//ASSERTION
		assertEquals(qtdeEsperada, qtdeInscritos, "Uma inscrição deve aumentar o total para 1");
	}

	@Test
	@DisplayName("ET-005 - Um evento não pode ultrapassar sua capacidade máxima")
	void naoDeveUltrapassarCapacidadeMaxima() {
		
		//SETUP
		int capacidadeEsperada = 2;
		Evento evento = new Evento("Java Day", "Infnet", capacidadeEsperada);
		
		//EXECUÇÃO
		evento.inscrever();
		evento.inscrever();

		evento.inscrever();
		
		int qtdeInscritos = evento.getQtdeInscritos();
		
		//ASSERTION
		assertEquals(capacidadeEsperada, qtdeInscritos, "A quantidade de inscritos não pode ultrapassar 2");
	}

	@Test
	@DisplayName("ET-006 - Um evento não poderá ser criado com capacidade inválida")
	void deveRejeitarCapacidadeNegativa() {
		
		//EXECUÇÃO + ASSERTION
		assertThrows(
				IllegalArgumentException.class, 
				() -> new Evento("Java Day", "Infnet", -5), 
				"Uma capacidade negativa deve ser rejeitada"
			);
	}
}