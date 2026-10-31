package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.model.Evento;
import br.edu.infnet.model.FakeHistoricoInscricoes;
import br.edu.infnet.model.Participante;
import br.edu.infnet.service.HistoricoInscricoes;
import br.edu.infnet.service.InscricaoService;

@DisplayName("Testes do serviço de inscrições")
public class InscricaoServiceTeste {

	private HistoricoInscricoes criarHistorico(){

		return new FakeHistoricoInscricoes();
	}
	
	@Test
	@DisplayName("ET-18A - Deve aceitar inscrição com Dummy")
	void deveAceitarInscricaoComDummy(){
		
		// ARRANGE
		HistoricoInscricoes historico = criarHistorico();
		Evento evento = new Evento("Java Day", "Infnet", 10);
		Participante participanteDummy = new Participante("Participante Fictício");
		InscricaoService service = new InscricaoService(historico);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participanteDummy);
		int qtdeInscritos = evento.getQtdeInscritos();
		
		// ASSERT
		assertAll(
			() -> assertTrue(resultado),
			() -> assertEquals(1, qtdeInscritos)
		);
		
	}

	@Test
	@DisplayName("ET-18B - Deve recusar inscrição em evento lotado")
	void deveRecusarInscricaoComDummy(){
		
		// ARRANGE
		HistoricoInscricoes historico = criarHistorico();
		Evento evento = new Evento("Java Day", "Infnet", 1);
		evento.inscrever();
		Participante participanteDummy = new Participante("Outro Participante");
		InscricaoService service = new InscricaoService(historico);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participanteDummy);
		int qtdeInscritos = evento.getQtdeInscritos();
		
		// ASSERT
		assertAll(
			() -> assertFalse(resultado),
			() -> assertEquals(1, qtdeInscritos)
		);
		
	}

	@Test
	@DisplayName("ET-19C - Deve registrar inscrição aceita")
	void deveRegistrarInscricaoAceita() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 1);
		Participante participanteDummy = new Participante("Outro Participante");
		
		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		InscricaoService service = new InscricaoService(historico);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participanteDummy);
		int qtdeInscritos = evento.getQtdeInscritos();
		
		// ASSERT
		assertAll(
			() -> assertTrue(resultado),
			() -> assertEquals(1, qtdeInscritos)
		);
		
	}
	
	@Test
	@DisplayName("ET-19D - Não deve registrar inscrição recusada")
	void naoDeveRegistrarInscricaoRecusada() {

		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 1);
		
		Participante participanteDummy = new Participante("Outro Participante");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		InscricaoService service = new InscricaoService(historico);

		service.realizarInscricao(evento, participanteDummy);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participanteDummy);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();

		// ASSERT
		assertAll(
			() -> assertFalse(resultado),
			
			() -> assertEquals(1, qtdeInscritos),
			
			() -> assertEquals(1, quantidade)
		);
	}
}