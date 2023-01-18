package com.bitacademy.hellogradle.service;

import java.util.List;
import java.util.Optional;

import com.bitacademy.hellogradle.domain.Member;
import com.bitacademy.hellogradle.repository.MemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;
	
	// private final MemberRepository memberRepository = new MemoryMemberRepository 이렇게 하면
	// repository의 객체랑 service의 객체랑 서로 다른 객체가 각각 생성되므로
	// 생성자를 이용해서 둘을 같이 연결시켜줘야함
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	* 회원가입
	*/
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복회원 검증
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
