package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.basket.BasketDeleteService;
import spring.basket.BasketRegisterService;
import spring.basket.BasketSearchService;
import spring.basket.BasketVO;
import spring.exception.DuplicationBasketException;
import spring.sell.SellVO;

@Controller
@RequestMapping(value="/basket")
public class BasketListController {
	
	private BasketSearchService basketSearchSvc;
	private BasketRegisterService basketRegisterSvc;
	private BasketDeleteService basketDeleteSvc;
	
	
	public BasketListController(BasketSearchService basketSearchSvc, BasketRegisterService basketRegisterSvc, BasketDeleteService basketDeleteSvc) {
		this.basketSearchSvc = basketSearchSvc;
		this.basketRegisterSvc = basketRegisterSvc;
		this.basketDeleteSvc=basketDeleteSvc;
	}
	
	@RequestMapping(path="/list/myBasket", method=RequestMethod.GET)
	public String myBasket(Model model, @RequestParam(value="id") String id) {
		List<SellVO> bList=basketSearchSvc.searchBaskets(id);
		model.addAttribute("myBasket", bList);
		model.addAttribute("id", id);
		return "basket/list/myBasket";
	}
	
	
	//장바구니에 추가하고 다시 sellContent 페이지로 리다이렉트
	@RequestMapping(path="/register/registerBasket", method= {RequestMethod.POST, RequestMethod.PUT})
	public String addBasket(@ModelAttribute("basketVO") BasketVO basketVO, RedirectAttributes ra) {
		try {
			basketRegisterSvc.registerBasket(basketVO);
		}catch(DuplicationBasketException e) {
			ra.addFlashAttribute("message" , "이미 북마크에 존재합니다.");
		}finally {
			ra.addAttribute("id", basketVO.getId());
			ra.addAttribute("identifier", basketVO.getIdentifier());
		}
		return "redirect:/sell/list/sellContent";
	}
	
	//삭제하려면 id와 identifier 둘 다 필요 -> 삭제 후 myBasket으로 리다이렉트
	@RequestMapping(path="/delete/deleteBasket", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteBasket(@ModelAttribute("basketVO") BasketVO basketVO, RedirectAttributes ra) {
		ra.addAttribute("id", basketVO.getId());
		basketDeleteSvc.deleteBasket(basketVO);
		return "redirect:/basket/list/myBasket";
	}
	
	
}
