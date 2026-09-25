package br.edu.infnet.garagem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.edu.infnet.garagem.model.Caminhao;
import br.edu.infnet.garagem.model.Carro;
import br.edu.infnet.garagem.model.Moto;
import br.edu.infnet.garagem.model.Veiculo;

public class GaragemArquivosApp {

	public static void main(String[] args) {

		Veiculo[] veiculos = new Veiculo[10];

		int qtde = 0;
		
		try {
			try {
				//RF04
				FileReader fileLeitura = new FileReader("veiculos.csv");
				BufferedReader leitura = new BufferedReader(fileLeitura);
				
				String linha = null;
				linha = leitura.readLine();
				
				while (linha != null) {
				
					try {
						
						//RF05
						String[] campos = linha.split(";");
						
						String tipoVeiculo = campos[0];

						Integer.valueOf(campos[3]);
						
						Integer.parseInt(campos[3]);
						
						Veiculo veiculo = null;
						
						//RF06
						switch (tipoVeiculo) {
						case "AP":
							veiculo = new Carro(campos[1], campos[2], Integer.valueOf(campos[3]), Integer.valueOf(campos[4]));
							break;

						case "M":
							veiculo = new Moto(campos[1], campos[2], Integer.valueOf(campos[3]), Integer.valueOf(campos[4]));
							break;
						
						case "C":
							veiculo = new Caminhao(campos[1], campos[2], Integer.valueOf(campos[3]), Double.valueOf(campos[4]));
							break;

						default:
							throw new IllegalArgumentException("Tipo de veículo inválido: " + tipoVeiculo);
						}
						
						veiculos[qtde++] = veiculo;
						
						//RF07
					} catch (IllegalArgumentException e) {
						System.err.println("Linha inválida: " + linha);
						System.err.println("Motivo: " + e.getMessage());
					}

					linha = leitura.readLine();
				}

				FileWriter fileEscrita = new FileWriter("relatorio-garagem.txt");
				BufferedWriter escrita = new BufferedWriter(fileEscrita);
				
				escrita.write("RELATÓRIO DA GARAGEM");
				escrita.newLine();

				//RF08
				System.out.println("VEÍCULOS PROCESSADOS");
				for(int i = 0; i < qtde; i++) {
					veiculos[i].exibirResumo();
					
					double custoManutencao = veiculos[i].calcularCustoManutencao();
					
					System.out.println("Custo de Manutenção = " + custoManutencao);
					System.out.println("----");
					
					escrita.write(veiculos[i].toString() + " - " + custoManutencao);
					escrita.newLine();
				}
				System.out.println("====================");

				leitura.close();

				
				escrita.close();
				
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
