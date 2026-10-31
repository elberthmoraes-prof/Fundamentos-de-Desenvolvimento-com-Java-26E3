package br.edu.infnet.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.service.HistoricoInscricoes;

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
