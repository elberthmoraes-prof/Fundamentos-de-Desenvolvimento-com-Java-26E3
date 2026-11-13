package br.edu.infnet.model;

public class Pedido {

	private double valor;
	
	public Pedido(double valor) {
		if(valor < 0) {
			throw new IllegalArgumentException("O valor do pedido não pode ser negativo.");
		}
		
		this.valor = valor;
	}

	public double calcularValorFinal(double percentualDesconto) {
		
		if(percentualDesconto < 0 || percentualDesconto > 100) {
			throw new IllegalArgumentException("Percentual de desconto inválido.");
		}
		
		double desconto = valor * percentualDesconto / 100;
		
		return valor - desconto;
		
	}

}
