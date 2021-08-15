package com.cliente.entrega.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;	// Uma inteface para fazer operações com as entidades que serão refletidas depois no DB como um CRUD
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.entrega.model.Cliente;

@RestController
public class ClienteController {
	
	@PersistenceContext  //Serve para injetar um EntityManager na variável abaixo
	private EntityManager entityM;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return entityM.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}
}
