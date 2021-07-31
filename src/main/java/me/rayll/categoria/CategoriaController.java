package me.rayll.categoria;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/casadocodigo/v1/categoria")
public class CategoriaController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public CategoriaDTO cadastroNovaCategoria(@Valid @RequestBody CategoriaDTO form) {
		Categoria categoriaParaSalvar = form.toModel();
		manager.persist(categoriaParaSalvar);
		return categoriaParaSalvar.toDTO();
	}
}
