package br.edu.infnet.model;

import br.edu.infnet.service.AuditoriaInscricoes;

public class SpyAuditoriaInscricoes implements AuditoriaInscricoes {

	private int qtdeChamadas;
	private Evento ultimoEvento;
	private Participante ultimoParticipante;
	
	@Override
	public void registrarInscricao(Evento evento, Participante participante) {

		qtdeChamadas++;
		
		ultimoEvento = evento;
		ultimoParticipante = participante;
	}

	public int getQtdeChamadas() {
		return qtdeChamadas;
	}

	public Evento getUltimoEvento() {
		return ultimoEvento;
	}

	public Participante getUltimoParticipante() {
		return ultimoParticipante;
	}
}