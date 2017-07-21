package cn.tax.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog 
{
	@Test
	public void test() throws Exception 
	{
		Log log = LogFactory.getLog(getClass());
		log.debug("调试级别日志");
	}
}
