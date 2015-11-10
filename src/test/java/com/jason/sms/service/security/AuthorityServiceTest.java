/**
 * 
 */
package com.jason.sms.service.security;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jason.framework.orm.Page;
import com.jason.sms.MyBatisTestBase;
import com.jason.sms.domain.security.Authority;

/**
 * 
 * @author Jason
 * @date 2015-11-10
 */
public class AuthorityServiceTest  extends MyBatisTestBase {
	
	@Autowired
	private AuthorityService authorityService;
	
	@Test
	public void testQueryPage(){
		Page<Authority> page = new Page<Authority>();
		page = authorityService.queryPage(page);
		System.out.println("总数："+page.getTotalCount());
		List<Authority> list = page.getResult();
		for (Authority t:list) {
			System.out.println(t.getId()+" "+t.getName());
		}
	}
}
