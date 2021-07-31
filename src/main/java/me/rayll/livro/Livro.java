package me.rayll.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;

import me.rayll.autor.Autor;
import me.rayll.categoria.Categoria;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String titulo;
	
	@Column(nullable = false)
	private String resumo;
	
	@Lob
	private String sumario;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	private Integer paginas;
	
	@Column(nullable = false)
	private String isbn;
	
	@Future
	private LocalDate lancamento;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Autor autor;

	public Livro(String titulo, String resumo, String sumario, BigDecimal preco, Integer paginas, String isbn,
			@Future LocalDate lancamento, Categoria categoria, Autor autor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.paginas = paginas;
		this.isbn = isbn;
		this.lancamento = lancamento;
		this.categoria = categoria;
		this.autor = autor;
	}

	@Deprecated
	private Livro() {}
	
	public LivroDTO toDto() {
		return new LivroDTO
				(id, titulo, resumo, sumario, preco, paginas, isbn, lancamento, categoria.getId(), autor.getId());
	}
	
}
