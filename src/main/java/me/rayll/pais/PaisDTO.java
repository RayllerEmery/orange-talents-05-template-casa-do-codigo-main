package me.rayll.pais;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.rayll.validacoes.valorunico.ValorUnico;

public class PaisDTO {
	
	@JsonIgnore
	private Long id;
	
	@NotEmpty @ValorUnico(domainClass = Pais.class, fieldName = "nome")
	private String nome;
	
	@Deprecated
	private PaisDTO() {}
	
	public PaisDTO(Long id, String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Pais toModel() {
		return new Pais(this.nome);
	}
}
