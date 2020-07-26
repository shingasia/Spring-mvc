package spring.basket;

import org.springframework.stereotype.Service;

@Service
public class BasketDeleteService {
	
	private BasketDaoImpl basketDao;

	public BasketDeleteService(BasketDaoImpl basketDao) {
		this.basketDao = basketDao;
	}
	
	public void deleteBasket(BasketVO basketVO) {
		BasketVO obj=basketDao.selectBasket(basketVO);
		if(obj==null) {
			return;//나중에 Exception으로 바꾼다
		}
		basketDao.delete(basketVO);
	}
	
}
