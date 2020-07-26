package spring.basket;

import java.util.List;

import spring.sell.SellVO;

public interface BasketDao {
	
	public void insert(BasketVO basketVO);//장바구니 추가
	public List<SellVO> selectBasketsById(String id);//아이디로 장바구니 리스트 검색
	public BasketVO selectBasket(BasketVO basketVO);
	public void delete(BasketVO basketVO);//장바구니 1개 삭제
	
}
