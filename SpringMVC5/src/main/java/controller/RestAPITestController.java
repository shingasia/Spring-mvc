package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.mem.Member;


//MappingJackson2HttpMessageConverter 를 통해서 application/json 컨텐츠 타입을 리턴
//@RestController는 객체(VO,DTO)를 반환하기만 하면, 객체데이터는 application/json 형식의 HTTP ResponseBody에 직접 작성
/*
	spring 3.1이하의 버전의경우에는 pom.xml에서 라이브러리도 입력을해주고, MessageConverter Bean도 따로 등록을 해주어야 동작을 했지만,
	이후 버전에서는 json 라이브러리가 클래스패스에 존재하고 , 
	servlet-context.xml에 <annotation-driven/>를 입력만 해주면 디폴트 MessageConverter가 자동으로 등록이 된다고한다!
*/
@RestController
@RequestMapping(value="/sample")
public class RestAPITestController {
	
	@RequestMapping("/hello")
	public String hello(){
		return "Hello World";// 문자열(text/html) "Hello World!" 형태를 응답 body 태그에 바로 작성된다
	}
	
	//produces 속성은 생략 가능(URL에 확장자.json을 붙이면 데이터가 보인다)
	//application/json, application/xml 타입으로 리턴이 가능하다
	@RequestMapping(value="/sampleVO", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Member sampleVO() {
		Member obj=new Member("shingasia", "a1s2d3f4", "남광현", "111-1111-111");
		return obj;
	}
	
	//기본적으로 xml형태로 리턴한다 (URL에 확장자.json을 붙이면 json 형태로 보인다)
	//produces="application/json", comsumes="application/json"
	@GetMapping(value="/sampleList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> sampleList(){
		ArrayList<Member> list=new ArrayList<>();
		for(int i=0;i<10;i++) {
			Member obj=new Member("shingasia"+i,i+"", "홍길동", "222-2222-2222");
			list.add(obj);
		}
		return list;
	}
	
	//확장자 .json을 붙여야 한다(xml형태로는 볼 수 없기 때문이다)
	@RequestMapping(value="/sampleMap")
	public Map sampleMap() {
		Map<Integer, Member> map=new HashMap<>();
		for(int i=0;i<10;i++) {
			map.put(i, new Member("shingasia"+i,i+"", "홍길동", "222-2222-2222"));
		}
		return map;
	}
}
