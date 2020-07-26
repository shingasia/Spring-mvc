package controller;
import spring.exception.DifferentPasswordException;
import spring.exception.PhoneNumberNotMatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mem.JoinRequest;
import spring.mem.MemberJoinService;
//컨트롤러도 ControllerConfig.java 파일 만들고 JoinController도 Bean으로 등록
//@RequestMapping(value="/join", method = RequestMethod.GET)//각 메서드의 공통되는 경로
@Controller
public class JoinController {
	
	private MemberJoinService memberJoinSvc;

	public void setMemberJoinSvc(MemberJoinService memberJoinSvc) {
		this.memberJoinSvc = memberJoinSvc;
	}
	
	@GetMapping("/join/joinStep1")
	public String step1(@ModelAttribute("message") String message) {
		return "join/joinStep1";
	}
	
	//URL 전체적으로 수정하기
	@PostMapping("/join/joinStep2")
	public ModelAndView step2(/*Model model*/ JoinRequest jRequest) {//커맨드 객체 이름=joinRequest JSP화면에서 ${joinRequest.프로퍼티이름}으로 사용
		if(!jRequest.getConfirmPassword().equals(jRequest.getPassword())) {//비번, 비번확인이 다르면 Exception 발생
			throw new DifferentPasswordException();
		}
		Pattern p=Pattern.compile("\\d{3}-\\d{3,4}-\\d{4}");//전화번호 정규표현식
		Matcher m=p.matcher(jRequest.getPhone());
		if(!m.matches()) {
			throw new PhoneNumberNotMatchException();
		}
		
		memberJoinSvc.memberJoin(jRequest);//DB에 회원정보 저장, null을 리턴하면 아이디 중복
		ModelAndView mav=new ModelAndView();
		mav.setViewName("join/joinStep2");
		return mav;
	}
	
	
}
