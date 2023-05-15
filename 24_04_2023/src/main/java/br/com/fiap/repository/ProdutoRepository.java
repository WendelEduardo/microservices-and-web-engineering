package br.com.fiap.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import br.com.fiap.model.ProdutoModel;
import jakarta.transaction.Transactional;

@Component
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	@Query("SELECT p FROM ProdutoModel p WHERE p.preco > ?1 AND p.categoria.nomeCategoria = ?2")
	List<ProdutoModel> findProductsByCategory(BigDecimal preco, String nomeCategoria);

	@Transactional
	@Modifying
	@Query(value = "UPDATE tb_produto SET nome = ?1, sku = ?2 WHERE id = ?3", nativeQuery = true)
	void updateProductNameAndSku(String nome, String sku, Long id);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_produto WHERE id = ?1", nativeQuery = true)
	void excluirProduto(Long id);

}
