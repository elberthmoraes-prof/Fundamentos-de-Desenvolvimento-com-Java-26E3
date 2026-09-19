package br.edu.infnet.garagem.model;

public class Moto extends Veiculo {
	
	private int cilindradas;

	public Moto(String placa, String marca, int ano, int cilindradas) {
		super(placa, marca, ano);
		this.cilindradas = cilindradas;
	}

	@Override
	public void exibirResumo() {
		System.out.printf("Moto: ");
		super.exibirResumo();
		System.out.printf(" - %d%n", cilindradas);
	}

	@Override
	public double calcularCustoManutencao() {

		return 300 + cilindradas;
	}
}
