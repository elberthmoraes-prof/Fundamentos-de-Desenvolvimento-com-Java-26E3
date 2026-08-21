package br.edu.infnet.fundamentos;

public class Filme {

	String titulo;
	int ano;
	double avaliacao;

	void impressao(){
		System.out.printf("[Filme] O filmes %s lançado em %d teve avaliação %.2f!!!%n", titulo, ano, avaliacao);
	}
}
