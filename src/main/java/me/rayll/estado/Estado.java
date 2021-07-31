package me.rayll.estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import me.rayll.pais.Pais;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	private Pais pais;
	
	@Deprecated
	private Estado() {}

	public Estado(String nome, Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}
	
	public EstadoDTO toDTO() {
		return new EstadoDTO(this.id, this.nome, this.pais.getId());
	}

	public Long getId() {
		return this.id;
	}
	
}
