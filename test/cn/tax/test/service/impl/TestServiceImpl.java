package cn.tax.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tax.test.dao.TestDao;
import cn.tax.test.entity.Person;
import cn.tax.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource
	TestDao testDao;
	@Override
	public void say() {
		// TODO 自动生成的方法存根
		System.out.println("service saying hi");
	}

	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		testDao.save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		// TODO Auto-generated method stub
		return testDao.findPerson(id);
	}

}
