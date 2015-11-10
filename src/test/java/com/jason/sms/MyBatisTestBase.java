
package com.jason.sms;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

// AbstractTransactionalJUnit4SpringContextTests 执行默认是会回滚
// AbstractJUnit4SpringContextTests 执行默认不回滚

@ContextConfiguration(locations = {"classpath:/META-INF/spring/application-root.xml"})
public class MyBatisTestBase extends AbstractJUnit4SpringContextTests{

	protected static MockHttpServletRequest request;

	protected static MockHttpServletResponse response;

	@Before
	public void setUp() {
		initMockData();
	}
	protected void initMockData() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
}
