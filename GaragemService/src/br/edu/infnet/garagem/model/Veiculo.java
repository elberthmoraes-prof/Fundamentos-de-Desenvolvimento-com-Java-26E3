package br.edu.infnet.garagem.model;

public abstract class Veiculo {

	private String placa;
	private String marca;
	private int ano;

	public Veiculo(String placa, String marca, int ano) {
		//RF01
		if(ano < 1900 || ano > 2026) {
			throw new IllegalArgumentException("Ano do veículo está inválido: " + ano);
		}
		
		this.placa = placa;
		this.marca = marca;
		this.ano = ano;
	}
	
	public abstract double calcularCustoManutencao();

	public void exibirResumo() {
		System.out.printf("%s - %s - %d", placa, marca, ano);
	}	
	
	public final void exibirIdentificacao() {
		System.out.println("Exibir ID!!!");
	}
}