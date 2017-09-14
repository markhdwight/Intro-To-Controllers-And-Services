package com.cooksys.Friendlr.controller;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.Friendlr.service.PersonService;
import com.cooksys.Friendlr.dto.PersonDto;
import com.cooksys.Friendlr.entity.Person;

@RestController
@RequestMapping("person")
public class PersonController 
{
	private PersonService service;
	
	public PersonController(PersonService service)
	{
		this.service = service;
	}
	
	@GetMapping
	public Set<PersonDto> getPeople()
	{
		return service.getPeople();
	}
	
	@GetMapping("{id}")
	public PersonDto getPerson(@PathVariable Long id,HttpServletResponse response)
	{
		PersonDto tempP = service.getPerson(id);
		if(tempP.equals(null))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		else response.setStatus(HttpServletResponse.SC_FOUND);
		return tempP;
	}
	
	@PostMapping
	public PersonDto createPerson(@RequestBody PersonDto p)
	{
		return service.createPerson(p);
	}
	
	@PutMapping("{id}")
	public PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonDto p,HttpServletResponse response){
		PersonDto tempP = service.updatePerson(id, p);
		if(tempP.equals(null))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		else response.setStatus(HttpServletResponse.SC_FOUND);
		return tempP;
	}
	
	@DeleteMapping("{id}")
	public PersonDto removePerson(@PathVariable Long id,HttpServletResponse response)
	{
		PersonDto tempP = service.removePerson(id);
		
		if(tempP.equals(null))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		else response.setStatus(HttpServletResponse.SC_FOUND);
		
		return tempP;
	}
	
	@PatchMapping("{id}/buddies/{friendId}")
	public void addFriend(@PathVariable Long id, @PathVariable Long friendId)
	{	
		service.addFriend(id,friendId);
	}
}
