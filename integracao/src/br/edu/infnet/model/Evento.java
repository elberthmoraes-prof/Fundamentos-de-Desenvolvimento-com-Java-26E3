package br.edu.infnet.model;

public class Evento {

	private String nome;
	private String local;
	private int capacidade;
	private int qtdeInscritos;
	
	public Evento(String nome, String local, int capacidade) {

		if(nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome do evento é obrigatório.");
		}
		
		if(capacidade <= 0) {
			throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
		}
		
		this.nome = nome;
		this.local = local;
		this.capacidade = capacidade;
	}
	
	public boolean temVaga(){

		return qtdeInscritos < capacidade;
	}
	
	public void inscrever(){
		if(temVaga()) {
			qtdeInscritos++;
		}
	}
	
	@Override
	public String toString() {

		return String.format("nome=%s; local=%s; capacidade=%d; qtdeInscritos=%d", 
				nome, 
				local, 
				capacidade, 
				qtdeInscritos
			);
	}

	public int getQtdeInscritos() {
		return qtdeInscritos;
	}
}
