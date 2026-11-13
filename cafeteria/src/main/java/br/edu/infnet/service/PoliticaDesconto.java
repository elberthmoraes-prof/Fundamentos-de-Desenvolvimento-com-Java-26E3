package br.edu.infnet.service;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;

public interface PoliticaDesconto {

	double obterPercentual(Pedido pedido, Cliente cliente);
}
