package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ProdutoModel;

@Repository
public class ProdutoRepository {
	
	private static final String GET_ALL = "SELECT * FROM TB_PRODUTO";
	private static final String SAVE = "INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO) VALUES (?, ?, ?, ?, ?)";

	;


	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ProdutoRepository() {
		super();
	}
	
	
	public List<ProdutoModel> findAll() {
		List<ProdutoModel> produtos = this.jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<ProdutoModel>(ProdutoModel.class));
		return produtos;
	}

	public ProdutoModel findById(long id) {
		return null;
	}

	public void update(ProdutoModel produtoModel) {
		
	}

	public void save(ProdutoModel produto) {
		this.jdbcTemplate.update(SAVE, produto.getNome(), produto.getSku(), produto.getDescricao(), produto.getCaracteristicas(), produto.getPreco());
	}
	
	public void deleteById(long id) {
		
	}
	
}
