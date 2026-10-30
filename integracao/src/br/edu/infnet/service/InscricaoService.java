package br.edu.infnet.service;

import br.edu.infnet.model.Evento;

public class InscricaoService {

	private HistoricoInscricoes historico;
	
	public InscricaoService(HistoricoInscricoes historico) {
		this.historico = historico;
	}

	public boolean realizarInscricao(Evento evento){
		
		historico.registrar(evento.toString());
		
		return evento.inscrever();
	}

}
