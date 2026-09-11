package br.edu.infnet.garagem;

public class GaragemApp {

	public static void main(String[] args) {
		
		Veiculo[] veiculos = new Veiculo[5];
		veiculos[0] = new Carro("ABC1D23", "Toyota", 2024, 4);
		veiculos[1] = new Carro("ABC2E46", "Fiat", 1947, 2);
		veiculos[2] = new Moto("XYZ9A87", "Honda", 2023, 1000);
		veiculos[3] = new Moto("XYZ0B43", "Yamaha", 2026, 125);
		veiculos[4] = new Caminhao("KLM1N23", "Volvo", 2021, 18.5);
		
		for(Veiculo v : veiculos) {
			
			if(v instanceof Carro) {
				System.out.println("Carro");
			} else if (v instanceof Moto) {
				System.out.println("Moto");
			} else if (v instanceof Caminhao) {
				System.out.println("Caminhão");
			} else {
				System.out.println("Nada");
			}	
			
			v.exibirResumo();
		}
		
		Carro[] carros = new Carro[2];		
		carros[0] = new Carro("ABC1D23", "Toyota", 2024, 4);
		carros[1] = new Carro("ABC2E46", "Fiat", 1947, 2);
		
		for(Carro c : carros) {
			c.exibirResumo();
		}

		Moto[] motos = new Moto[2];
		motos[0] = new Moto("XYZ9A87", "Honda", 2023, 1000);
		motos[1] = new Moto("XYZ0B43", "Yamaha", 2026, 125);
		
		for(Moto m : motos) {
			m.exibirResumo();
		}
	}
}