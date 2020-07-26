package spring.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;

import config.DBConfig;

@Import({DBConfig.class})
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public void insert(CommentVO commentVO) {
		sqlSession.insert("CommentNS.insert", commentVO);
	}

	@Override
	public List<CommentVO> selectBySell(String identifier) {
		List<CommentVO> list=sqlSession.selectList("CommentNS.selectBySell", identifier);
		return list;
	}

	@Override
	public CommentVO selectByCommentId(String commentId) {
		CommentVO commentVO=sqlSession.selectOne("CommentNS.selectByCommentId", commentId);
		return commentVO;
	}

	@Override
	public void delete(CommentVO commentVO) {
		sqlSession.delete("CommentNS.delete", commentVO);
	}

	@Override
	public void deleteAll(String identifier) {
		sqlSession.delete("CommentNS.deleteAll", identifier);
	}

}
