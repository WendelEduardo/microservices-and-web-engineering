package br.com.fiap.model;

public class ProdutoModel {
	private long id;
	private String nome;
	private String sku;
	private String descricao;
	private double preco;
	private String caracteristicas;

	public ProdutoModel(long id, String nome, String sku, String descricao, double preco, String caracteristicas) {
		super();
		this.id = id;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.preco = preco;
		this.caracteristicas = caracteristicas;
	}

	public ProdutoModel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Override
	public String toString() {
		return "ProdutoModel [id=" + id + ", nome=" + nome + ", sku=" + sku + ", descricao=" + descricao + ", preco="
				+ preco + ", caracteristicas=" + caracteristicas + "]";
	}
	
	
}
