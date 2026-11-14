package br.edu.infnet.service;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;

public class PedidoService {

	private final HistoricoPedidos historico;
	private final PoliticaDesconto politica;
	private final AuditoriaPedidos auditoria;
	private final AutorizadorPedido autorizador;
	
	public PedidoService(HistoricoPedidos historico) {
		this(historico, null, (pedido, cliente) -> { }, (pedido, cliente) -> true);
	}

	public PedidoService(HistoricoPedidos historico, PoliticaDesconto politica) {
		
		this(historico, politica, (pedido, cliente) -> { }, (pedido, cliente) -> true);
	}
	
	public PedidoService(HistoricoPedidos historico, PoliticaDesconto politica, AuditoriaPedidos auditoria) {
		this(historico, politica, auditoria, (pedido, cliente) -> true);
	}

	public PedidoService(HistoricoPedidos historico, PoliticaDesconto politica, AuditoriaPedidos auditoria, AutorizadorPedido autorizador) {
		this.historico = historico;
		this.politica = politica;
		this.auditoria = auditoria;
		this.autorizador = autorizador;
	}

	public double concluirPedido(Pedido pedido, Cliente cliente, double percentualDesconto) {
		
		double valorFinal = pedido.calcularValorFinal(percentualDesconto);
		
		boolean autorizado = autorizador.autorizar(pedido, cliente);
		
		if(!autorizado) {
			throw new SecurityException("Pedido não está autorizado.");
		}

		historico.registrar(pedido);
		
		auditoria.registrarConclusao(pedido, cliente);
		
		return valorFinal;
	}
	
	public double concluirPedido(Pedido pedido, Cliente cliente) {
		
		if(politica == null) {
			throw new IllegalStateException("Política de desconto não configurada.");
		}
		
		double percentual = politica.obterPercentual(pedido, cliente);
		
		return concluirPedido(pedido, cliente, percentual);
	}

	public double concluirPedidoComTeto(Pedido pedido, Cliente cliente) {

		return concluirPedido(pedido, cliente);
	}
}
