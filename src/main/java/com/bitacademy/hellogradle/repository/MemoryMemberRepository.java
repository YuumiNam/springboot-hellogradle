package com.bitacademy.hellogradle.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bitacademy.hellogradle.domain.Member;

// MemberRepository의 구현체
// @Repository
public class MemoryMemberRepository implements MemberRepository{
	
	// 멤버 정보를 저장해주는 필드 생성
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		
		member.setId(++sequence);
		store.put(member.getId(), member);
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
	
	// 객체의 내용을 비워주는 메소드
	public void clearStore() {
		store.clear();
	}
}
