package me.rayll.cliente;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import me.rayll.estado.Estado;
import me.rayll.pais.Pais;
import me.rayll.validacoes.cpfcnpj.CPFouCNPJ;
import me.rayll.validacoes.mes.MesVazioOuNao;
import me.rayll.validacoes.valorunico.ValorUnico;

@MesVazioOuNao
public class ClienteDTO {

	@JsonIgnore
	private Long id;
	@Email @ValorUnico(domainClass = Cliente.class, fieldName = "email")
	private String email;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String sobrenome;
	@CPFouCNPJ @ValorUnico(domainClass = Cliente.class, fieldName = "documento")
	private String documento;
	@NotEmpty
	private String endereco;
	@NotEmpty
	private String complemento;
	@NotEmpty
	private String cidade;
	@NotNull
	private Long idPais;
	
	private Long idEstado;
	@NotEmpty @Length(max = 11, min = 11)
	private String telefone;
	@NotEmpty
	private String cep;
	
	public ClienteDTO(Long id, @Email String email, @NotEmpty String nome, @NotEmpty String sobrenome, String documento,
			@NotEmpty String endereco, @NotEmpty String complemento, @NotEmpty String cidade, @NotEmpty Long idPais,
			Long idEstado, @NotEmpty @Length(max = 11, min = 11) String telefone, @NotEmpty String cep) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
	}
	
	
	@Deprecated
	private ClienteDTO() {}
	
	public Cliente toModel(EntityManager manager) {
		return new Cliente(
					this.email,
					this.nome,
					this.sobrenome,
					this.documento,
					this.endereco,
					this.complemento,
					this.cidade,
					manager.find(Pais.class, this.idPais),
					this.idEstado == null ? null : manager.find(Estado.class, this.idEstado),
					this.telefone,
					this.cep
				);
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public Long getIdPais() {
		return this.idPais;
	}
	
	public Long getIdEstado() {
		return this.idEstado;
	}
}
