package controller.extra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.sell.SellSearchService;

@Controller
@RequestMapping(value="/sell/list", method=RequestMethod.GET)
public class TendencyController {
	
	private SellSearchService sellSearchSvc;

	public TendencyController(SellSearchService sellSearchSvc) {
		this.sellSearchSvc = sellSearchSvc;
	}
	
	@RequestMapping(value="/showTendency", method=RequestMethod.GET)
	public String getTendency(Model model, @RequestParam("id") String id) {
		model.addAttribute("id", id);
		model.addAttribute("nationalities", sellSearchSvc.searchUnits());//나라 이름 리스트
		model.addAttribute("moneyAverage", sellSearchSvc.averagesOfMoney());//나라별 판매 액수 리스트
		model.addAttribute("suggestAverage", sellSearchSvc.averagesOfSuggest());//나라별 제시 가격 리스트
		return "sell/list/showTendency";
	}
	
}
