package br.edu.infnet.service;

public interface HistoricoInscricoes {

	void registrar(String descricao);
	
	int obterQuantidade();
}
