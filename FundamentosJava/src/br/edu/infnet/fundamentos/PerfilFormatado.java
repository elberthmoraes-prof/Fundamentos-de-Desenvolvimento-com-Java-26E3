package br.edu.infnet.fundamentos;

public class PerfilFormatado {
	
	public static void main(String[] args){

		String nome = "Elberth Moraes";
		String profissao = "Programador";
		int idade = 47;
		String estado = "Rio de Janeiro";
		double altura = 1.8;
		boolean professor = true;
		String cidade = "Marica";
		double salario = 99.99;
		String empresa = "Dataprev";

		System.out.println("O meu nome eh " + nome + " e sou " + profissao + ".");
		System.out.println("Tenho " + idade + " anos e sou natural do " + estado + ".");
		System.out.println("Tenho " + altura + " metros de altura, moro na cidade de " + cidade + ".");
		System.out.println("Atualmente, eu tenho o rendimento de R$" + salario + " na " + empresa + "!");

//		if(professor) {
//			System.out.println("Também atuo como professor.");
//		} else {
//			System.out.println("Não atuo como professor.");
//		}
		
		//operadores ternários				
		String situacao = professor ? "Também" : "Não";				
		System.out.println(situacao + " atuo como professor.");
		
	}
}