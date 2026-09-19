package br.edu.infnet.garagem;

import br.edu.infnet.garagem.interfaces.Revisto;
import br.edu.infnet.garagem.model.Caminhao;
import br.edu.infnet.garagem.model.Carro;
import br.edu.infnet.garagem.model.Maquina;
import br.edu.infnet.garagem.model.Moto;
import br.edu.infnet.garagem.model.Veiculo;

public class GaragemApp {

	public static void main(String[] args) {
		Maquina maquina = new Maquina("Lavadora");
		Carro carro = new Carro("ABC1234", "Qualquer", 1900, 8);
		
		Revisto[] revistos = {maquina, carro};
		
		for(Revisto r : revistos) {
			r.realizarRevisao();
		}
		

		Veiculo[] veiculos = new Veiculo[6];
		veiculos[0] = new Carro("ABC1D23", "Toyota", 2024, 4);
		veiculos[1] = new Carro("ABC2E46", "Fiat", 1947, 2);
		veiculos[2] = new Moto("XYZ9A87", "Honda", 2023, 1000);
		veiculos[3] = new Moto("XYZ0B43", "Yamaha", 2026, 125);
		veiculos[4] = new Caminhao("KLM1N23", "Volvo", 2021, 18.5);
		veiculos[5] = carro;
		//veiculos[6] = maquina;
		
		for(Veiculo v : veiculos) {

			v.exibirResumo();
			
			double custoManutencao = v.calcularCustoManutencao();
			
			System.out.printf("Manutenção estimada: R$ %.2f%n", custoManutencao);
			
			System.out.println("---------------------------");
		}
	}
}