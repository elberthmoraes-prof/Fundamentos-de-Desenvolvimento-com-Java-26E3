package br.edu.infnet.fundamentos;

public class TestaFilme {

	public static void main(String[] args) {
		
		Filme filmeElberth = new Filme();
		filmeElberth.impressao();

		//
		
		Filme filmeMatrix = new Filme();
		filmeMatrix.titulo = "Matrix";
		filmeMatrix.ano = 1999;
		filmeMatrix.avaliacao = 9.0;
		filmeMatrix.impressao();

		//
		
		Filme filmeInterestelar = new Filme();
		filmeInterestelar.titulo = "Interestelar";
		filmeInterestelar.ano = 2014;
		filmeInterestelar.avaliacao = 9.5;
		filmeInterestelar.impressao();
	}
}
