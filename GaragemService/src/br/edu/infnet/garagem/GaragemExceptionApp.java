package br.edu.infnet.garagem;

import br.edu.infnet.garagem.model.Caminhao;
import br.edu.infnet.garagem.model.Carro;
import br.edu.infnet.garagem.model.Moto;

public class GaragemExceptionApp {

	public static void main(String[] args) {

		try {
			//RF03
			try {
				Carro fiat = new Carro("ABC2E46", "Fiat", 1847, 2);
				fiat.exibirResumo();

			} catch (IllegalArgumentException e) {
				System.err.println("Não foi possível criar o veículo: " + e.getMessage());
			}
			
			try {
				Carro toyota = new Carro("ABC1D23", "Toyota", 2030, 4);
				toyota.exibirResumo();
			} catch (IllegalArgumentException e) {
				System.err.println("Não foi possível criar o veículo: " + e.getMessage());
			}
			
			try {
				Moto honda = new Moto("XYZ9A87", "Honda", 2023, 1000);
				honda.exibirResumo();
			} catch (IllegalArgumentException e) {
				System.err.println("Não foi possível criar o veículo: " + e.getMessage());
			}

			try {
				Moto yamaha = new Moto("XYZ0B43", "Yamaha", 2026, 125);
				yamaha.exibirResumo();
				
			} catch (IllegalArgumentException e) {
				System.err.println("Não foi possível criar o veículo: " + e.getMessage());
			}

			try {
				Caminhao volvo = new Caminhao("KLM1N23", "Volvo", 2021, 18.5);
				volvo.exibirResumo();

			} catch (IllegalArgumentException e) {
				System.err.println("Não foi possível criar o veículo: " + e.getMessage());
			}		

		} finally {
			System.out.println("Fim do Programa");
		}
	}
}