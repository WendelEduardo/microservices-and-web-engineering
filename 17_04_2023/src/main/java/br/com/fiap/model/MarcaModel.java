package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_MARCA")
public class MarcaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_marca;
	private String nome_marca;

	@OneToMany(mappedBy = "marcaModel")
	private List<ProdutoModel> produtos;

	public MarcaModel(long id_marca, String nome_marca, List<ProdutoModel> produtos) {
		super();
		this.id_marca = id_marca;
		this.nome_marca = nome_marca;
		this.produtos = produtos;
	}

	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

	public MarcaModel() {

	}

	public MarcaModel(long id_marca, String nome_marca) {
		super();
		this.id_marca = id_marca;
		this.nome_marca = nome_marca;
	}

	public long getId_marca() {
		return id_marca;
	}

	public void setId_marca(long id_marca) {
		this.id_marca = id_marca;
	}

	public String getNome_marca() {
		return nome_marca;
	}

	public void setNome_marca(String nome_marca) {
		this.nome_marca = nome_marca;
	}

	@Override
	public String toString() {
		return "MarcaModel [id_marca=" + id_marca + ", nome_marca=" + nome_marca + "]";
	}
}
