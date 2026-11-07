package br.edu.infnet.service;

import br.edu.infnet.model.Evento;
import br.edu.infnet.model.Participante;

public interface AutorizadorInscricao {

	boolean autorizar(Evento evento, Participante participante);
}
