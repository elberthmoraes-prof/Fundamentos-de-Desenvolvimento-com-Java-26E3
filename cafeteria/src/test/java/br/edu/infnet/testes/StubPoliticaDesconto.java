package br.edu.infnet.testes;

import br.edu.infnet.model.Cliente;
import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.PoliticaDesconto;

public class StubPoliticaDesconto implements PoliticaDesconto {
	
	private double percentual;
	
	public StubPoliticaDesconto(double percentual) {
		this.percentual = percentual;
	}

	@Override
	public double obterPercentual(Pedido pedido, Cliente cliente) {

		return percentual;
	}

}
