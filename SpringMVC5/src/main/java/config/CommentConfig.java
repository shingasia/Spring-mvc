package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.comment.CommentDaoImpl;
import spring.comment.CommentDeleteService;
import spring.comment.CommentRegisterService;
import spring.comment.CommentSearchService;
import spring.util.CommentComparator;

@Configuration
public class CommentConfig {
	
	@Bean
	public CommentDaoImpl commentDao() {
		CommentDaoImpl commentDaoImpl=new CommentDaoImpl();
		return commentDaoImpl;
	}
	
	@Bean
	public CommentRegisterService commentRegisterService() {
		CommentRegisterService service=new CommentRegisterService();
		service.setCommentDao(commentDao());
		return service;
	}
	@Bean
	public CommentSearchService commentSearchService() {
		CommentSearchService service=new CommentSearchService();
		service.setCommentDao(commentDao());
		return service;
	}
	
	@Bean
	public CommentDeleteService commentDeleteService() {
		CommentDeleteService service=new CommentDeleteService();
		service.setCommentDao(commentDao());
		return service;
	}
	@Bean
	public CommentComparator commentComparator() {
		return new CommentComparator();
	}
	
}
