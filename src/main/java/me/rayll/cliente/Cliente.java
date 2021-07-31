package me.rayll.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import me.rayll.estado.Estado;
import me.rayll.pais.Pais;

@Entity
public class Cliente {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String sobrenome;
	@Column(nullable = false)
	private String documento;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private String complemento;
	@Column(nullable = false)
	private String cidade;
	@ManyToOne @JoinColumn(nullable = false)
	private Pais pais;
	@ManyToOne
	private Estado estado;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String cep;
	
	@Deprecated
	private Cliente() {}
	
	public Cliente(String email, String nome, String sobrenome, String documento, String endereco, String complemento,
			String cidade, Pais pais, Estado estado, String telefone, String cep) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.pais = pais;
		this.estado = estado;
		this.telefone = telefone;
		this.cep = cep;
	}

	public ClienteDTO toDTO() {
		return new ClienteDTO(
				this.id,
				this.email,
				this.nome,
				this.sobrenome,
				this.documento,
				this.endereco,
				this.complemento,
				this.cidade,
				this.pais.getId(),
				this.estado==null ? null : this.estado.getId(),
				this.telefone,
				this.cep
				);
		
	}
	
}
