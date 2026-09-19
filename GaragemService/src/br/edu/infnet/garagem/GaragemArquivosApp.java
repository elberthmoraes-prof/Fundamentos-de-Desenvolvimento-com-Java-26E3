package br.edu.infnet.garagem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.edu.infnet.garagem.model.Caminhao;
import br.edu.infnet.garagem.model.Carro;
import br.edu.infnet.garagem.model.Moto;

public class GaragemArquivosApp {

	public static void main(String[] args) {
		
		try {
			try {
				FileReader file = new FileReader("veiculos.csv");
				BufferedReader leitura = new BufferedReader(file);
				
				String linha = null;
				linha = leitura.readLine();
				
				while (linha != null) {
					System.out.println("Linha: " + linha);
					
					String[] campos = linha.split(";");
					
					String tipoVeiculo = campos[0];
					
					switch (tipoVeiculo) {
					case "AP":
						Carro carro = new Carro(linha, linha, 0, 0);
						break;

					case "M":
						Moto moto = new Moto(linha, linha, 0, 0);
						break;
					
					case "C":
						Caminhao caminhao = new Caminhao(linha, linha, 0, 0);
						break;

					default:
						break;
					}

					linha = leitura.readLine();
				}

			} catch (FileNotFoundException e) {
				System.err.println("Impossível abrir o arquivo: " + e.getMessage());

			} catch (IOException e) {
				System.err.println("Impossível ler o arquivo: " + e.getMessage());
			}
		} finally {
			System.out.println("Fim do Processamento");
		}
		
	}
}
