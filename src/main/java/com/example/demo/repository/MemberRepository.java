package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

    //select m from Member m where m.username = ?  --> 자동생성
    List<Member> findByUsername(String username);
}
