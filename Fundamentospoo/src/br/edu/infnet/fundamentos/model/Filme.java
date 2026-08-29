package br.edu.infnet.fundamentos.model;

public class Filme {

	private String titulo;
	private int ano;	
	private double avaliacao;
	
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
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public void setAno(int ano){
		this.ano = ano;
	}
}