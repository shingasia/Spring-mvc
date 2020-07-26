package spring.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentRegisterService {
	
	private CommentDaoImpl commentDao;

	public void setCommentDao(CommentDaoImpl commentDao) {
		this.commentDao = commentDao;
	}
	
	public void registerComment(CommentRegisterRequest commentRequest) {
		
		LocalDateTime dateTime=LocalDateTime.now();
		
		CommentVO obj=new CommentVO();
		obj.setDate(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		obj.setIdentifier(commentRequest.getIdentifier());//게시물의 식별자
		obj.setId(commentRequest.getId());
		obj.setCommentId(obj.getId()+obj.getDate());//게시물은 [날짜+아이디] 댓글은 [아이디+날짜]
		obj.setContent(commentRequest.getContent());
		
		commentDao.insert(obj);
	}
	
}
