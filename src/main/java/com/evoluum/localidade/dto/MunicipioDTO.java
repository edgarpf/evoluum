package com.evoluum.localidade.dto;

public class MunicipioDTO {
	private String nome;
	private Microrregiao microrregiao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Microrregiao getMicrorregiao() {
		return microrregiao;
	}
	public void setMicrorregiao(Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}
}
