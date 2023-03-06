package br.com.fiap.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.fiap.model.ProdutoModel;

public class ProdutoRepository {
	private static Map<Long, ProdutoModel> produtos;

	public ProdutoRepository() {
		produtos = new HashMap<Long, ProdutoModel>();

		produtos.put(1L, new ProdutoModel(1L, "Água", "AD32", "Garrafa plástica", 2.50, "500ml"));
		produtos.put(2L, new ProdutoModel(2L, "Máscara", "CD42", "Caixa de 50 máscaras descartáveis", 50.00, "Branca"));
		produtos.put(3L, new ProdutoModel(3L, "Teclado", "T1G2", "Teclado mecânico", 450.75, "Yellow switches"));
	}

	public ArrayList<ProdutoModel> findAll() {
		return new ArrayList<>(produtos.values());
	}

	public ProdutoModel findById(long id) {
		return produtos.get(id);
	}

	public void save(ProdutoModel produto) {
		Long newID = (long) (produtos.size() + 1);
		produto.setId(newID);
		produtos.put(newID, produto);
	}

	public void delete(long id) {
		produtos.remove(id);
	}

	public void edit(long id, ProdutoModel modelo) {
		produtos.put(modelo.getId(), modelo);
	}
}
