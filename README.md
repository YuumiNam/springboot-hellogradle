# Springboot를 Gradle, JPA, Thymeleaf를 활용한 최신트렌드로 입문
### (기존에는 Maven, MyBatis, jsp)
<br><br>

### (인프런) 스프링 입문 - 코드로배우는 스프링부트, 웹MVC, DB접근기술
#### 인프런 주소 : https://www.inflearn.com/
<br><br>

**스프링부트 기본 라이브러리**
```
spring-boot-starter-web
	spring-boot-starter-tomcat: 톰캣(웹서버)
	spring-webmvc: 스프링 웹MVC
spring-boot-starter-thymeleaf: 웹브라우저에 HTML이 보이는 것 -> HTML을 만들어주는 Template Engine이 필요
				=> Thymeleaf (View)

spring-boot-starter (공통): 스프링부트 + 스프링코어 + 로깅(logback, slf4j)
```
<br><br>

**테스트 라이브러리**
```
spring-boot-starter-test
	junit: 테스트 프레임워크
	mockito: 목 라이브러리
	assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
	spring-test: 스프링 통합 테스트 지원
```
<br><br>

![화면 캡처 2023-01-17 180755](https://user-images.githubusercontent.com/114986610/212855861-8a30af47-7abb-4f03-a5f6-415ee5a81ea9.png) \
출처 : 김영한의 스프링 입문

- 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다
- (참고) **spring-boot-devtools** 라이브러리를 추가하면, html파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경이 가능
<br><br>

**웹사이트를 구성하는 3가지 방법**
1. 정적컨텐츠 (HTML파일 자체를 웹에 올림)
2. MVC모델 (Model, View, Controller)
3. API 사용
<br><br>

**스프링 빈을 등록하는 2가지 방법**
1. 컴포넌트 스캔과 자동 의존관계 설정
``` java
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
}
```

컴포넌트 스캔 원리
- @Component 어노테이션이 있으면 스프링 빈으로 자동 등록됨
- @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이류도 컴포넌트 스캔 때문

- @Component 를 포함하는 다음 어노테이션도 스프링 빈으로 자동 등록됨
	- @Controller
	- @Service
	- @Repository
<br>

2. 자바 코드로 직접 스프링 빈 등록하기
``` java
@Configuration
public class SpringConfig {

	// private DataSource dataSource;
	private EntityManager em;
	
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		super();
//		this.dataSource = dataSource;
//	}

	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		// return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}
```

- XML로 설정하는 방식도 있지만 최근에는 잘 사용하지않음
- 실무에서는 정형화된 컨트롤러, 서비스, 레포지토리 같은 코드는 컴포넌트 스캔을 사용함
- 그러나 상황에 따라서 구현 글래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록
<br><br>

**스프링 DB 접근기술**
1. 순수 JDBC \
(고대 개발자 선배님이 썼던방법... 넘어갈예정)
2. 스프링 JDBCTemplate \
(**JDBCTemplate**, **MyBatis** 같은 라이브러리는 순수 JDBC API에서 본 반복코드를 대부분 제거해줌. 하지만 SQL은 직접 작성해야함)
3. **JPA** \
(기존의 반복코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해줌 \
SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환 할 수 있음)

<br><br>

**JPA와 DB관련 라이브러리 추가**
1. build.gradle에 추가할 사항
``` java
dependencies {
	implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.7.3'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
```

2. application.properties에 추가할 사항
``` java
spring.datasource.driverClassName=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://127.0.0.1:3306/(DB 이름)
spring.datasource.username=(user 이름)
spring.datasource.password=(user 패스워드)
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=none
```
