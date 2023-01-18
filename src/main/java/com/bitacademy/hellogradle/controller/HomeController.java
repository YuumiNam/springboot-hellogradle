package com.bitacademy.hellogradle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// index.html을 찾기 전에 먼저
// 스프링 컨테이너에서 관련 컨트롤러를 찾기때문에
// index.html가 아닌 HomeController가 우선순위를 갖는다

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
}
