package br.edu.infnet.fundamentos.model;

public class Filme {

	private String titulo;
	private int ano;	
	private double avaliacao;
	
	public Filme(String titulo, int ano) {
		this.titulo = titulo;
		this.ano = ano;
	}
	
	public void impressao(){

		System.out.printf("[Filme] O filmes %s lançado em %d teve avaliação %.2f!!!%n", titulo, ano, avaliacao);

		double notaEmEstrelas = calcularNotaEmEstrelas();
		
		System.out.println("Estrelas: " + notaEmEstrelas);
	}
	
	public void exibirResumo(){

		double nota = calcularNotaEmEstrelas();
		
		System.out.println(titulo + " (" + ano +"): estrelas >>> " + nota);
	}

	public void avaliar(double avaliacao){

		if(avaliacao < 0 || avaliacao > 10) {
			System.err.println("Deu ruim!!!");
			return;
		}

		this.avaliacao = avaliacao;
	}
	
	private double calcularNotaEmEstrelas() {
		
		return avaliacao / 2;
	}
}