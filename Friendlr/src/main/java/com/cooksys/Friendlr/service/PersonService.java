package com.cooksys.Friendlr.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.Friendlr.entity.Person;

@Service
public class PersonService {
	
	private Set<Person> people;
	private static Long index;
	
	public PersonService()
	{
		people = new HashSet<Person>();
		setIndex((long) 1);
	}

	public Set<Person> getPeople()
	{
		return people;
	}
	
	public void setPeople(Set<Person> people)
	{
		this.people = people;
	}

	public static Long getIndex() {
		return index;
	}

	public static void setIndex(Long index) {
		PersonService.index = index;
	}
	
	public Person getPerson(Long id)
	{
		for(Person p : people)
		{
			if(p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	public Person createPerson(Person p)
	{
		p.setId(index++);
		people.add(p);
		return p;
	}
	
	public Person updatePerson(Long id,Person p)
	{
		for(Person temp : people)
		{
			if(temp.getId().equals(id))
			{
				temp.setFirstName(p.getFirstName());
				temp.setLastName(p.getLastName());
				return temp;
			}
		}
		return null;
	}

	public Person removePerson(Long id) {
		
		Person removed;
		
		for(Person temp : people)
		{
			if(temp.getId().equals(id))
			{
				removed = temp;
				people.remove(temp);
				return removed;
			}
		}
		
		return null;
	}
}
