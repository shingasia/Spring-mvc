package spring.mem;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;

import config.DBConfig;

//@Repository
//Mapper 인스턴스는 SqlSession의 getMapper() 메소드로 취득한다.
@Import({DBConfig.class})
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
//	public MemberDaoImpl(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
//	}
	
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public void insert(Member member) {
		sqlSession.update("MemberNS.insert", member);
	}
	
	@Override
	public Member selectById(String id) {
		Member member=sqlSession.selectOne("MemberNS.selectById", id);
		return member;
	}
	
	@Override
	public List<Member> selectAll() {
		List<Member> memberList=sqlSession.selectList("MemberNS.selectAll");
		return memberList;
	}

	@Override
	public void update(Member member) {
		sqlSession.update("MemberNS.update", member);
	}

	@Override
	public void delete(Member member) {
		sqlSession.delete("MemberNS.delete", member);
	}

}
