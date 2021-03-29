package com.fabriciolondero.RestAPISwagger.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabriciolondero.RestAPISwagger.Models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>
{
	
}
