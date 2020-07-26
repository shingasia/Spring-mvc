package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mem.Member;
import spring.mem.MemberSearchService;
import spring.exception.*;

@Controller
@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
public class LoginController {
	
	private MemberSearchService memberSearchSvc;

	public LoginController(MemberSearchService memberSearchSvc) {
		this.memberSearchSvc = memberSearchSvc;
	}
	
	@RequestMapping(value="/loginStep1", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginStep1() {
		return "login/loginStep1";
	}
	
	@RequestMapping(value="/loginStep2", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginStep2(Model model,
			@RequestParam(value="id") String id,
			@RequestParam(value="password") String pw,
			HttpSession session, RedirectAttributes ra) {
		try {
			Member member=memberSearchSvc.searchMember(id, pw);//해당 아이디와 비밀번호를 가진 회원을 검색
			
			ra.addAttribute("id", member.getId());
			session.setAttribute(member.getId(), member);//Member 세션 저장
		}catch(MemberNotFoundException e1) {
			return "login/loginFail";//회원 정보가 없습니다.
		}catch(DifferentPasswordException e1) {
			return "login/loginFail";//비밀번호가 다릅니다.
		}
		
		return "redirect:/sell/list/sellList";//메인페이지로 이동
	}
	
}
