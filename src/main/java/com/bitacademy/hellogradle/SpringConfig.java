package com.bitacademy.hellogradle;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bitacademy.hellogradle.repository.JpaMemberRepository;
import com.bitacademy.hellogradle.repository.MemberRepository;
import com.bitacademy.hellogradle.service.MemberService;

@Configuration
public class SpringConfig {

	// private DataSource dataSource;
	private EntityManager em;
	
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		super();
//		this.dataSource = dataSource;
//	}

	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		// return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}
