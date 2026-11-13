package br.edu.infnet.testes;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.HistoricoPedidos;

public class FakeHistoricoPedidos implements HistoricoPedidos {

	private final List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@Override
	public void registrar(Pedido pedido) {
		pedidos.add(pedido);
	}
	
	public int obterQuantidade(){
		return pedidos.size();
	}

	public List<Pedido> consultarPedidos(){
		return new ArrayList<Pedido>(pedidos);
	}
}