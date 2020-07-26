package spring.basket;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import config.DBConfig;
import spring.sell.SellVO;

@Import({DBConfig.class})
public class BasketDaoImpl implements BasketDao {
	
//	1. selectOne : 	쿼리 결과가 없으면 null을 반환
//    				쿼리 결과로 레코드가 하나만 나와야 한다
// 					DB에 레코드가 하나만 있는 경우에 사용 => 다수의 레코드가 있을 시 TooManyResultsException 에러 발생
//
//  2. selectList : 쿼리 결과를 List<E>로 반환 
//					결과가 없을 시에는 빈 List를 반환한다(null로 반환하지는 않는다)
	
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	
	@Override
	public void insert(BasketVO basketVO) {
		sqlSession.insert("BasketNS.insert", basketVO);
	}
	@Override
	public List<SellVO> selectBasketsById(String id) {
		List<BasketVO> bList=sqlSession.selectList("BasketNS.selectBasketsById", id);
		List<SellVO> sList=new ArrayList<SellVO>();
		for(int i=0;i<bList.size();i++) {
			//bList.get(i).getIdentifier()
			sList.add(sqlSession.selectOne("SellNS.selectByIdentifier", bList.get(i).getIdentifier()));
		}
		return sList;
	}
	@Override
	public BasketVO selectBasket(BasketVO basketVO) {
		return sqlSession.selectOne("BasketNS.selectBasket", basketVO);
	}
	
	@Override
	public void delete(BasketVO basketVO) {
		sqlSession.delete("BasketNS.delete", basketVO);
	}
	
	
}
