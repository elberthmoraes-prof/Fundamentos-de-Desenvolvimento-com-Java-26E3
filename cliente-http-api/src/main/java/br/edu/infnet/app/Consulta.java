package br.edu.infnet.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;

public class Consulta {

	public static void main(String[] args) {
		String endereco = "https://apichallenges.eviltester.com/sim/entities";
		
		HttpURLConnection conexao = null;
		
		try {
			// 1 - abertura da conexão
			conexao = (HttpURLConnection)URI.create(endereco).toURL().openConnection();
			
			// 2 - configuração de uma requisição (GET)
			conexao.setRequestMethod("GET");
			conexao.setConnectTimeout(5000);
			conexao.setReadTimeout(5000);
			conexao.setRequestProperty("Accept", "application/json");
			
			// 3 - status do HTTP
			int status = conexao.getResponseCode();
			
			System.out.println("URL: " + endereco);
			System.out.println("STATUS HTTP: " + status);
			System.out.println("CONTENT TYPE: " + conexao.getContentType());
			
			// 4 - definir o fluxo da resposta
			
			// 5 - leitura do conteúdo
			BufferedReader leitura = new BufferedReader(null);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			System.out.println("Processo encerrado!!!");
			if(conexao != null) {
				conexao.disconnect();
			}
		}
		
		
		
		
		
				
		
		
	}
}
