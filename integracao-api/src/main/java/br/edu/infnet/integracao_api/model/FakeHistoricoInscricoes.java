package br.edu.infnet.integracao_api.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.integracao_api.service.HistoricoInscricoes;

public class FakeHistoricoInscricoes implements HistoricoInscricoes {
	
	private final List<String> registros = new ArrayList<String>();

	@Override
	public void registrar(String descricao) {
	
		registros.add(descricao);
	}

	@Override
	public int obterQuantidade() {
		return registros.size();
	}
	
	public List<String> consultarRegistros(){
		return new ArrayList<String>(registros);
	}

}
