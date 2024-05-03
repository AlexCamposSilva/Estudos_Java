package com.estudoJavaApi.estudo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		return ResponseEntity.status(HttpStatus.OK).body(ListCliente);
	}
	
	@GetMapping("/clientes/{id}")
	private ResponseEntity<Cliente> findById(@PathVariable (value = "id") UUID id){
		Optional<Cliente> obj = clienteRepositories.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj.get());
	}
		
	@PostMapping("/clientes")
	private ResponseEntity<Cliente> salvar (@RequestBody Cliente cliente){
	
		var newCliente = new Cliente();
		newCliente.setName(cliente.getName());
		newCliente.setCpf(cliente.getCpf());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepositories.save(newCliente));	
	}


	@PutMapping("/clientes/{id}")
	private ResponseEntity<Cliente> atualizar(@PathVariable (value = "id") UUID id,
										      @RequestBody Cliente cliente){
		Optional<Cliente> obj = clienteRepositories.findById(id);
		var newcliente = obj.get();
		newcliente.setName(cliente.getName());
		newcliente.setCpf(cliente.getCpf());
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepositories.save(newcliente));
	}
	
	@DeleteMapping("/clientes/{id}")
	private ResponseEntity<Cliente>deletar (@PathVariable(value ="id") UUID id){
		Optional<Cliente> obj = clienteRepositories.findById(id);
		clienteRepositories.delete(obj.get());
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
		
	}

}
