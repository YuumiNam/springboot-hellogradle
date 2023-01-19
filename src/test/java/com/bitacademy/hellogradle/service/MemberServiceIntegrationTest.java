package com.bitacademy.hellogradle.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bitacademy.hellogradle.domain.Member;
import com.bitacademy.hellogradle.repository.MemberRepository;

@SpringBootTest
@Transactional // test를 실행할때 만든 쿼리를 DB에 다 실행시키고 test가 끝나면 다시 롤백을 시켜줌
public class MemberServiceIntegrationTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
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
		member02.setName("둘리");
		
		// when
		memberService.join(member01);

		try {
			memberService.join(member02);
		} catch (IllegalStateException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
		}
		
		// then
	}

}
