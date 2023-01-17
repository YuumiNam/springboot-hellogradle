package com.bitacademy.hellogradle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HellogradleController {
	
	 @GetMapping("hello")
	 public String hello(Model model) {
		 model.addAttribute("data", "Gradle!!");
		 return "hello";
	 }
	 
	 @GetMapping("hello-mvc")
	 public String helloMvc(@RequestParam(value="name", required=false) String name, Model model) {
		 model.addAttribute("name", name);
		 return "hello-template"; 
	 }
	 
	 @GetMapping("hello-string")
	 @ResponseBody
	 public String helloString(@RequestParam("name") String name) {
		 return "hello " + name;
	 }
	 
	 @GetMapping("hello-api")
	 @ResponseBody // viewResolver를 사용하지않음 대신에 HTTP의 BODY에 문자 내용을 직접 반환 (HttpMessageConverter 문자는 String, 객체는 Json으로 반환)
	 public Hello helloApi(@RequestParam("name") String name) {
		 Hello hello = new Hello();
		 
		 hello.setName(name);
		 
		 return hello; // {"name":"spring"} 기본 default는 JSON방식으로 출력됨
	 }
	 
	 // helloApi에서 불러오기위해 만든 객체임
	 static class Hello {
		 private String name;

		 public String getName() {
			 return name;
		 }

		 public void setName(String name) {
			 this.name = name;
		 }
	 }
}
