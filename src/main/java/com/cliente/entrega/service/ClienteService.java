package com.cliente.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cliente.entrega.exception.ServiceException;
import com.cliente.entrega.model.Cliente;
import com.cliente.entrega.repository.ClienteRepository;


@Service //Torna a classe um componente Spring que representa os serviços que vão ser executados - Colocando regras de negócios
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional  // Deve ser executado dentro de uma transação / Se algo que estiver executando nesse metodo der errado, todas as operações devem ser descatadas - Ou tudo ou Nada
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).distinct()
				.allMatch(clienteExiste -> !clienteExiste.equals(cliente));
		if(emailEmUso) {
			throw new ServiceException("Esse e-mail já foi cadastrado");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
