package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Table(name = "TB_LOJA")
@Entity
public class LojaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOJA")
	private long idLoja;

	@Column(name = "NOME_LOJA")
	private String nomeLoja;

	@ManyToMany(mappedBy = "lojas")
	private List<ProdutoModel> produtos;

	public LojaModel() {
		super();
	}

	public LojaModel(long idLoja, String nomeLoja, List<ProdutoModel> produtos) {
		super();
		this.idLoja = idLoja;
		this.nomeLoja = nomeLoja;
		this.produtos = produtos;
	}

	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

	public long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(long idLoja) {
		this.idLoja = idLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	@Override
	public String toString() {
		return "LojaModel [idLoja=" + idLoja + ", nomeLoja=" + nomeLoja + ", produtos=" + produtos + "]";
	}

}
