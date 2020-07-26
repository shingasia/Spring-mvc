package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.sell.SellModifyRequest;
import spring.sell.SellModifyService;
import spring.sell.SellSearchService;
import spring.sell.SellVO;

@Controller
@RequestMapping(value="/sell/modify", method= {RequestMethod.GET, RequestMethod.POST})
public class SellModifyController {
	
	private SellSearchService sellSearchSvc;
	private SellModifyService sellModifySvc;

	public SellModifyController(SellSearchService sellSearchSvc, SellModifyService sellModifySvc) {
		this.sellSearchSvc = sellSearchSvc;
		this.sellModifySvc = sellModifySvc;
	}


	@RequestMapping(value="/modifySellStep1", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView step1(@RequestParam("identifier") String identifier) {
		SellVO sellVO=sellSearchSvc.searchByIdentifier(identifier);
		ModelAndView mav=new ModelAndView();
		mav.addObject("sellInfo", sellVO);
		mav.setViewName("sell/modify/modifySell");
		return mav;
	}
	
	//수정, 삭제 모두 전체 리스트로 리다이렉트 한다
	@RequestMapping(value="/modifySellStep2", method= {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
	public String step2(SellModifyRequest smRequest, RedirectAttributes rttr) {
		sellModifySvc.modifySell(smRequest);//DB 업데이트
		rttr.addAttribute("id", smRequest.getId());
		return "redirect:/sell/list/sellList";
	}
}
