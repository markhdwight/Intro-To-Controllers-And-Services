package com.cooksys.Friendlr.mapper;

import org.mapstruct.Mapper;

import com.cooksys.Friendlr.dto.PersonDto;
import com.cooksys.Friendlr.entity.Person;

@Mapper(componentModel="spring")
public interface PersonMapper {

	PersonDto toPersonDto(Person person);
	
	Person toPerson(PersonDto personDto);
	
}
