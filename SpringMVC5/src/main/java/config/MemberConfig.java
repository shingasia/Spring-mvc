package config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.mem.MemberDao;
import spring.mem.MemberDaoImpl;
import spring.mem.MemberDeleteService;
import spring.mem.MemberJoinService;
import spring.mem.MemberSearchService;
import spring.mem.ModifyPasswordService;

@Configuration
@Import(DBConfig.class)
public class MemberConfig {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	@Bean
	public MemberDaoImpl memberDao() {
		MemberDaoImpl memberDaoImpl=new MemberDaoImpl();
		memberDaoImpl.setSqlSession(sqlSession);
		return memberDaoImpl;
	}
	
	@Bean//생성자 주입
	public MemberJoinService memberJoinSvc() {
		MemberJoinService service=new MemberJoinService(memberDao());
		return service;
	}
	
	@Bean//setter 주입
	public MemberDeleteService memeberDeleteSvc() {
		MemberDeleteService service=new MemberDeleteService();
		service.setMemberDao(memberDao());
		return service;
	}
	
	@Bean//setter 주입
	public ModifyPasswordService modifyPasswordSvc() {
		ModifyPasswordService service=new ModifyPasswordService();
		service.setMemberDao(memberDao());
		return service;
	}
	
	@Bean//생성자 주입
	public MemberSearchService memberSearchSvc() {
		return new MemberSearchService(memberDao());
	}
	
}
