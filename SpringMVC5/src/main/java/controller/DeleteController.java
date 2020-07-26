package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.DifferentPasswordException;
import spring.exception.MemberNotFoundException;
import spring.mem.Member;
import spring.mem.MemberDeleteService;

//컨트롤러도 ControllerConfig.java 파일 만들고 JoinController도 Bean으로 등록
//@RequestMapping(value="/join", method = RequestMethod.GET)//각 메서드의 공통되는 경로

@Controller
@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class DeleteController {
	
	private MemberDeleteService memberDeleteSvc;

	public void setMemberDeleteSvc(MemberDeleteService memberDeleteSvc) {
		this.memberDeleteSvc = memberDeleteSvc;
	}
	
	@RequestMapping(value="/deleteStep1", method= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
	public String step1() {
		return "delete/deleteStep1";
	}
	
	@RequestMapping(value="/deleteStep2", method= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})//세션 종료, 다시 로그인 화면으로 이동
	public String step2(Model model, 
			@RequestParam(value="id") String id, 
			@RequestParam(value="password") String password,
			HttpSession session) {
		Member member=memberDeleteSvc.deleteMember(id, password);
		session.removeAttribute(member.getId());//세션에서 해당 속성 삭제
		session.invalidate();//현재 세션 종료
		model.addAttribute("name", member.getName());
		
		return "delete/deleteStep2";
	}
	
	//존재하는 회원 아이디가 없거나 비밀번호가 일치하지 않으면 Error
	@ExceptionHandler({MemberNotFoundException.class, DifferentPasswordException.class})
	public String handleMemberNotFound() {
		return "delete/deleteError";
	}
	
	
}
