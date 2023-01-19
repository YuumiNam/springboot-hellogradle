package com.bitacademy.hellogradle.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.bitacademy.hellogradle.domain.Member;

public class JpaMemberRepository implements MemberRepository{

	// JPA는 EntityManager에서 모든것이 동작함
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
							  	.setParameter("name", name)
							  	.getResultList();
		
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		List<Member> result = em.createQuery("select m from Member m", Member.class)
				.getResultList();
		
		return result;
	}
}
