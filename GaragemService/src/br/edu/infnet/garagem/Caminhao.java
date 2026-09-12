package br.edu.infnet.garagem;

public class Caminhao extends Veiculo {

	private double capacidadeCarga;
	
	public Caminhao(String placa, String marca, int ano, double capacidadeCarga) {
		super(placa, marca, ano);
		this.capacidadeCarga = capacidadeCarga;
	}

	@Override
	public void exibirResumo() {
		System.out.printf("Caminhão: ");
		super.exibirResumo();
		System.out.printf(" - %.2f%n", capacidadeCarga);
	}

	@Override
	public double calcularCustoManutencao() {

		return 1500 + (capacidadeCarga * 100);
	}
}
