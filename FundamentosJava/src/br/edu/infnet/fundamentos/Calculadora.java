package br.edu.infnet.fundamentos;

import java.util.Scanner;

public class Calculadora {

	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
	
		int opcao = 0;
		
		do {
			
			System.out.println("Calculadora!!!");
			System.out.println("1 - Somar");
			System.out.println("2 - Subtrair");
			System.out.println("3 - Multiplicar");
			System.out.println("4 - Dividir");
			System.out.println("5 - Calcular o resto");
			System.out.println("9 - Sair");
			
			System.out.print("Escolha uma opção: ");
			
			opcao = scanner.nextInt();
			
			if(opcao >= 1 && opcao <= 5) {
				System.out.println("Informe o primeiro numero: ");
				int primeiroNumero = scanner.nextInt();

				System.out.println("Informe o segundo numero: ");
				int segundoNumero = scanner.nextInt();

				int resultado = 0; 
				
				switch (opcao) {
				case 1:
					resultado = primeiroNumero + segundoNumero;		
					System.out.println("Soma: " + resultado);
					
					break;

				case 2:
					resultado = primeiroNumero - segundoNumero;
					System.out.println("Subtracao: " + resultado);
					
					break;

				case 3:
					resultado = primeiroNumero * segundoNumero;
					System.out.println("Multiplicacao: " + resultado);
					
					break;

				case 4:
					if(segundoNumero != 0) {
						resultado = primeiroNumero / segundoNumero;
						System.out.println("Divisao: " + resultado);
					} else {
						System.out.println("Não é possível dividir por zero!");
					}
					
					break;

				case 5:
					if(segundoNumero != 0) {
						resultado = primeiroNumero % segundoNumero;
						System.out.println("Resto: " + resultado);
					} else {
						System.out.println("Não é possível dividir por zero!");
					}
					
					break;

				default:
					break;
				}

			} else if(opcao == 9) {
				System.out.println("Sayy Goodbye");
			} else {
				System.out.println("Opção inválida!!!");
			}
			
		} while (opcao != 9);

		scanner.close();
	}
}