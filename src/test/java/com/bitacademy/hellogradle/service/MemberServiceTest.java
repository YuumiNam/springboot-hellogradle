package com.bitacademy.hellogradle.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bitacademy.hellogradle.domain.Member;
import com.bitacademy.hellogradle.repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	// repository와 service에 객체를 각각 생성해주는것이 아닌 서로 연결시킨것
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	// 객체의 내용을 비워주는 메소드
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void join() {
		// given
		Member member = new Member();
		member.setName("hello");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void duplicateJoin() {
		// given
		Member member01 = new Member();
		member01.setName("hi");
		
		Member member02 = new Member();
		member02.setName("hi");
		
		// when
		memberService.join(member01);

		try {
			memberService.join(member02);
		} catch (IllegalStateException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
		}
		
		// then
	}
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void fineOne() {
		
	}
}
