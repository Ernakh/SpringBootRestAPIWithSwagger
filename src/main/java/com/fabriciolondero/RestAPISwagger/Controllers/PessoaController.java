package com.fabriciolondero.RestAPISwagger.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fabriciolondero.RestAPISwagger.Models.Pessoa;
import com.fabriciolondero.RestAPISwagger.Repositories.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Swagger2RestController", 
	description = "REST APIs relacionada ao cadastro de PESSOAS")
@RestController
@RequestMapping("/pessoas")
public class PessoaController 
{
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@ApiOperation(value = "Retorna as pessoas cadastradas", response = Iterable.class, tags = "getPessoas")
	@GetMapping
	public List<Pessoa> getPessoas()
	{
		return pessoaRepository.findAll();
	}
	
	@ApiOperation(value = "Retorna a pessoa com o ID informado", response = Iterable.class, tags = "getPessoas")
	@GetMapping("/{id}")
	Pessoa getPessoa(@PathVariable Long id) 
	{
		try
		{
			return pessoaRepository.getOne(id);
		}
		catch (Exception e) 
		{
			return null;
		}
	}

	@ApiOperation(value = "Cadastra uma nova pessoa e recebe-a como retorno", response = Iterable.class, tags = "adicionar")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa)
	{
		return pessoaRepository.save(pessoa);
	}
	
	@ApiOperation(value = "Exclui a pessoa do ID informado", response = Iterable.class, tags = "deletar")
	@DeleteMapping("/{id}")
	void deletePessoa(@PathVariable Long id) 
	{
		pessoaRepository.deleteById(id);
	}
	
	@ApiOperation(value = "Altera uma pessoa, recebendo uma pessoa e o ID por parametro. Retorna a nova pessoa.", response = Iterable.class, tags = "alterar")
	@PutMapping("/{id}")
	Pessoa updatePessoa(@RequestBody Pessoa novaPessoa, @PathVariable Long id) 
	{
	    Pessoa p = pessoaRepository.getOne(id);
		
	    p.setNome(novaPessoa.getNome());
		
	    return pessoaRepository.save(p);
	 }
}
