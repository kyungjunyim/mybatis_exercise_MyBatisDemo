package com.lectopia.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.lectopia.mybatis.mapper.PersonMapper;
import com.lectopia.mybatis.vo.Person;

public class PersonDao implements PersonMapper {
	private SqlSessionFactory sqlSessionFactory = null;
	
	public PersonDao(SqlSessionFactory factory) {
		this.sqlSessionFactory = factory;
	}
	
	public int add(Person vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int result = session.insert("person.add", vo);
		session.commit();
		session.close();
		return result;
	}
	
	public List<Person> getAll() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Person> result = session.selectList("person.getAll");
		session.close();
		return result;
	}
	
	public Person getById(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		Person result = session.selectOne("person.getById", id);
		session.close();
		return result;
	}
	
	public int modify(Person vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int result = session.update("person.modify", vo);
		session.commit();
		session.close();
		return result;
		
	}
}
