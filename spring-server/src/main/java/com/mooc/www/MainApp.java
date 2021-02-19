package com.mooc.www;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mooc.config.AppConfig;
import com.mooc.entity.Member;
import com.mooc.service.MemberService;

public class MainApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = context.getBean(MemberService.class);

		memberService.add(new Member("id1", "name1", "password1", null));
//		memberService.add(new Member("id2", "name2", "password2"));
//		memberService.add(new Member("id3", "name3", "password3"));

//		List<Member> members = memberService.listMembers();
//		for (Member member : members) {
//			System.out.println("Id = " + member.getId());
//			System.out.println("Name = " + member.getName());
//			System.out.println("Password = " + member.getPassword());
//		}
		
		context.close();
	}

}
