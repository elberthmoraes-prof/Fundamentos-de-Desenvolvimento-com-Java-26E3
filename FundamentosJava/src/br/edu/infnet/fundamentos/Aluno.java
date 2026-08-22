package br.edu.infnet.fundamentos;

public class Aluno {
	String nome;
	double notaTP1;
	double notaTP2;
	double notaTP3;
	double media;
	String situacao;

	void imprimir(String nome) {
		System.out.println("Maravilha!!! Bom te receber, " + nome + "!");		
	}
	
	void imprimir() {
		System.out.printf("A média do aluno %s é igual a %.2f\n", nome, media);
		System.out.printf("O aluno %s está %s", nome, situacao);		
	}
}