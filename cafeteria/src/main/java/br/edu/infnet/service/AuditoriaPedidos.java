package br.edu.infnet.service;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;

public interface AuditoriaPedidos {

	void registrarConclusao(Pedido pedido, Cliente cliente);
}
