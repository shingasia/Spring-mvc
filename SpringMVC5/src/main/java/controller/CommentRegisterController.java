package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.comment.CommentDaoImpl;
import spring.comment.CommentRegisterRequest;
import spring.comment.CommentRegisterService;
import spring.sell.SellSearchService;

@Controller
@RequestMapping(value="/sell/list", method= {RequestMethod.POST, RequestMethod.PUT})
public class CommentRegisterController {
	
	private CommentRegisterService commentRegisterSvc;
	//댓글을 최근 날짜 순서로 다 가져온다
	//private CommentDaoImpl commentDao;
	

	public CommentRegisterController(CommentRegisterService commentRegisterSvc) {
		this.commentRegisterSvc = commentRegisterSvc;
	}

	//댓글 쓰고 다시 해당 페이지로 리다이렉트
	@RequestMapping(value="/registerComment", method= {RequestMethod.POST, RequestMethod.PUT})
	public String register(CommentRegisterRequest cRequest, RedirectAttributes ra) {
		commentRegisterSvc.registerComment(cRequest);//댓글 등록
		//ra.addFlashAttribute("sellVO", sellSearchSvc.searchByIdentifier(cRequest.getIdentifier()));//게시물 정보
		ra.addAttribute("identifier", cRequest.getIdentifier());//게시물 식별자
		ra.addAttribute("id", cRequest.getId());//로그인한 사용자 아이디
//		ra.addAttribute("")해당 게시물에 대한 댓글 리스트를 전부 출력
		return "redirect:/sell/list/sellContent";
	}
	
	
}
