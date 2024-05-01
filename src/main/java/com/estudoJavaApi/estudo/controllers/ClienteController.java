package com.estudoJavaApi.estudo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudoJavaApi.estudo.models.Cliente;
import com.estudoJavaApi.estudo.repositories.ClienteRepositories;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepositories clienteRepositories;
	
	@GetMapping("/clientes")
	private ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> ListCliente = clienteRepositories.findAll();
		return ResponseEntity.ok().body(ListCliente);
	}
	
	@GetMapping("/clientes/{id}")
	private ResponseEntity<Cliente> findById(@PathVariable (value = "id") UUID id){
		Optional<Cliente> obj = clienteRepositories.findById(id);
		return ResponseEntity.ok().body(obj.get());
	}
		
	@PostMapping("/clientes")
	private ResponseEntity<Cliente> salvar (@RequestBody Cliente cliente){
	
		var newCliente = new Cliente();
		newCliente.setName(cliente.getName());
		newCliente.setCpf(cliente.getCpf());
		
		return ResponseEntity.ok().body(clienteRepositories.save(newCliente));	
		}
	
}
