package br.edu.infnet.service;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;

public interface AutorizadorPedido {

	boolean autorizar(Pedido pedido, Cliente cliente);
}
