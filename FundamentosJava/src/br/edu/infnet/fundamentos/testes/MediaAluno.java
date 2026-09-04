package br.edu.infnet.fundamentos.testes;

import java.util.Scanner;

import br.edu.infnet.fundamentos.model.Aluno;

public class MediaAluno {

	public static void main(String[] args){
			
		Scanner sc = new Scanner(System.in);

		Aluno aluno1 = null;
		System.out.println("Informe o seu nome: ");
		aluno1 = new Aluno(sc.nextLine());
		System.out.print("Informe a nota do TP1: ");
		aluno1.setNotaTP1(sc.nextDouble());
		System.out.print("Informe a nota do TP2: ");
		aluno1.setNotaTP2(sc.nextDouble());
		System.out.print("Informe a nota do TP3: ");
		aluno1.setNotaTP3(sc.nextDouble());
		aluno1.imprimir();

		
		System.out.println("Informe o seu nome: ");
		String nome = sc.nextLine();
		
		System.out.print("Informe a nota do TP1: ");
		double notaTP1 = sc.nextDouble();

		System.out.print("Informe a nota do TP2: ");
		double notaTP2 = sc.nextDouble();
		
		System.out.print("Informe a nota do TP3: ");
		double notaTP3 = sc.nextDouble();

		Aluno aluno2 = new Aluno(nome, notaTP1, notaTP2, notaTP3);
		aluno2.imprimir();
		
		sc.close();
		
	}
}