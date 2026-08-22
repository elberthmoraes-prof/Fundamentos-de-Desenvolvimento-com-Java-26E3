package br.edu.infnet.fundamentos;

import java.util.Scanner;

public class MediaAluno {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		Aluno aluno = new Aluno();
		
		System.out.println("Informe o seu nome: ");
		aluno.nome = sc.nextLine();

		System.out.print("Informe a nota do TP1: ");
		aluno.notaTP1 = sc.nextDouble();
		
		System.out.print("Informe a nota do TP2: ");
		aluno.notaTP2 = sc.nextDouble();

		System.out.print("Informe a nota do TP3: ");
		aluno.notaTP3 = sc.nextDouble();
		
		aluno.media = (aluno.notaTP1 + aluno.notaTP2 + aluno.notaTP3)/3;

		if(aluno.media >= 7) {
			aluno.situacao = "Aprovado";
		} else if (aluno.media >= 5) {
			aluno.situacao = "Recuperação";
		} else {
			aluno.situacao = "Reprovado";
		}
		
		aluno.imprimir();

		sc.close();
		
	}
}