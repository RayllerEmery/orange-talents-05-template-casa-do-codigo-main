package me.rayll.autor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import me.rayll.validacoes.valorunico.ValorUnico;


public class AutorDTO {

	@NotEmpty
	private String nome;

	@NotEmpty
	@Email
	@ValorUnico(domainClass = Autor.class, fieldName = "email")
	private String email;
	
	private String instante;
	
	@NotNull @Length(max = 400)
	private String descricao; 

	@Deprecated
	private AutorDTO() {}
	
	public AutorDTO(
			@NotEmpty String nome, 
			@NotEmpty @Email String email,
			LocalDateTime instante,
			@NotNull @Length(max = 400) String descricao) {
		
		this.nome = nome;
		this.email = email;
		this.instante = dataAmigavel(instante);
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getInstante() {
		return instante;
	}

	public String getDescricao() {
		return descricao;
	}
	
	private String dataAmigavel(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
	}
	
	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}

	@Override
	public String toString() {
		return "AutorDTO [nome=" + nome + ", email=" + email + ", instante=" + instante + ", descricao=" + descricao
				+ "]";
	}
	
	
}
