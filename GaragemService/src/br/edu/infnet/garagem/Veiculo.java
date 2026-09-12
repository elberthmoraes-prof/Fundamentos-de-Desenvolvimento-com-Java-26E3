package br.edu.infnet.garagem;

public abstract class Veiculo {

	private String placa;
	private String marca;
	private int ano;

	public Veiculo(String placa, String marca, int ano) {
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