package com.evoluum.localidade.dto;

public class LocalDTO {
	private int idEstado;
	private String siglaEstado;
	private String regiaoNome;
	private String nomeCidade;
	private String nomeMesorregiao;

	public LocalDTO(Estado estado, Municipio municipio) {
		this.idEstado = estado.getId();
		this.siglaEstado = estado.getSigla();
		this.regiaoNome = estado.getRegiao().getNome();
		this.nomeCidade = municipio.getNome();
		this.nomeMesorregiao = municipio.getMicrorregiao().getMesorregiao().getNome();
	}
	
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public void setNomeMesorregiao(String nomeMesorregiao) {
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
