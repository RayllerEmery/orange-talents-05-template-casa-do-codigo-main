package me.rayll.estado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import me.rayll.pais.Pais;
import me.rayll.validacoes.idunico.IdUnico;

public class EstadoDTO {
	
	@JsonIgnore
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotNull @IdUnico(domainClass = Estado.class, fieldName = "id")
	private Long idPais;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdPais() {
		return idPais;
	}

	public EstadoDTO(Long id, @NotEmpty String nome, @NotEmpty Long idPais) {
		this.id = id;
		this.nome = nome;
		this.idPais = idPais;
	}
	
	@Deprecated
	private EstadoDTO() {}
	
	public Estado toModel(EntityManager manager) {
		Pais pais = manager.find(Pais.class, this.idPais);
		validadorEstadoPais(manager, idPais);
		return new Estado(this.nome, pais);
	}
	
	private void validadorEstadoPais(EntityManager manager, Long idPais) {
		String jpql = "select c from Estado c where c.nome = :Pnome and c.pais.id = :PidPais";
		TypedQuery<Estado> query = manager.createQuery(jpql, Estado.class);
		query.setParameter("Pnome", this.nome);
		query.setParameter("PidPais", idPais);
		List<Estado> lista = query.getResultList();
		Assert.state(lista.size() < 1, "Já existe um estado cadastrado para esse país!");
		
	}
}
