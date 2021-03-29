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

@RestController
@RequestMapping("/pessoas")
public class PessoaController 
{
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> getPessoas()
	{
		return pessoaRepository.findAll();
	}
	
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa)
	{
		return pessoaRepository.save(pessoa);
	}
	
	@DeleteMapping("/{id}")
	void deletePessoa(@PathVariable Long id) 
	{
		pessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	Pessoa updatePessoa(@RequestBody Pessoa novaPessoa, @PathVariable Long id) 
	{
	    Pessoa p = pessoaRepository.getOne(id);
		
	    p.setNome(novaPessoa.getNome());
		
	    return pessoaRepository.save(p);
	 }
}
