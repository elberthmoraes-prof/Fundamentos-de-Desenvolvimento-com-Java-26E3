package br.edu.infnet.fundamentos;

import java.util.Scanner;

public class PrimeiroScanner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //instanciar um objeto 
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

        System.out.println("Maravilha!!! Bom te receber, " + nome + "!");
        sc.close();
    }
}