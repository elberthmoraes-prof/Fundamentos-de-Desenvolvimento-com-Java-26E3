package br.edu.infnet.garagem;

public class Carro extends Veiculo implements Revisto {
	
	private int numeroPortas;

	public Carro(String placa, String marca, int ano, int numeroPortas) {
		super(placa, marca, ano);
		this.numeroPortas = numeroPortas;
	}

	@Override
	public void exibirResumo() {
		System.out.printf("Carro: ");
		super.exibirResumo();
		System.out.printf(" - %d%n", numeroPortas);
	}

	@Override
	public double calcularCustoManutencao() {

		return 800 + (numeroPortas * 50);
	}

	@Override
	public void realizarRevisao() {
		System.out.println("Realizando a revisão de itens de um carro!");
	}
}

