package br.edu.infnet.fundamentos.model;

public class Aluno {
	private String nome;
	private double notaTP1;
	private double notaTP2;
	private double notaTP3;
	
	public Aluno(String nome) {
		if(nome.length() < 3) {
			System.err.println("O nome precisa ter pelo menos três letras!");
			return;
		}

		this.nome = nome;
	}
	
	public Aluno(String nome, double notaTP1, double notaTP2, double notaTP3) {
		this(nome);
		this.notaTP1 = notaTP1;
		this.notaTP2 = notaTP2;
		this.notaTP3 = notaTP3;
	}
	
	public void imprimir(String nome) {
		System.out.println("Maravilha!!! Bom te receber, " + nome + "!");		
	}

	private double calcularMedia(){

		return (notaTP1 + notaTP2 + notaTP3)/3;
	}
	
	private String obterSituacao(double media){

		if(media >= 7) {
			return "Aprovado";
		} else if (media >= 5) {
			return "Recuperação";
		} else {
			return "Reprovado";
		}
	}

	public void imprimir() {
		
		double media = calcularMedia();
		String situacao = obterSituacao(media);
		
		System.out.printf("A média do aluno %s é igual a %.2f\n", nome, media);
		System.out.printf("O aluno %s está %s", nome, situacao);		
	}

	public void setNotaTP1(double notaTP1) {

		this.notaTP1 = notaTP1;
	}

	public void setNotaTP2(double notaTP2) {

		this.notaTP2 = notaTP2;
	}

	public void setNotaTP3(double notaTP3) {
		
		this.notaTP3 = notaTP3;
	}
}