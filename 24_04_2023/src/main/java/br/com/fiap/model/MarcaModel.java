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
@Table(name="tb_marca")
public class MarcaModel {
	
	@Id
	@Column(name="ID_MARCA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMarca;
	@Column(name="NOME_MARCA")
	private String nomeMarca;
	
	@OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
	private List<ProdutoModel> produtos;
	
	public MarcaModel(Long idMarca, String nomeMarca, List<ProdutoModel> produtos) {
		this.idMarca = idMarca;
		this.nomeMarca = nomeMarca;
		this.produtos=produtos;
	}
	
	
	public MarcaModel() {
	}

	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	
	@NotNull (message = "Nome é obrigatorio!")
	@Size(min = 2, max =50, message = "Nome deve ser entre 2 e 50 caracteres")
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}


	public List<ProdutoModel> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

}
