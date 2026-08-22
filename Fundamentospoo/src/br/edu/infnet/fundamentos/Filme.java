package br.edu.infnet.fundamentos;

public class Filme {

	String titulo;
	int ano;
	double avaliacao;

	void impressao(){
		System.out.printf("[Filme] O filmes %s lançado em %d teve avaliação %.2f!!!%n", titulo, ano, avaliacao);
	}
	
	void exibirResumo(){
		System.out.println(titulo + " (" + ano +")");
	}
	
	void avaliar(double nota){

		avaliacao = nota;
	}
	
	void atualizarAno(int novoAno){
		ano = novoAno;
	}
}
