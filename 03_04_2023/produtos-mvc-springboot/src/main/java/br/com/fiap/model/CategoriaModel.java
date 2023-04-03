package br.com.fiap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "tb_categoria")
@Entity
public class CategoriaModel {

	@Id
	private Long idCategoria;
	private String nomeCategoria;
	
	
	public CategoriaModel() {
		super();
	}
	
	public CategoriaModel(long idCategoria, String nomeCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	@NotNull(message = "Nome é obrigatorio")
	@Size(min = 2, max = 50, message = "Nome deve ser entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	@Override
	public String toString() {
		return "CategoriaModel [idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + "]";
	}
		
}
