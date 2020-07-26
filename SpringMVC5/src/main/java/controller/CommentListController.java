package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.comment.CommentSearchService;
import spring.comment.CommentVO;

@RestController
public class CommentListController {
	
	private CommentSearchService commentSearchSvc;
	
	public void setCommentSearchSvc(CommentSearchService commentSearchSvc) {
		this.commentSearchSvc = commentSearchSvc;
	}
	/*
		//수정
		@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{bno}",
			consumes="application/json",
			produces={ MediaType.TEXT_PLAIN_VALUE})
	 */
	//JSON 데이터 배열 리턴
	@GetMapping(value="/sell/list/commentListByJSON/{identifier}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})//@PathVariable
	public List<CommentVO> commentList(@PathVariable("identifier") String identifier){
		return commentSearchSvc.searchCommentsBySell(identifier);
	}
	
}
