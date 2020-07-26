package spring.comment;

import spring.exception.CommentNotFoundException;

public class CommentDeleteService {
	
	private CommentDaoImpl commentDao;
	
	public void setCommentDao(CommentDaoImpl commentDao) {
		this.commentDao = commentDao;
	}
	
	public CommentVO deleteComment(String commentId) {
		CommentVO obj=commentDao.selectByCommentId(commentId);
		if(obj==null) {
			throw new CommentNotFoundException();
		}
		commentDao.delete(obj);
		return obj;
	}
	
}
