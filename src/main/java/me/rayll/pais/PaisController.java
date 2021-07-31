package me.rayll.pais;

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
@RequestMapping("/casadocodigo/v1/pais")
public class PaisController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public PaisDTO cadastroNovoPais(@RequestBody @Valid PaisDTO form) {
		Pais novoPais = form.toModel();
		manager.persist(novoPais);
		return novoPais.toDTO();
	}
	
}
