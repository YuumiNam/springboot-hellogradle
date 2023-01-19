package com.bitacademy.hellogradle.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bitacademy.hellogradle.domain.Member;
import com.bitacademy.hellogradle.repository.MemberRepository;

// @Service
@Transactional // 비즈니스 로직은 보통 여러 리포지토리를 호출 만약에 해당 비즈니스 로직에 문제가 발생했을 경우에는 해당 비즈니스 로직과 관련된 부분을 모두 롤백해야함 그래서 일반적으로 비즈니스 로직의 시작점인 서비스에 트랜잭션을 사용
public class MemberService {

	MemberRepository memberRepository;
	
	// private final MemberRepository memberRepository = new MemoryMemberRepository 이렇게 하면
	// repository의 객체랑 service의 객체랑 서로 다른 객체가 각각 생성되므로
	// 생성자를 이용해서 둘을 같이 연결시켜줘야함
	// @Autowired
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
