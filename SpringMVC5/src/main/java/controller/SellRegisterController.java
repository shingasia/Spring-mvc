package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.sell.SellRegisterRequest;
import spring.sell.SellRegisterService;
import spring.sell.SellSearchService;
import spring.sell.SellVO;


@Controller
@RequestMapping(value="/sell/register/*", method= { RequestMethod.GET, RequestMethod.POST})
public class SellRegisterController {

	private SellRegisterService sellRegisterSvc;

	public void setSellRegisterSvc(SellRegisterService sellRegisterSvc) {
		this.sellRegisterSvc = sellRegisterSvc;
	}
	
	//로그인을 안했으면 인터셉터에 걸린다
	@RequestMapping(value="/registerSellStep1", method= { RequestMethod.GET, RequestMethod.POST})
	public String registerSellStep1() {
		return "sell/register/registerSellStep1";
	}
	
	//로그인을 안했으면 인터셉터에 걸린다
	@RequestMapping(value="/registerSellStep2", method= { RequestMethod.GET, RequestMethod.POST})
	public String registerSellStep2(Model model, SellRegisterRequest sellRequest, HttpSession session) {//커맨드 객체 이름 sellVO
		if(session.getAttribute(sellRequest.getId())==null) {//세션에 로그인 정보가 없어서 리다이렉트
			return "redirect:/login/loginStep1";
		}
		
		model.addAttribute("id", sellRequest.getId());
		sellRegisterSvc.registerSell(sellRequest);
		return "sell/register/registerSellStep2";
	}
}
