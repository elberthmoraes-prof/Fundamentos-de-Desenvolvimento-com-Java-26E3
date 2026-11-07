package br.edu.infnet.integracao_api.service;

import br.edu.infnet.integracao_api.model.Evento;
import br.edu.infnet.integracao_api.model.Participante;

public class InscricaoService {

	private final HistoricoInscricoes historico;
	private final PoliticaInscricao politica;
	private final AuditoriaInscricoes auditoria;
	private final AutorizadorInscricao autorizador;
	
	public InscricaoService(HistoricoInscricoes historico, PoliticaInscricao politica, AuditoriaInscricoes auditoria, AutorizadorInscricao autorizador) {
		this.historico = historico;
		this.politica = politica;
		this.auditoria = auditoria;
		this.autorizador = autorizador;
	}

	public InscricaoService(HistoricoInscricoes historico, PoliticaInscricao politica, AuditoriaInscricoes auditoria) {
		
		this(historico, politica, auditoria, (evento, participante) -> true);
	}

	public InscricaoService(HistoricoInscricoes historico, PoliticaInscricao politica) {
		this(historico, politica, (evento, participante) -> { });
	}

	public InscricaoService(HistoricoInscricoes historico) {
		this(historico, (evento, participante) -> true);
	}

	public boolean realizarInscricao(Evento evento, Participante participante){
		
		boolean permissao = politica.permiteInscricao(evento, participante);
		
		if(!permissao) {
			
			return false;
		}
		
		boolean autorizado = autorizador.autorizar(evento, participante);
		
		if(!autorizado) {
			throw new SecurityException("Inscrição não autorizada.");
		}
		
		boolean resultado = evento.inscrever();
		
		if(resultado) {
			historico.registrar(evento.toString());
			
			auditoria.registrarInscricao(evento, participante);
		}
		
		return resultado;
	}

}