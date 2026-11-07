package br.edu.infnet.integracao_api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.integracao_api.model.Evento;
import br.edu.infnet.integracao_api.model.FakeHistoricoInscricoes;
import br.edu.infnet.integracao_api.model.Participante;
import br.edu.infnet.integracao_api.model.SpyAuditoriaInscricoes;
import br.edu.infnet.integracao_api.model.StubPoliticaInscricao;
import br.edu.infnet.integracao_api.service.AutorizadorInscricao;
import br.edu.infnet.integracao_api.service.HistoricoInscricoes;
import br.edu.infnet.integracao_api.service.InscricaoService;

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
	
	@Test
	@DisplayName("ET-20A - Deve aceitar inscrição permitida pela política")
	void deveAceitarInscricaoPermitidaPelaPolitica() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Dona Maria");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		InscricaoService service = new InscricaoService(historico, politica);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();
				
		// ASSERT
		assertAll(
		
			() -> assertTrue(resultado),
			
			() -> assertEquals(1, qtdeInscritos),
			
			() -> assertEquals(1, quantidade)
		);
		
	}
	
	@Test
	@DisplayName("ET-20B - Deve recusar inscrição pela política")
	void deveRecusarInscricaoPelaPolitica() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Sr João");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(false);
		
		InscricaoService service = new InscricaoService(historico, politica);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();
				
		// ASSERT
		assertAll(
		
			() -> assertFalse(resultado),
			
			() -> assertEquals(0, qtdeInscritos),
			
			() -> assertEquals(0, quantidade)
		);
		
	}

	@Test
	@DisplayName("ET-20C - Deve recusar evento lotado")
	void deveRecusarEventoLotadoMesmoComPoliticaPermitida() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 1);
		
		evento.inscrever();
		
		Participante participante = new Participante("Sr João");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(false);
		
		InscricaoService service = new InscricaoService(historico, politica);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();
				
		// ASSERT
		assertAll(
		
			() -> assertFalse(resultado),
			
			() -> assertEquals(1, qtdeInscritos),
			
			() -> assertEquals(0, quantidade)
		);
		
	}
	
	@Test
	@DisplayName("ET-21A - Deve auditar uma inscrição aceita")
	void deveAuditarInscricaoAceita() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Lucas Santos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();
		int qtdeChamadas = auditoria.getQtdeChamadas();
		
		// ASSERT
		assertAll(
				
				() -> assertTrue(resultado),
				
				() -> assertEquals(1, qtdeInscritos),
				
				() -> assertEquals(1, quantidade),
				
				() -> assertEquals(1, qtdeChamadas)
			);
		
		
	}
	@Test
	@DisplayName("ET-21B - Não deve auditar uma inscrição recusada")
	void naoDeveAuditarInscricaoRecusada() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Lucas Santos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(false);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();
		int qtdeChamadas = auditoria.getQtdeChamadas();
		
		// ASSERT
		assertAll(
				
				() -> assertFalse(resultado),
				
				() -> assertEquals(0, qtdeInscritos),
				
				() -> assertEquals(0, quantidade),
				
				() -> assertEquals(0, qtdeChamadas)
			);
		
		
	}
	@Test
	@DisplayName("ET-21C - Não deve auditar evento lotado")
	void naoDeveAuditarEventoLotado() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 1);
		
		evento.inscrever();
		
		Participante participante = new Participante("Lucas Santos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		int quantidade = historico.obterQuantidade();
		int qtdeChamadas = auditoria.getQtdeChamadas();
		
		// ASSERT
		assertAll(
				
				() -> assertFalse(resultado),
				
				() -> assertEquals(1, qtdeInscritos),
				
				() -> assertEquals(0, quantidade),
				
				() -> assertEquals(0, qtdeChamadas)
			);
		
		
	}
	@Test
	@DisplayName("ET-21D - Deve auditar objetos corretos")
	void deveAuditarObjetosCorretos() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Lucas Santos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria);
		
		// ACT
		service.realizarInscricao(evento, participante);
		int qtdeChamadas = auditoria.getQtdeChamadas();
		Evento ultimoEvento = auditoria.getUltimoEvento();
		Participante ultimoParticipante = auditoria.getUltimoParticipante();
		
		// ASSERT
		
		assertAll(
				
				() -> assertEquals(1, qtdeChamadas),
				
				() -> assertSame(evento, ultimoEvento),
				
				() -> assertSame(participante, ultimoParticipante)
				
			);
	}
	
	@Test
	@DisplayName("ET-22A - Deve aceitar uma inscrição autorizada")
	void deveAceitarInscricaoAutorizada() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Lucas Santos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		AutorizadorInscricao autorizador = mock(AutorizadorInscricao.class);
		
		when(autorizador.autorizar(evento, participante)).thenReturn(true);
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria, autorizador);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		int qtdeInscritos = evento.getQtdeInscritos();
		
		// ASSERT
		assertAll(
			() -> assertTrue(resultado),
			
			() -> assertEquals(1, qtdeInscritos)
		);
		
		verify(autorizador, times(1)).autorizar(evento, participante);
	}

	@Test
	@DisplayName("ET-22B - Deve recusar autorização negada")
	void deveRecusarAutorizacaoNegada() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Lucas Santos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		AutorizadorInscricao autorizador = mock(AutorizadorInscricao.class);
		
		when(autorizador.autorizar(evento, participante)).thenReturn(false);
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria, autorizador);
		
		String mensagemExcecao = "Inscrição não autorizada.";
		
		// ACT
		
		SecurityException excecao = 
				assertThrows(
						SecurityException.class, 
						() -> service.realizarInscricao(evento, participante)
					);
		int qtdeInscritos = evento.getQtdeInscritos();
		
		// ASSERT
		assertAll(
			() -> assertEquals(mensagemExcecao, excecao.getMessage()),
			
			() -> assertEquals(0, qtdeInscritos)
		);
		
		verify(autorizador, times(1)).autorizar(evento, participante);
	}
	
	@Test
	@DisplayName("ET-23A - Deve consultar o autorizador uma vez")
	void deveConsultarAutorizadorUmaVez() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Gabriel de Andrade");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		AutorizadorInscricao autorizador = mock(AutorizadorInscricao.class);
		
		when(autorizador.autorizar(evento, participante)).thenReturn(true);
		
		InscricaoService service = new InscricaoService(historico, politica, auditoria, autorizador);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		
		// ASSERT
		
		assertTrue(resultado);
		
		verify(autorizador, times(1)).autorizar(evento, participante);
	}

	@Test
	@DisplayName("ET-23B - Não deve consultar autorização quando a política for recusada")
	void napDeveConsultarAutorizacaoQuandoPolicitaRecusada() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Arthur Braz Farias");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(false);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		AutorizadorInscricao autorizador = mock(AutorizadorInscricao.class);
			
		InscricaoService service = new InscricaoService(historico, politica, auditoria, autorizador);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		
		// ASSERT
		assertFalse(resultado);
		
		verify(autorizador, never()).autorizar(evento, participante);
	}

	@Test
	@DisplayName("ET-23C - Não deve realizar outras interações")
	void napDeveRealizarOutrasInteracoes() {
		
		// ARRANGE
		Evento evento = new Evento("Java Day", "Infnet", 10);
		
		Participante participante = new Participante("Matheus Ramos");

		FakeHistoricoInscricoes historico = new FakeHistoricoInscricoes();
		
		StubPoliticaInscricao politica = new StubPoliticaInscricao(true);
		
		SpyAuditoriaInscricoes auditoria = new SpyAuditoriaInscricoes();
		
		AutorizadorInscricao autorizador = mock(AutorizadorInscricao.class);
		
		when(autorizador.autorizar(evento, participante)).thenReturn(true);		
			
		InscricaoService service = new InscricaoService(historico, politica, auditoria, autorizador);
		
		// ACT
		boolean resultado = service.realizarInscricao(evento, participante);
		
		// ASSERT
		assertTrue(resultado);
		
		verify(autorizador, times(1)).autorizar(evento, participante);
		
		verifyNoMoreInteractions(autorizador);
	}
}