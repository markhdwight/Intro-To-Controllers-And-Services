package com.cooksys.Friendlr.entity;

import java.util.HashSet;
import java.util.Set;

public class Person {

	private Long id;
	private String firstName;
	private String lastName;
	
	private Set<Person> friends = new HashSet<Person>();
	
	public Person()
	{
		
	}
	
	public Person(Long id,String firstName,String lastName)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addFriend(Person f)
	{
		friends.add(f);
	}
	
	public void removeFriend(Person f)
	{
		friends.remove(f);
	}
}
