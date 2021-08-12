package com.cliente.entrega.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.entrega.model.Cliente;

@RestController
public class ClienteController {
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(2L);
		cliente1.setNome("Nicolas");
		cliente1.setEmail("sdksld@gmail.com");
		cliente1.setTelefone("99895644545");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(5L);
		cliente2.setNome("Lucas");
		cliente2.setEmail("lelelsd@gmail.com");
		cliente2.setTelefone("4848415");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
