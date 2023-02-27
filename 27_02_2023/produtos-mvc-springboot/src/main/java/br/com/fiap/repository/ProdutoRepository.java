package br.com.fiap.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.fiap.model.ProdutoModelo;

public class ProdutoRepository {

	public static Map<Integer, ProdutoModelo> produtos;

	public ProdutoRepository() {
		produtos = new HashMap<Integer, ProdutoModelo>();
		produtos.put(1, new ProdutoModelo(1, "Produto 1", "1234", "Ventilador", 2.5, "Bonitao"));
		produtos.put(2, new ProdutoModelo(2, "Produto 2", "4321", "Computador", 2.500, "Rapido"));
		produtos.put(3, new ProdutoModelo(3, "Produto 3", "765", "Produto", 4.5, "Produtoso"));

	}

	public ArrayList<ProdutoModelo> findAll() {
		return new ArrayList<>(produtos.values());
	}

	public ProdutoModelo findById(int id) {
		return produtos.get(id);
	}

	public void save(ProdutoModelo model) {
		int newID = (int) produtos.size() + 1;
		model.setId(newID);
		produtos.put(newID, model);
	}
	
//	public void delete(int id) {
//		produtos.remove(id);
//	}

}
