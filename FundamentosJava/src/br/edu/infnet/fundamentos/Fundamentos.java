package br.edu.infnet.fundamentos;

class Fundamentos {
	public static void main(String[] args){
		int[] idades = {10,20,30,40,50,60,70,80,90,100,110};		
		float[] salarios = {100,200,300,400,500,600,700,800,900,1000,1100};
			
		
//		for(String nome : args) {
//			System.out.println(nome);
//		}
//		for(int idade : idades) {
//			System.out.println(idade);
//		}		
//		for(float salario : salarios) {
//			System.out.println(salario);
//		}

		for(int indice = 0; indice < args.length; indice++) {
			System.out.println("O jogador "+ args[indice] +", tem "+ idades[indice] +" anos e recebe "+ salarios[indice] +" mil reais");
		}
	}
}