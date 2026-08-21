package br.edu.infnet.fundamentos;

import java.util.Scanner;

public class MediaAluno {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Informe a nota do TP1: ");
		double notaTP1 = sc.nextDouble();
		
		System.out.print("Informe a nota do TP2: ");
		double notaTP2 = sc.nextDouble();

		System.out.print("Informe a nota do TP3: ");
		double notaTP3 = sc.nextDouble();
		
		String situacao = null;

		double media = (notaTP1 + notaTP2 + notaTP3)/3;

		if(media >= 7) {
			situacao = "Aprovado";
		} else if (media >= 5) {
			situacao = "Recuperação";
		} else {
			situacao = "Reprovado";
		}
		
		String nome = "Elberth";
				
		System.out.printf("A média do aluno %s é igual a %.2f\n", nome, media);
		System.out.printf("O aluno %s está %s", nome, situacao);

		sc.close();
	}
}