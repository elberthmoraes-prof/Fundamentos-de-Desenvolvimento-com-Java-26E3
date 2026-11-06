package br.edu.infnet.service;

import br.edu.infnet.model.Evento;
import br.edu.infnet.model.Participante;

public interface AuditoriaInscricoes {

	void registrarInscricao(Evento evento, Participante participantes);
}
