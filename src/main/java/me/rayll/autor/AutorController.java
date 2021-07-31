package me.rayll.autor;

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
@RequestMapping("/casadocodigo/v1/autor")
public class AutorController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public AutorDTO CadastroNovoAutor(@Valid @RequestBody AutorDTO form) {
		Autor autorParaSalvar = form.toModel();
		manager.persist(autorParaSalvar);
		return autorParaSalvar.toDTO();
	}
}
