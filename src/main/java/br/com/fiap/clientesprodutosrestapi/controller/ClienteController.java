package br.com.fiap.clientesprodutosrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.clientesprodutosrestapi.model.Cliente;
import br.com.fiap.clientesprodutosrestapi.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/cliente")
@Slf4j
@Tag(name = "API de clientes", description = "API que mostra os clientes de produtos")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Mostra todos os clientes")
	public List<Cliente> index() {
		return repository.findAll();
	}

	@PostMapping
	@Operation(summary = "Criar um novo cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!"),
			@ApiResponse(responseCode = "400", description = "Parâmentros insuficientes!"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor!") })
	public ResponseEntity<String> create(@RequestBody Cliente clienteRequest) {

		System.out.println(clienteRequest);

		try {
			if (clienteRequest.getNome() == null || clienteRequest.getTelefone() == null
					|| clienteRequest.getEndereco() == null) {
				return ResponseEntity.badRequest().build();
			}

			Cliente cliente = new Cliente();
			cliente.setNome(clienteRequest.getNome());
			cliente.setTelefone(clienteRequest.getTelefone());
			cliente.setEndereco(clienteRequest.getEndereco());

			repository.save(cliente);

			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id}")
	@Operation(summary = "Consulta cliente por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado!"), })
	public ResponseEntity<Cliente> show(@PathVariable("id") long id) {
		Cliente cliente = repository.findById(id).get();

		if (cliente == null) {
			ResponseEntity.notFound();
		}

		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um cliente através do ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado!"), })
	public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Cliente clienteRequest) {
		try {
			if (repository.findById(id).isEmpty()) {
				ResponseEntity.notFound().build();
			}

			Cliente cliente = new Cliente();
			cliente.setId(id);
			cliente.setNome(clienteRequest.getNome());
			cliente.setEndereco(clienteRequest.getEndereco());
			cliente.setTelefone(clienteRequest.getTelefone());

			repository.save(cliente);

			return ResponseEntity.ok("Cliente atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Atualização parcial de cliente por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado!"), })
	public ResponseEntity<Cliente> change(@PathVariable("id") long id, @RequestBody Cliente clienteRequest) {
		if (repository.findById(id).isEmpty()) {
			ResponseEntity.notFound().build();
		}

		Cliente cliente = repository.findById(id).get();
		cliente.setId(id);

		if (clienteRequest.getNome() != null) {
			cliente.setNome(clienteRequest.getNome());
		}

		if (clienteRequest.getEndereco() != null) {
			cliente.setEndereco(clienteRequest.getEndereco());
		}

		if (clienteRequest.getTelefone() != null) {
			cliente.setTelefone(clienteRequest.getTelefone());
		}

		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um cliente por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado!"), })
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			if (repository.findById(id).get() == null) {
				ResponseEntity.notFound().build();
			}

			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

	}

}
