package spring.basket;

import org.springframework.stereotype.Service;

import spring.exception.DuplicationBasketException;

@Service
public class BasketRegisterService {
	
	private BasketDao basketDao;

	public BasketRegisterService(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
	
	public void registerBasket(BasketVO basketVO) {
		BasketVO temp=null;
		temp=basketDao.selectBasket(basketVO);
		
		if(temp!=null) {//이미 장바구니에 있으면 null을 리턴 -> 나중에 Exception으로 바꾼다
			throw new DuplicationBasketException();
		}
		basketDao.insert(basketVO);
	}
	
}
