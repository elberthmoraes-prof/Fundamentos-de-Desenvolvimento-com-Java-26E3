package br.edu.infnet.service;

import br.edu.infnet.model.Evento;
import br.edu.infnet.model.Participante;

public interface PoliticaInscricao {

	boolean permiteInscricao(Evento evento, Participante participante);
}
