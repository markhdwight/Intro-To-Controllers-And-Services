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
	
	public Set<PersonDto> getFriends(Long id)
	{
		Set<PersonDto> friends = new HashSet<PersonDto>();
		
		for(Person p : people)
		{
			if(p.getId().equals(id))
			{
				for(Person f : p.getFriends())
				{
					friends.add(mapper.toPersonDto(f));
				}
			}
		}
		return friends;
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
		
		PersonDto removedDto = null;
		Person removed = null;
		
		for(Person temp : people)
		{			
			if(temp.getId().equals(id))
			{				
				for(Person f : temp.getFriends())
				{
					f.removeFriend(temp);
				}
				
				removedDto = mapper.toPersonDto(temp);
				removed = temp;
			}
		}
		
		people.remove(removed);
		return removedDto;
	}
	
	public void addFriend(Long id,Long friendId)
	{
		Person person = null,friend = null;
		
		for(Person temp : people)
		{		
			if(temp.getId().equals(id))
			{				
				person = temp;
			}
			else if(temp.getId().equals(friendId))
			{
				friend = temp;
			}
		}

		if(!(person == null || friend == null))
		{
			person.addFriend(friend);
			friend.addFriend(person);		//Assuming that the friendship is reciprocated			
		}
	}
}
