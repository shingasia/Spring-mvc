package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogoutController {
	
	@RequestMapping(value="/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session, RedirectAttributes rAttr) {//세션 종료, 다시 로그인 화면으로 이동
		rAttr.addFlashAttribute("message", "로그아웃 되었습니다.");
		session.invalidate();
		return "redirect:/login/loginStep1";
	}
	
}
