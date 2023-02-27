package br.com.fiap.model;

public class ProdutoModelo {

	private int id;
	private String nome;
	private String sku;
	private String descricao;
	private double preco;
	private String caracteristica;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public ProdutoModelo(int id, String nome, String sku, String descricao, double preco, String caracteristica) {
		super();
		this.id = id;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.preco = preco;
		this.caracteristica = caracteristica;
	}

	public ProdutoModelo() {
		super();
	}

	@Override
	public String toString() {
		return "ProdutoModelo [id=" + id + ", nome=" + nome + ", sku=" + sku + ", descricao=" + descricao + ", preco="
				+ preco + ", caracteristica=" + caracteristica + "]";
	}

}
