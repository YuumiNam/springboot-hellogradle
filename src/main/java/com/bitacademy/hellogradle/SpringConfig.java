package com.bitacademy.hellogradle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bitacademy.hellogradle.repository.MemberRepository;
import com.bitacademy.hellogradle.repository.MemoryMemberRepository;
import com.bitacademy.hellogradle.service.MemberService;

@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
