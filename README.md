### (인프런) 스프링 입문 - 코드로배우는 스프링부트, 웹MVC, DB접근기술
#### 인프런 주소 : https://www.inflearn.com/
<br><br>

**스프링부트 기본 라이브러리**
```
spring-boot-starter-web
	spring-boot-starter-tomcat: 톰캣(웹서버)
	spring-webmvc: 스프링 웹MVC
spring-boot-starter-thymeleaf: 웹브라우저에 HTML이 보이는 것 -> HTML을 만들어주는 Template Engine이 필요  => Thymeleaf (View)

spring-boot-starter (공통): 스프링부트 + 스프링코어 + 로깅(logback, slf4j)
```
<br>

**테스트 라이브러리**
```
spring-boot-starter-test
	junit: 테스트 프레임워크
	mockito: 목 라이브러리
	assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
	spring-test: 스프링 통합 테스트 지원
```
<br>

![화면 캡처 2023-01-17 180755](https://user-images.githubusercontent.com/114986610/212855861-8a30af47-7abb-4f03-a5f6-415ee5a81ea9.png)
- 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다
- (참고) **spring-boot-devtools** 라이브러리를 추가하면, html파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경이 가능
<br>

**웹사이트를 구성하는 3가지 방법**
1. 정적컨텐츠 (HTML파일 자체를 웹에 올림)
2. MVC모델 (Model, View, Controller)
3. API 사용
