package cn.tax.test;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tax.test.entity.Person;
import cn.tax.test.service.TestService;

public class TestMerge 
{	ClassPathXmlApplicationContext ctx;
	@Before
	public void loadCtx() 
	{
	ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void testSping()
	{	
		TestService ts= (TestService) ctx.getBean("testService");
		ts.say();
	}
	@Test
	public void testHibernate(){
		// TODO Auto-generated method stub
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		//保存人员
		session.save(new Person("人员1"));
		transaction.commit();
		session.close();
	}
	@Test
	 public void testServiceAndDao() 
	 {
		 TestService ts = (TestService)ctx.getBean("testService");
		 //ts.save(new Person("人员2"));
		//System.out.println(ts.findPerson("8a81b6915c667b88015c667b89d90000"));
	 }
	@Test
	public void testTransationReadOnly()
	{	//只读事务,如果在只读事务中出现更新操作则回滚
		TestService ts =(TestService)ctx.getBean("testService");
		System.out.println(ts.findPerson("8a81b6915c667b88015c667b89d90000"));
	}
 
}
