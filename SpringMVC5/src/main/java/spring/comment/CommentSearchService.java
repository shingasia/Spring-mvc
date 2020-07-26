package spring.comment;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring.util.CommentComparator;

public class CommentSearchService {
	
	private CommentDaoImpl commentDao;
	@Autowired
	private CommentComparator commentComparator;
	
	public void setCommentDao(CommentDaoImpl commentDao) {
		this.commentDao = commentDao;
	}
	
	public List<CommentVO> searchCommentsBySell(String identifier){
		List<CommentVO> list=commentDao.selectBySell(identifier);
		Collections.sort(list, commentComparator);
		return list;
	}
	
}
