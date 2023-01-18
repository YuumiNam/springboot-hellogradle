package com.bitacademy.hellogradle.repository;

import java.util.List;
import java.util.Optional;

import com.bitacademy.hellogradle.domain.Member;

// 간단한 임시저장소 인터페이스를 구현
public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
