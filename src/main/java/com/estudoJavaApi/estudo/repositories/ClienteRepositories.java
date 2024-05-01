package com.estudoJavaApi.estudo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudoJavaApi.estudo.models.Cliente;

@Repository
public interface ClienteRepositories extends JpaRepository<Cliente, UUID>  {

}
