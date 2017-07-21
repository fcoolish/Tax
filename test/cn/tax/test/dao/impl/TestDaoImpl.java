package cn.tax.test.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tax.test.dao.TestDao;
import cn.tax.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {
	
	
	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Person.class,id);
	}

}
