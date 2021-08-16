package com.cliente.entrega.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cliente.entrega.model.Cliente;

@Repository // Anotação que define esse repository é ainda um componente do Spring
public interface ClienteRepository extends JpaRepository<Cliente, Long>{ //Add funcionalidades para consulta/gerencia a entity Cliente
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
	Stream<Cliente> findByEmail(String email);
	//Optional<Cliente> findByEmail(String email);
}
