package br.edu.infnet.service;

import br.edu.infnet.model.Evento;
import br.edu.infnet.model.Participante;

public class InscricaoService {

	private final HistoricoInscricoes historico;
	private final PoliticaInscricao politica;
	private final AuditoriaInscricoes auditoria;
	
	public InscricaoService(HistoricoInscricoes historico, PoliticaInscricao politica, AuditoriaInscricoes auditoria) {
		this.historico = historico;
		this.politica = politica;
		this.auditoria = auditoria;
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
		
		boolean resultado = evento.inscrever();
		
		if(resultado) {
			historico.registrar(evento.toString());
			
			auditoria.registrarInscricao(evento, participante);
		}
		
		return resultado;
	}

}