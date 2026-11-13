package br.edu.infnet.service;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;

public class PedidoService {

	private final HistoricoPedidos historico;
	private final PoliticaDesconto politica;
	private final AuditoriaPedidos auditoria;
	
	public PedidoService(HistoricoPedidos historico) {
		this(historico, null, (pedido, cliente) -> { });
	}

	public PedidoService(HistoricoPedidos historico, PoliticaDesconto politica) {
		
		this(historico, politica, (pedido, cliente) -> { });
	}
	
	public PedidoService(HistoricoPedidos historico, PoliticaDesconto politica, AuditoriaPedidos auditoria) {
		this.historico = historico;
		this.politica = politica;
		this.auditoria = auditoria;
	}

	public double concluirPedido(Pedido pedido, Cliente cliente, double percentualDesconto) {
		
		double valorFinal = pedido.calcularValorFinal(percentualDesconto);

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
}
