package com.lectopia.mybatis.mapper;

import java.util.List;

import com.lectopia.mybatis.vo.Person;

public interface PersonMapper {
	public int add(Person person);
	public int modify(Person person);
	public List<Person> getAll();
	public Person getById(int id);
	
}
