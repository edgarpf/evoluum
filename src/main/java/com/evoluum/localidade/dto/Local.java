package com.evoluum.localidade.dto;

public class Local {
	private int idEstado;
	private String siglaEstado;
	private String regiaoNome;
	private String nomeCidade;
	private String nomeMesorregiao;
	
	public Local(int idEstado, String siglaEstado, String regiaoNome, String nomeCidade, String nomeMesorregiao) {
		this.idEstado = idEstado;
		this.siglaEstado = siglaEstado;
		this.regiaoNome = regiaoNome;
		this.nomeCidade = nomeCidade;
		this.nomeMesorregiao = nomeMesorregiao;
	}

	public String getNomeFormatado() {
		return nomeCidade + "/" + siglaEstado;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public String getRegiaoNome() {
		return regiaoNome;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}
}
