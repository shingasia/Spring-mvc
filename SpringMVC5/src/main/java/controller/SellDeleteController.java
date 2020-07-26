package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.sell.SellDeleteService;
import spring.sell.SellSearchService;
import spring.sell.SellVO;

@Controller
public class SellDeleteController {
	
	private SellDeleteService sellDeleteSvc;
	private SellSearchService sellSearchSvc;

	public SellDeleteController(SellDeleteService sellDeleteSvc, SellSearchService sellSearchSvc) {
		this.sellDeleteSvc = sellDeleteSvc;
		this.sellSearchSvc = sellSearchSvc;
	}



	//(전체목록에서 삭제, 내 목록에서 삭제, 화폐단위 리스트에서 삭제) 각 URL을 구분할 수  있는파라미터(listType)가 하나 필요
	//수정, 삭제 모두 리다이렉트 한다
	//RedirectAttributes에서 addAttribute, addFlashAttribute, FlashMap 차이
	//리다이렉트는 컨트롤러가 받는다
	@RequestMapping(value="/delete/deleteSell", method= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
	public String deleteSell(RedirectAttributes ra,
			@RequestParam(value="identifier") String identifier,
			@RequestParam(value="listType") String listType,
			@RequestParam(value="id") String id) {
		
		if(listType.equals("sellList")) {//전체 목록에서 삭제
			sellDeleteSvc.deleteSellByIdentifier(identifier);//해당 게시물을 삭제
			ra.addAttribute("id", id);
			return "redirect:/sell/list/sellList";
		}else if(listType.equals("myList")) {//나의 목록에서 삭제
			SellVO obj=sellSearchSvc.searchByIdentifier(identifier);//해당 게시물 검색
			sellDeleteSvc.deleteSellByIdentifier(obj.getIdentifier());//해당 게시물을 삭제
			ra.addAttribute("id", id);
			return "redirect:/sell/list/myList";
		}else if(listType.equals("sellContent")) {//내용에서 삭제
			SellVO obj=sellSearchSvc.searchByIdentifier(identifier);
			sellDeleteSvc.deleteSellByIdentifier(obj.getIdentifier());
			ra.addAttribute("id", id);
			return "redirect:/sell/list/sellList";//전체 리스트로 이동
		}
		return null;
	}
	
}
