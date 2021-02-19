package com.mooc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.mooc.entity.Member;
import com.mooc.entity.Organization;

@Component
public interface MemberDAO extends JpaRepository<Member, String>{
	void add(Member member);
	
	void delete(String id);
	
	Member findOne(String id);
	
	List<Member> findByOrganization(Organization organization);
}