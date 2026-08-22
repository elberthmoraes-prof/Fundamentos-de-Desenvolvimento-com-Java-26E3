package br.edu.infnet.fundamentos;

public class Profissional {
	String nome;
	String profissao;
	int idade;
	String estado;
	double altura;
	boolean professor;
	String cidade;
	double salario;
	String empresa;
	
	void imprimir() {
		System.out.println("O meu nome eh " + nome + " e sou " + profissao + ".");
		System.out.println("Tenho " + idade + " anos e sou natural do " + estado + ".");
		System.out.println("Tenho " + altura + " metros de altura, moro na cidade de " + cidade + ".");
		System.out.println("Atualmente, eu tenho o rendimento de R$" + salario + " na " + empresa + "!");

		String situacao = professor ? "Também" : "Não";				
		System.out.println(situacao + " atuo como professor.");				
	}
}