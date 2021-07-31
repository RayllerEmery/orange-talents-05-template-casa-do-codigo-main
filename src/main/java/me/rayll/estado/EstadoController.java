package me.rayll.estado;

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
@RequestMapping("/casadocodigo/v1/estado")
public class EstadoController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	@Transactional
	public EstadoDTO cadastroNovoEstado(@RequestBody @Valid EstadoDTO form) {
		Estado novoEstado = form.toModel(manager);
		manager.persist(novoEstado);
		return novoEstado.toDTO();
	}
}
