package spring.sell;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;

import config.DBConfig;

@Import({DBConfig.class})
public class SellDaoImpl implements SellDao {
	
//	1. selectOne : 쿼리 결과가 없으면 null을 반환
//				      쿼리 결과로 레코드가 하나만 나와야 한다
//				   DB에 레코드가 하나만 있는 경우에 사용 => 다수의 레코드가 있을 시 TooManyResultsException 에러 발생
//
//	2. selectList : 쿼리 결과를 List<E>로 반환 
//					결과가 없을 시에는 빈 List를 반환한다(null로 반환하지는 않는다)

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	@Override
	public void insertSell(SellVO sellVO) {
		sqlSession.update("SellNS.insertSell", sellVO);
	}
	@Override
	public List<SellVO> selectAll() {
		List<SellVO> list=sqlSession.selectList("SellNS.selectAll");
		return list;
	}
	@Override
	public SellVO selectByIdentifier(String identifier) {
		SellVO sellVO=sqlSession.selectOne("SellNS.selectByIdentifier", identifier);
		return sellVO;
	}
	@Override
	public List<SellVO> selectByTitle(String title) {
		List<SellVO> list=sqlSession.selectList("SellNS.selectByTitle", title);
		return list;
	}
	@Override
	public List<SellVO> selectByMemberId(String memberId) {
		List<SellVO> list=sqlSession.selectList("SellNS.selectByMemberId", memberId);
		return list;
	}
	@Override
	public List<SellVO> selectByUnit(String unit) {
		List<SellVO> list=sqlSession.selectList("SellNS.selectByUnit", unit);
		return list;
	}
	@Override
	public List<SellVO> selectByLocation(String location) {
		List<SellVO> list=sqlSession.selectList("SellNS.selectByLocation", location);
		return list;
	}
	
	
	
	
	//현재 계시판에 올라와있는 화폐의 종류가 몇개인지 리턴
	@Override
	public int unitCount() {
		return (Integer)sqlSession.selectOne("SellNS.unitCount");
	}
	//단위별로 money의 평균을 리턴
	@Override
	public double moneyAverage(String unit) {
		return (Double)sqlSession.selectOne("SellNS.moneyAverage", unit);
	}
	//단위별로 suggest의 평균을 리턴
	@Override
	public double suggestAverage(String unit) {
		return (Double)sqlSession.selectOne("SellNS.suggestAverage", unit);
	}
	
	
	@Override
	public List<String> selectUnits() {
		List<String> list=sqlSession.selectList("SellNS.selectUnits");
		return list;
	}
	
	
	
	
	@Override
	public void updateSell(SellVO sellVO) {
		sqlSession.update("SellNS.updateSell", sellVO);
	}

	@Override
	public void deleteSell(SellVO sellVO) {
		sqlSession.delete("SellNS.deleteSell", sellVO);
	}

}
