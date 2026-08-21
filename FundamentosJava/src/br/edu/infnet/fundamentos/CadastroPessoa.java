package br.edu.infnet.fundamentos;

import java.util.Scanner;

public class CadastroPessoa {
	
	public static void main(String[] args){
	
		Scanner in = new Scanner(System.in);
	
		String nome = null;
		int idade = 0;
		double altura = 0;

		boolean oNomeTahVazio = false;

		do {			
			System.out.print("Digite o seu nome: ");

			nome = in.nextLine();
			
			oNomeTahVazio = nome.isBlank();

			if(oNomeTahVazio) {
				System.out.println("O nome não pode ficar vazio!");
			}
			
		} while (oNomeTahVazio);
		
		do {
			System.out.print("Idade: ");	//criança (< 12), adolescente (< 18), adulto (< 60), idoso (>= 60)
			idade = in.nextInt();

			if(idade < 0 || idade > 120) {
				System.out.println("Informe a idade entre 0 e 120.");
			}
		} while (idade < 0 || idade > 120);
		
		
		do {			
			System.out.print("Altura: ");
			altura = in.nextDouble();
			
			if(altura <= 0 || altura > 3) {
				System.out.println("Informe a altura maior que zero e menos que três.");
			}
		} while (altura <= 0 || altura > 3);
		
		System.out.println("Eu sou " + nome + ", tenho " + idade + " anos e " + altura + " de altura!");

		if(idade < 0) {		
			System.out.println("Foi impossível definir a faixa etária através da idade ["+idade+"] informada!");
		} else {
			String faixaEtaria = null;
			
			if(idade < 12) {
				faixaEtaria = "criança";
			} else if (idade < 18) {
				faixaEtaria = "adolescente";
			} else if (idade < 60) {
				faixaEtaria = "adulto";
			} else {
				faixaEtaria = "idoso";
			}
			
			System.out.println("Classificação: " + faixaEtaria);
		}

		in.close();
	}
}