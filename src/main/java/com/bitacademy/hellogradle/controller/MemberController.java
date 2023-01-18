package com.bitacademy.hellogradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitacademy.hellogradle.domain.Member;
import com.bitacademy.hellogradle.service.MemberService;

// 스프링 빈을 등록하는 2가지 방법
// 1. 컴포넌트 스캔과 자동 의존관계 설정
// 2. 자바 코드로 직접 스프링 빈 등록

// 실무에서는 주로 정형화된 1번 의존관계를 성정하고
// 2번같은 경우는 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.

@Controller
public class MemberController {
	
	// DI 첫번째 방법 : 필드 주입
	// @Autowired
	MemberService memberService;
	
	// DI 두번째 방법 : 생성자 주입 (추천)
	// 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// DI 세번째 방법 : setter 주입
	// @Autowired
	// public void setMemberService(MemberService memberService) {
	// 	this.memberService = memberService;
	// }
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		
		return "members/memberlist";
	}
}
