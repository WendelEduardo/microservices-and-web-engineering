package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fiap.model.ProdutoModelo;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	ProdutoRepository repository = new ProdutoRepository();

	@RequestMapping("/produtos")
	public String produtos(Model model) {
		model.addAttribute("produtos", repository.findAll());
		return "produtos";
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable("id") int id, Model model) {
		model.addAttribute("produto", repository.findById(id));
		return "produto-detalhe";
	}

	@RequestMapping(value = "/produto/new", method = RequestMethod.GET)
	public String openSave() {
		return "produto-novo";
	}

	@PostMapping("/produto/new")
	public String save(ProdutoModelo produto) {
		repository.save(produto);
		return "produto-novo-sucesso";
	}

//	@DeleteMapping("/produtos/delete/{id}")
//	public String delete(@PathVariable("id") int id) {
//		repository.delete(id);
//		return "produto-novo-sucesso";
//	}

}
