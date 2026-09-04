package br.edu.infnet.fundamentos.testes;

import br.edu.infnet.fundamentos.model.Filme;

public class TestaFilme {

	public static void main(String[] args) {
		
		Filme filme = new Filme("não sei", 2000);
		filme.avaliar(10);
		filme.impressao();
		
		//
		
		Filme filmeElberth = new Filme(null, 0);
		filmeElberth.avaliar(10);
		filmeElberth.exibirResumo();
		
		//
		
		Filme filmeMatrix = new Filme("Matrix", 2000);
		filmeMatrix.avaliar(9.0);
		filmeMatrix.impressao();						

		//
		
		Filme filmeInterestelar = new Filme("Interestelar", 2014);
		filmeInterestelar.avaliar(9.5);
		filmeInterestelar.exibirResumo();

		//
		
		Filme outroFilme = new Filme("Interestelar", 2014);
		outroFilme.avaliar(-20);
		outroFilme.impressao();
	}
}