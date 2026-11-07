package br.edu.infnet.integracao_api.service;

public interface HistoricoInscricoes {

	void registrar(String descricao);
	
	int obterQuantidade();
}
