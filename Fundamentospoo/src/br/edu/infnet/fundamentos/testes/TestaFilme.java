package br.edu.infnet.fundamentos.testes;

import br.edu.infnet.fundamentos.model.Filme;

public class TestaFilme {

	public static void main(String[] args) {
		
		Filme filmeElberth = new Filme();
		filmeElberth.avaliar(10);
		filmeElberth.exibirResumo();
		
		//
		
		Filme filmeMatrix = new Filme();
		filmeMatrix.setTitulo("Matrix");				
		filmeMatrix.avaliar(9.0);
		filmeMatrix.setAno(2000);					
		filmeMatrix.impressao();						

		//
		
		Filme filmeInterestelar = new Filme();
		filmeInterestelar.setTitulo("Interestelar");	
		filmeInterestelar.setAno(2014);
		filmeInterestelar.avaliar(9.5);
		filmeInterestelar.exibirResumo();

		//
		
		Filme outroFilme = new Filme();
		outroFilme.setTitulo("Interestelar");			
		outroFilme.setAno(2014);
		outroFilme.avaliar(-20);
		outroFilme.impressao();
	}
}