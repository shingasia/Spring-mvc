package spring.comment;

public class CommentRegisterRequest {
	
	private String commentId;//댓글 식별자
	private String identifier;//게시물 식별자
	private String id;//회원 아이디
	//private String date;//날짜
	private String content;//내용
	
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
