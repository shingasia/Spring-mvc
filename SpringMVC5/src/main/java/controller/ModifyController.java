package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.exception.DifferentPasswordException;
import spring.exception.MemberNotFoundException;
import spring.exception.WrongPasswordException;
import spring.mem.ModifyPasswordService;
import spring.mem.ModifyRequest;

@Controller
@RequestMapping(value="/modify")
public class ModifyController {
	
	private ModifyPasswordService modifyPasswordSvc;
	
	//생성자
	public ModifyController(ModifyPasswordService modifyPasswordSvc) {
		this.modifyPasswordSvc=modifyPasswordSvc;
	}
	
	
	@RequestMapping(value="/modifyStep1", method= {RequestMethod.GET, RequestMethod.POST})
	public String step1(@ModelAttribute("message") String message) {
		return "modify/modifyStep1";
	}
	
	@RequestMapping(value="/modifyStep2", method= {RequestMethod.POST, RequestMethod.PUT})//세션 종료
	public ModelAndView step2(ModifyRequest mRequest, HttpSession session) {//커맨드 객체 이름=modifyRequest JSP화면에서 ${modifyRequest.프로퍼티이름}으로 사용
		if(!mRequest.getNewPassword().equals(mRequest.getConfirmNewPassword())) {//새 비밀번호 != 새 비밀번호 확인
			throw new DifferentPasswordException();
		}
		modifyPasswordSvc.modifyPassword(mRequest.getId(), mRequest.getCurrentPassword(), mRequest.getNewPassword());
		session.invalidate();
		ModelAndView mav=new ModelAndView();
		mav.addObject("mRequest", mRequest);
		mav.setViewName("modify/modifyStep2");//비밀번호 바뀐거 확인 후 다시 로그인 화면으로 이동
		return mav;
	}
	
	//MemberNotFoundException, DifferentPasswordException, WrongPasswordException 처리
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleException1(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "회원정보를 찾을 수 없습니다.");
		return "redirect:/modify/modifyStep1";
	}
	@ExceptionHandler(DifferentPasswordException.class)
	public String handleException2(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "새로운 비밀번호와 새로운 비밀번호 확인이 서로 다릅니다");
		return "redirect:/modify/modifyStep1";
	}
	@ExceptionHandler(WrongPasswordException.class)
	public String handelException3(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "기존에 사용하던 비밀번호가 틀렸습니다.");
		return "redirect:/modify/modifyStep1";
	}
	
}
