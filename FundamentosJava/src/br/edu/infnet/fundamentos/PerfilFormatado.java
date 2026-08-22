package br.edu.infnet.fundamentos;

public class PerfilFormatado {
	
	public static void main(String[] args){

		Profissional profissional = new Profissional();
		
		profissional.nome = "Elberth Moraes";
		profissional.profissao = "Programador";
		profissional.idade = 47;
		profissional.estado = "Rio de Janeiro";
		profissional.altura = 1.8;
		profissional.professor = true;
		profissional.cidade = "Marica";
		profissional.salario = 99.99;
		profissional.empresa = "Dataprev";

		profissional.imprimir();
	}
}