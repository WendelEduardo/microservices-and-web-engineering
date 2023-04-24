package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_categoria")
public class CategoriaModel {

	@Id
	@Column(name="ID_CATEGORIA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategoria; 
	@Column(name="NOME_CATEGORIA")
	private String nomeCategoria;
	
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<ProdutoModel> produtos;
	
	public CategoriaModel(long idCategoria, String nomeCategoria,List<ProdutoModel> produtos) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
		this.produtos=produtos;
	}
	public CategoriaModel() {
		super();
		
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
	@NotNull (message = "Nome é obrigatorio!")
	@Size(min = 2, max =50, message = "Nome deve ser entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
