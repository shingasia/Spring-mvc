package spring.basket;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.sell.SellVO;

@Service
public class BasketSearchService {
	
	private BasketDao basketDao;

	public BasketSearchService(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
	
	public List<SellVO> searchBaskets(String id){
		return basketDao.selectBasketsById(id);
	}
	
}
