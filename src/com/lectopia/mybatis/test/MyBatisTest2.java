package com.lectopia.mybatis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lectopia.mybatis.mapper.PersonMapper;
import com.lectopia.mybatis.vo.Person;

class MyBatisTest2 {
	private PersonMapper mapper = null;
	
	@BeforeEach
	void setup() {
		String file = "com/lectopia/mybatis/config.xml";
		try {
			InputStream is = Resources.getResourceAsStream(file);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			SqlSession session = factory.openSession();
			mapper = session.getMapper(PersonMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSelectAll() {
		List<Person> list = mapper.getAll();
		for(Person p : list) {
			System.out.println(p);
		}
	}
	
	@Test
	void testAdd() {
		Person obj = new Person(1, "lee");
		int result = mapper.add(obj);
		assertEquals(1, result);
	}

}
