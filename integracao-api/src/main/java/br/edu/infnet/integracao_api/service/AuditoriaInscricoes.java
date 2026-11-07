package br.edu.infnet.integracao_api.service;

import br.edu.infnet.integracao_api.model.Evento;
import br.edu.infnet.integracao_api.model.Participante;

public interface AuditoriaInscricoes {

	void registrarInscricao(Evento evento, Participante participantes);
}
