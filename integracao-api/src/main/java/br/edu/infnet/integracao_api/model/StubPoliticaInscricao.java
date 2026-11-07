package br.edu.infnet.integracao_api.model;

import br.edu.infnet.integracao_api.service.PoliticaInscricao;

public class StubPoliticaInscricao implements PoliticaInscricao {

	private final boolean permitido;
	
	public StubPoliticaInscricao(boolean permitido) {
		this.permitido = permitido;
	}
	
	@Override
	public boolean permiteInscricao(Evento evento, Participante participante) {

		return permitido;
	}

}
