package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	private ProdutoRepository repository = new ProdutoRepository();

	@GetMapping("/produtos")
	public String findAll(Model model) {
		model.addAttribute("produtos", repository.findAll());
		return "produtos";
	}

	@GetMapping("/produto/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		model.addAttribute("produto", repository.findById(id));
		return "produto-detalhe";
	}

	@GetMapping("/produto/new")
	public String openSave() {
		return "produto-novo";
	}

	@PostMapping("/produto/new")
	public String save(ProdutoModel produto) {
		repository.save(produto);
		return "produto-novo-sucesso";
	}

//	@GetMapping("/produto/delete/{id}")
//	public String delete(@PathVariable("id") long id) {
//		repository.delete(id);
//		return "redirect:/produtos";
//	}

	@DeleteMapping("/produto/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		repository.delete(id);
		model.addAttribute("produtos", repository.findAll());
		return "redirect:/produtos";
	}

	@GetMapping("/produto/edit/{id}")
	public String openUpdate(@PathVariable("id") long id, Model model) {

		ProdutoModel produto = repository.findById(id);

		if (produto == null) {
			model.addAttribute("produtos", repository.findAll());
			return "produtos";
		}

		model.addAttribute("produto", repository.findById(id));
		return "produto-editar";
	}

	@PostMapping("/produto/update")
	public String update(@RequestParam("identificador") long id, @RequestParam("nome") String nome,
			@RequestParam("sku") String sku, @RequestParam("descricao") String descricao,
			@RequestParam("preco") double preco, @RequestParam("caracteristicas") String caracteristicas, Model model) {

		ProdutoModel modelo = new ProdutoModel(id, nome, sku, descricao, preco, caracteristicas);

		repository.edit(id, modelo);

		model.addAttribute("produtos", repository.findAll());
		return "produtos";
	}

}
