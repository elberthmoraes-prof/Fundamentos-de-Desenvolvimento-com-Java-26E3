package br.edu.infnet.fundamentos;

import java.util.Scanner;

import br.edu.infnet.fundamentos.model.Aluno;

public class PrimeiroScanner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); 
        String nome;
        boolean nomeVazio = false;
        
       do {
    	   System.out.println("Digite seu nome: ");

    	   nome = sc.nextLine();
    	   nomeVazio = nome.isBlank();
    	   
    	 if (nomeVazio)  {
    		 System.out.println("O nome não pode ficar vazio");	 
    	 }
    	  
       } while (nomeVazio);

        sc.close();

        new Aluno().imprimir(nome);
    }
}