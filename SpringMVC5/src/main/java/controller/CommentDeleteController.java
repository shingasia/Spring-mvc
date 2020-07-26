package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.comment.CommentDeleteService;
import spring.comment.CommentSearchService;

@Controller
public class CommentDeleteController {
	
	private CommentDeleteService commentDeleteSvc;

	public void setCommentDeleteSvc(CommentDeleteService commentDeleteSvc) {
		this.commentDeleteSvc = commentDeleteSvc;
	}
	
	//sell/list/sellContent로 다시 리다이렉트 한다.
	@RequestMapping(value="/sell/list/deleteComment", method= {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	public String deleteComment(
			@RequestParam(value="commentId") String commentId,
			@RequestParam(value="id") String loginId,
			@RequestParam(value="identifier") String identifier, RedirectAttributes ra) {
		
		commentDeleteSvc.deleteComment(commentId);//삭제
		ra.addAttribute("identifier", identifier);
		ra.addAttribute("id", loginId);
		return "redirect:/sell/list/sellContent";
	}
	
}
