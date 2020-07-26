package spring.sell;

import spring.exception.SellNotFoundException;

public class SellDeleteService {
	
	private SellDaoImpl sellDao;

	public void setSellDao(SellDaoImpl sellDao) {
		this.sellDao = sellDao;
	}
	
	
	public SellVO deleteSellByIdentifier(String identifier) {
		SellVO obj=sellDao.selectByIdentifier(identifier);
		if(obj==null) {
			throw new SellNotFoundException();
		}
		sellDao.deleteSell(obj);//삭제
		return obj;
	}
	
}
