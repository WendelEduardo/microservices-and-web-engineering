package br.com.fiap.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

	@RequestMapping(value = { "/hello", "/" }, method = RequestMethod.GET)
	public String falaWendel(Model model) {

		ArrayList<String> lista = new ArrayList<>();
		lista.add("Retorno 1");
		lista.add("");
		lista.add("Retorno 2");
		lista.add("Retorno 3");

		model.addAttribute("hello", lista);

		return "hello";
	}

//	@RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
//	public String hello

}
