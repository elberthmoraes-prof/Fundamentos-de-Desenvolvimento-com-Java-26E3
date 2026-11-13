package br.edu.infnet.testes;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.AuditoriaPedidos;

public class SpyAuditoriaPedidos implements AuditoriaPedidos {

	private int qtdeChamados;
	private Pedido ultimoPedido;
	private Cliente ultimoCliente;
	
	@Override
	public void registrarConclusao(Pedido pedido, Cliente cliente) {

		qtdeChamados++;
		ultimoPedido = pedido;
		ultimoCliente = cliente;
	}

	public int getQtdeChamados() {
		return qtdeChamados;
	}

	public Pedido getUltimoPedido() {
		return ultimoPedido;
	}

	public Cliente getUltimoCliente() {
		return ultimoCliente;
	}
}
