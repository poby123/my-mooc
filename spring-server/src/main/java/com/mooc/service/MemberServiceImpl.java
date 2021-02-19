package com.mooc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mooc.dao.MemberDAO;
import com.mooc.entity.Member;
import com.mooc.entity.Organization;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Transactional
	@Override
	public void add(Member member) {
		memberDAO.add(member);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Member> findByOrganization(Organization organization) {
		return memberDAO.findByOrganization(organization);
	}

	@Transactional
	@Override
	public void delete(String id) {
		memberDAO.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Member findOne(String id) {
		return memberDAO.findOne(id);
	}

}
