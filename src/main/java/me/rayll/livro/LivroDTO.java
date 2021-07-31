package me.rayll.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import me.rayll.autor.Autor;
import me.rayll.categoria.Categoria;
import me.rayll.validacoes.valorunico.ValorUnico;

public class LivroDTO {
	
	
	private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty @Length(max=500)
	private String resumo;
	
	private String sumario;
	
	@NotNull @DecimalMin(value = "20")
	private BigDecimal preco;
	
	@Min(value=100)
	private Integer paginas;
	
	@ValorUnico(domainClass = Livro.class, fieldName = "isbn")
	private String isbn;
	
	@Future @NotNull @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate lancamento;
	
	@NotNull
	private Long idCategoria;
	
	@NotNull
	private Long idAutor;
	
	public LivroDTO(Long id, @NotEmpty String titulo, @NotEmpty @Length(max = 500) String resumo, String sumario,
			@NotNull @DecimalMin(value = "20") BigDecimal preco, @Min(100) Integer paginas, String isbn, 
			@Future @NotNull LocalDate lancamento,
			@NotNull Long idCategoria, @NotNull Long idAutor) {
		
		this.id = id;
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.paginas = paginas;
		this.isbn = isbn;
		this.lancamento = lancamento;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}
	
	@Deprecated
	private LivroDTO() {}
	
	public Livro toModel(EntityManager manager) {
		
		Autor autor = manager.find(Autor.class, idAutor);
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		
		Assert.state(autor != null, "Não existe esse autor cadastrado! " + idAutor);
		Assert.state(categoria != null, "Não existe essa categoria cadastrada! " + idCategoria);
		
		return new Livro(titulo, resumo, sumario, preco, paginas, isbn, lancamento, categoria, autor);
		
	}
	
	@Override
	public String toString() {
		return "LivroDTO [titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco=" + preco
				+ ", paginas=" + paginas + ", isbn=" + isbn + ", lancamento=" + lancamento + ", idCategoria="
				+ idCategoria + ", idAutor=" + idAutor + "]";
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	
}
