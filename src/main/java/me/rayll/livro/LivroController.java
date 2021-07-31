package me.rayll.livro;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/casadocodigo/v1/livro")
public class LivroController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public LivroDTO CadastroNovoLivro(@RequestBody @Valid LivroDTO form) {
		Livro livro = form.toModel(manager);
		manager.persist(livro);
		return livro.toDto();
	}
	
	
	@GetMapping
	@Transactional
	public List<LivroDTO> buscarLivros() {

		Query query = manager.createQuery("select l from Livro l");
		List<Livro> listaLivros = query.getResultList();
		List<LivroDTO> dtos = listaLivros.stream().map(livro -> livro.toDto()).collect(Collectors.toList());
		return dtos;

	}
	
	@GetMapping("/{id}")
	@Transactional
	public LivroDTO buscarLivroUnico(@PathVariable Long id) {
		Livro livroBuscado = manager.find(Livro.class, id);
		if(livroBuscado == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!");
		}
		return livroBuscado.toDto();
	}
	
	
}
