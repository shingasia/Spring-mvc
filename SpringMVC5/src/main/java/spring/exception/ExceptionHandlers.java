package spring.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import controller.JoinController;
/*
	// @RestController를 사용한 모든 controller 대상 
	@ControllerAdvice(annotations = RestController.class) 
	public class ExampleAdvice1 {} 
	
	// 해당 패키지 내 모든 controller 대상 
	@ControllerAdvice("org.example.controllers") 
	public class ExampleAdvice2 {} 
	
	// 해당 클래스 하위로 구현된 controller 대상 
	@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class}) 
	public class ExampleAdvice3 {}
	
	------------------------------------------------------------------------
	@ControllerAdvice의 속성들
	value, basePackages 타입=String[]						 	<-공통 설정을 적용할 컨트롤러가 속하는 기준 패키지
	annotations			타입=Class<? extends Annotation>[] 	<-특정 애너테이션이 적용된 컨트롤러 대상
	assignableTypes		타입=Class<?>[]						<-특정 타입 또는 그 하위 타입인 컨트롤러 대상
 */


//ControllerAdvice를 적용한 클래스가 동작하려면 해당 클래스를 스프링에 빈으로 등록해야 한다
@Component
@ControllerAdvice(basePackages= {"controller"})
public class ExceptionHandlers {
	
	//회원가입할 때 이미 아이디가 존재하면 동작
	@ExceptionHandler(DuplicationMemberException.class)
	public String handleDuplicationMember(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message", "이미 존재하는 아이디입니다.");//일회성 데이터 전달
		return "redirect:/join/joinStep1";
	}
	
	//회원가입할 때 비밀번호와 비밀번호 확인이 다르면 동작
	@ExceptionHandler(DifferentPasswordException.class)
	public String handleDifferentPassword(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message", "비밀번호와 비밀번호 확인이 일치하지 않습니다");
		return "redirect:/join/joinStep1";
	}
	
	//전화번호가 정규표현식과 일치하지 않으면 동작
	@ExceptionHandler(PhoneNumberNotMatchException.class)
	public String handlePhoneNumber(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message", "전화번호를  XXX-XXXX-XXXX 형태로 입력해주세요");
		return "redirect:/join/joinStep1";
	}
	
	
}
