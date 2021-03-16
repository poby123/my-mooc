package com.mooc.moocServer.repository;

import com.mooc.moocServer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
