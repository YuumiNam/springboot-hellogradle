package com.bitacademy.hellogradle.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bitacademy.hellogradle.domain.Member;

class MemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// 객체의 내용을 비워주는 메소드를 갖고옴
	// 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있는데
	// 이렇게되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		
		member.setName("dooli");
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		
		Assertions.assertEquals(result, member);
	}
	
	@Test
	public void findByName() {
		Member member01 = new Member();
		member01.setName("ddochi");
		repository.save(member01);
		
		Member member02 = new Member();
		member02.setName("mikol");
		repository.save(member02);
		
		Member result01 = repository.findByName("ddochi").get();
		Assertions.assertEquals(result01, member01);
		
		Member result02 = repository.findByName("mikol").get();
		assertThat(result02).isEqualTo(member02);
	}
	
	@Test
	public void findAll() {
		Member member01 = new Member();
		member01.setName("ddochi");
		repository.save(member01);
		
		Member member02 = new Member();
		member02.setName("mikol");
		repository.save(member02);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
}
