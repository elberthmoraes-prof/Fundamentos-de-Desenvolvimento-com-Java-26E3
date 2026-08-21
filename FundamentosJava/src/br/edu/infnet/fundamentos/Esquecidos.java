package br.edu.infnet.fundamentos;

import java.util.Random;
import java.util.Scanner;

public class Esquecidos {

	public static void main(String[] args) {
		
		final int VALOR_MINIMO = 1;
		final int VALOR_MAXIMO = 10;

		Random random = new Random();
		
		int numero = random.nextInt(VALOR_MAXIMO - VALOR_MINIMO + 1) + VALOR_MINIMO;
		
		System.out.printf("Número sorteado entre %d e %d = %d\n:", VALOR_MINIMO, VALOR_MAXIMO, numero);
		
		Scanner in = new Scanner(System.in);

		String nome;
		do {			
			System.out.println("Informe o nome: ");
			nome = in.nextLine();
		} while(nome.isBlank());
		System.out.println("Nome: [" + nome + "]");
		
		in.close();
		
		final double NOTA_APROVACAO = 8;
		
		double notaAluno = 6;
		
		if(notaAluno > NOTA_APROVACAO) {
			System.out.println("aprovado");
		} else {
			System.out.println("Perdeu!!!");
		}
				
		notaAluno = 7;

		if(notaAluno > NOTA_APROVACAO) {
			System.out.println("aprovado");
		} else {
			System.out.println("Perdeu!!!");
		}
		
		notaAluno = 8;

		if(notaAluno > NOTA_APROVACAO) {
			System.out.println("aprovado");
		} else {
			System.out.println("Perdeu!!!");
		}
		
		notaAluno = 9;

		if(notaAluno > NOTA_APROVACAO) {
			System.out.println("aprovado");
		} else {
			System.out.println("Perdeu!!!");
		}
}
}
