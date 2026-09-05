package br.edu.infnet.testes;

import br.edu.infnet.model.Evento;

public class EventoTeste {

	public static void main(String[] args) {
		
		final int INSCRICOES = 5;
		
		String[] nomes = {"Café Ágil", "Java Day", "Aula de Fundamentos"};
		int[] capacidades = {3,2,4};
		
		String[] semInscricoes = new String[15];
		
		int x = 0;
		
		for (int i = 0; i < nomes.length; i++) {
			Evento evento = new Evento(nomes[i], "Local", capacidades[i]);

			for(int j = 0; j < INSCRICOES; j++) {

				if(evento.temVaga()) {
					evento.inscrever();
				} else {
					semInscricoes[x++] = evento.toString();
				}
			}
		}
		
		for(String ev : semInscricoes) {
			System.out.println("Evento sem inscrição: " + ev);
		}
		

//		Evento ev1 = new Evento("Café Ágil", "sala 1", 30);
//		
//		if(ev1.temVaga()) {
//			System.out.println("Opa... ainda temos vagas!!!");
//			ev1.inscrever();
//		} else {
//			System.out.println("Game over!!!");
//		}
//
//		Evento ev2 = new Evento("Java Day", "Auditório", 100);
//		ev2.inscrever();
//		
//		Evento ev3 = new Evento("Java Day", "Auditório", -200);
//		ev3.inscrever();
//		
//		System.out.println("Finalizado!!!");
//		
//		System.out.println("EV1 " + ev1);
//		System.out.println("EV2 " + ev2);
//		System.out.println("EV3 " + ev3);
	}
}