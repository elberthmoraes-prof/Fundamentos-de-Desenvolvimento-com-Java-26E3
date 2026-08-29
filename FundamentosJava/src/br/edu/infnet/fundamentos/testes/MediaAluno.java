package br.edu.infnet.fundamentos.testes;

import java.util.Scanner;

import br.edu.infnet.fundamentos.model.Aluno;

public class MediaAluno {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		Aluno aluno = new Aluno();
		
		System.out.println("Informe o seu nome: ");
		aluno.setNome(sc.nextLine());

		System.out.print("Informe a nota do TP1: ");
		aluno.setNotaTP1(sc.nextDouble());
		
		System.out.print("Informe a nota do TP2: ");
		aluno.setNotaTP2(sc.nextDouble());

		System.out.print("Informe a nota do TP3: ");
		aluno.setNotaTP3(sc.nextDouble());
		
		aluno.imprimir();

		sc.close();
		
	}
}