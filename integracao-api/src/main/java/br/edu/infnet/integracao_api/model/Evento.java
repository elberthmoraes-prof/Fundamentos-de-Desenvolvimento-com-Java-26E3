
package br.edu.infnet.integracao_api.model;

public class Evento {

    private String nome;
    private String local;
    private int capacidade;
    private int qtdeInscritos;

    public Evento(String nome, String local, int capacidade) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome do evento é obrigatório.");
        }

        if (capacidade <= 0) {
            throw new IllegalArgumentException(
                "A capacidade deve ser maior que zero.");
        }

        this.nome = nome;
        this.local = local;
        this.capacidade = capacidade;
    }

	public int getVagasDisponiveis() {
		return capacidade - qtdeInscritos;
	}

    public boolean temVaga() {
        return getVagasDisponiveis() > 0;
    }

    public boolean inscrever() {

        if (!temVaga()) {
            return false;
        }

        qtdeInscritos++;
        return true;
    }

    public int getQtdeInscritos() {
        return qtdeInscritos;
    }

    public int getCapacidade() {
        return capacidade;
    }

    @Override
    public String toString() {
        return String.format(
            "nome=%s; local=%s; capacidade=%d; qtdeInscritos=%d",
            nome,
            local,
            capacidade,
            qtdeInscritos
        );
    }

	public double getPercentualOcupacao() {
		
		double percentual = 100;

		return (qtdeInscritos * percentual) / capacidade;
	}

	public String getEstadoOcupacao() {

		if(qtdeInscritos == 0) {
			return "VAZIO";
		}
		
		if(capacidade == qtdeInscritos) {
			return "LOTADO";
		}

		return "PARCIALMENTE_OCUPADO";
	}
}