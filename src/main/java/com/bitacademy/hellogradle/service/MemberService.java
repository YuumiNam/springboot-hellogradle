package com.bitacademy.hellogradle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitacademy.hellogradle.domain.Member;
import com.bitacademy.hellogradle.repository.MemoryMemberRepository;

public class MemberService {

	@Autowired
	MemoryMemberRepository memberRepository;
	
	/**
	* 회원가입
	*/
	public Long join(Member member) {
		validateDuplicateMember(member); // 같은이름 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	// 같은 이름이 있는 중복회원X
	private void validateDuplicateMember(Member member) {
		Optional<Member> result = memberRepository.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		});
	}
	
	
	/**
	* 전체회원조회
	*/
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
