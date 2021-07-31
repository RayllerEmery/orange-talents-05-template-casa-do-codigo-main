package me.rayll.autor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String email;

	@CreationTimestamp
	private LocalDateTime instante;
	
	private String descricao;

	@Deprecated
	private Autor() {}
	
	public Autor(String nome, String email, String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public AutorDTO toDTO() {
		return new AutorDTO(this.nome, this.email, this.instante, this.descricao);
	}
	
	public Long getId() {
		return this.id;
	}
}
