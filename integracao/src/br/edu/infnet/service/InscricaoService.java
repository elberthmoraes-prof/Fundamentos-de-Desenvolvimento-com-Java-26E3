package br.edu.infnet.service;

import br.edu.infnet.model.Evento;
import br.edu.infnet.model.Participante;

public class InscricaoService {

	private HistoricoInscricoes historico;
	
	public InscricaoService(HistoricoInscricoes historico) {
		this.historico = historico;
	}

	public boolean realizarInscricao(Evento evento, Participante participante){
		
		boolean resultado = evento.inscrever();
		
		if(resultado) {
			historico.registrar(evento.toString());
		}
		
		return resultado;
	}

}