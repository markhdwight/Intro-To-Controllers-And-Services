package com.cooksys.Friendlr.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.Friendlr.dto.PersonDto;
import com.cooksys.Friendlr.entity.Person;
import com.cooksys.Friendlr.mapper.PersonMapper;

@Service
public class PersonService {
	
	private Set<Person> people;
	private static Long index;
	
	private PersonMapper mapper;
	
	public PersonService(PersonMapper mapper)
	{
		people = new HashSet<Person>();
		setIndex((long) 1);
		this.mapper = mapper;
	}

	public Set<PersonDto> getPeople()
	{
		return people.stream().map(mapper::toPersonDto).collect(Collectors.toSet());
	}
	
	public void setPeople(Set<PersonDto> people)
	{
		this.people = people.stream().map(mapper::toPerson).collect(Collectors.toSet());
	}

	public static Long getIndex() {
		return index;
	}

	public static void setIndex(Long index) {
		PersonService.index = index;
	}
	
	public PersonDto getPerson(Long id)
	{
		for(Person p : people)
		{
			if(p.getId().equals(id))
				return mapper.toPersonDto(p);
		}
		return null;
	}
	
	public PersonDto createPerson(PersonDto p)
	{
		p.setId(index++);
		people.add(mapper.toPerson(p));
		return p;
	}
	
	public PersonDto updatePerson(Long id,PersonDto p)
	{
		for(Person temp : people)
		{
			if(temp.getId().equals(id))
			{
				temp.setFirstName(p.getFirstName());
				temp.setLastName(p.getLastName());
				return mapper.toPersonDto(temp);
			}
		}
		return null;
	}

	public PersonDto removePerson(Long id) {
		
		PersonDto removed = null;
		
		for(Person temp : people)
		{
			temp.removeFriend(temp);
			
			if(temp.getId().equals(id))
			{				
				removed = mapper.toPersonDto(temp);
				people.remove(temp);
			}
		}
		
		return removed;
	}
	
	public void addFriend(Long id,Long friendId)
	{
		Person person = mapper.toPerson(getPerson(id));
		Person friend = mapper.toPerson(getPerson(friendId));

		if(!(person == null || friend == null))
		{
			person.addFriend(friend);
			//friend.addFriend(person);		//Assuming that the friendship is reciprocated			
		}
	}
}
