package spring.comment;

import java.util.List;

public interface CommentDao {
	
	public void insert(CommentVO commentVO);//댓글 등록
	public List<CommentVO> selectBySell(String identifier);//게시물 식별자로 댓글 검색
	public CommentVO selectByCommentId(String commentId);//댓글 식별자(commentId)로 댓글 1개 검색
	public void delete(CommentVO commentVO);//댓글 1개 삭제
	public void deleteAll(String identifier);//게시물 식별자로 댓글 전부다 삭제
	
}
