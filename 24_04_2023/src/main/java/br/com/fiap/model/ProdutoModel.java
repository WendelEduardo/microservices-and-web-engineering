package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	private String sku;

	private String descricao;

	private double preco;

	private String caracteristicas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria", nullable = false)
	private CategoriaModel categoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_marca", nullable = false)
	private MarcaModel marca;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_PRODUTO_LOJA", joinColumns = @JoinColumn(name = "ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_LOJA", referencedColumnName = "ID_LOJA"))
	private List<LojaModel> lojas;

	public ProdutoModel() {
		super();
	}

	public ProdutoModel(long id, String nome, String sku, String descricao, double preco, String caracteristicas,
			CategoriaModel categoria, MarcaModel marca, List<LojaModel> lojas) {
		super();
		this.id = id;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.preco = preco;
		this.caracteristicas = caracteristicas;
		this.categoria = categoria;
		this.marca = marca;
		this.lojas = lojas;
	}

	public List<LojaModel> getLojas() {
		return lojas;
	}

	public void setLoja(List<LojaModel> lojas) {
		this.lojas = lojas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Size(min = 2, max = 40, message = "Nome deve ter no minimo 2 e no maximo 40 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Size(min = 2, max = 8, message = "SKU deve conter 8 caracteres")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Size(min = 1, max = 200, message = "Descrção deve ter no minimo 1 e no maximo 200 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@DecimalMin(value = "0.1", message = "Preço deve ser acima de 0.0")
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Size(min = 1, max = 200, message = "Caracteristicas deve ter no minimo 1 e no maximo 200 caracteres")
	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public MarcaModel getMarca() {
		return marca;
	}

	public void setMarca(MarcaModel marca) {
		this.marca = marca;
	}


}
