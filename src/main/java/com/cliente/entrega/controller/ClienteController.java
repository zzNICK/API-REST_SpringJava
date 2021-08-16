package com.cliente.entrega.controller;


import java.util.List;

import javax.validation.Valid;

// import javax.persistence.EntityManager;	// Uma inteface para fazer operações com as entidades que serão refletidas depois no DB como um CRUD


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.entrega.model.Cliente;
import com.cliente.entrega.repository.ClienteRepository;
import com.cliente.entrega.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired // Anotações para dizer que queremos implentar uma instancia que esta sendo gerenciada pelo Spring
	private ClienteRepository clienteRepository; // Variável de instancia 
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
		
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable @Valid Long clienteId) {    // Valid, serve para validar na hora que executa o metodo/ Na entrada do metodo
		return clienteRepository.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // Modificando Status para 201 como CRIADO
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable  @Valid Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		//clienteRepository.deleteById(clienteId);
		clienteService.excluir(clienteId);
		return ResponseEntity.noContent().build(); // Codigo http 204
	}
}
