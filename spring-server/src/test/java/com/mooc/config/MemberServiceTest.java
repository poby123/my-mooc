package com.mooc.config;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mooc.entity.Member;
import com.mooc.service.MemberService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class MemberServiceTest {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	//MemberService memberService = context.getBean(MemberService.class);

	@Autowired
	MemberService memberService;
	
	@Test
	public void addMember() {	
		Member member = new Member("id1", "name1", "password1", null);
		memberService.add(member);
	}
	
	@Ignore
	public void getMember() {
		Member member = memberService.findOne("id1");
		log.info(member.toString());
	}
}
