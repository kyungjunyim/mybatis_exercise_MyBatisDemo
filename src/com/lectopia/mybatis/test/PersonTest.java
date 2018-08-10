package com.lectopia.mybatis.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lectopia.mybatis.dao.PersonDao;
import com.lectopia.mybatis.vo.Person;

class PersonTest {
	private static final int OK = 1;
	private PersonDao personDao = null;

	@BeforeEach
	void setup() {
		String file = "com/lectopia/mybatis/config.xml";
		try {
			InputStream is = Resources.getResourceAsStream(file);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			this.personDao = new PersonDao(factory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetAll() {
		List<Person> result = personDao.getAll();
		assertNotNull(result);
		for (Person obj : result) {
			System.out.println(obj);
		}
	}

	@Test
	void testInsert() {
		Person obj = new Person();
		obj.setId(6);
		obj.setName("Test Lee");
		int result = personDao.add(obj);
		assertEquals(OK, result);
	}

	@Test
	void testInsertNG() {
		Person p = new Person();
		if (p.getName() == null) {
			int result = personDao.add(p);
			assertNotEquals(OK, result);
		} else {
			int result = personDao.add(p);
			assertEquals(OK, result);
		}
	}

	@Test
	void testSelectById() {
		Person result = personDao.getById(1);
		System.out.println(result);
		assertEquals(result.getId(), 1);
	}

	@Test
	void testUpdate() {
		Person obj = new Person(2, "lee sunsin");
		int result = personDao.modify(obj);
		assertEquals(OK, result);
	}
}
