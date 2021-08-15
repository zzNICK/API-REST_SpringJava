package com.cliente.entrega.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;	// Uma inteface para fazer operações com as entidades que serão refletidas depois no DB como um CRUD
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.entrega.model.Cliente;
import com.cliente.entrega.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired // Anotações para dizer que queremos implentar uma instancia que esta sendo gerenciada pelo Spring
	private ClienteRepository clienteRepository; // Variável de instancia 
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
}
