package spring.sell;

import org.springframework.transaction.annotation.Transactional;

import spring.exception.SellNotFoundException;

import java.sql.SQLException;

public class SellModifyService {
	
	private SellDaoImpl sellDao;
	
	public void setSellDao(SellDaoImpl sellDao) {
		this.sellDao = sellDao;
	}
	
	//인터셉터는 URL단위로 묶어서 처리하지만 트렌잭션은 메서드 단위로 묶어서 처리
	//별도의 설정을 추가하지 않으면 RuntimeException(UncheckedException)일 때 트랜잭션을 롤백한다.
	//@Transactional 의 속성으로 rollbackFor와 noRollbackFor가 있다
	@Transactional(rollbackFor= {SQLException.class})
	public void modifySell(SellModifyRequest sellModifyReq) {
		SellVO sellVO=sellDao.selectByIdentifier(sellModifyReq.getIdentifier());
		if(sellVO==null) {//수정하려는 게시물이 없으면 Exception발생
			throw new SellNotFoundException();
		}
		sellVO.setTitle(sellModifyReq.getTitle());
		sellVO.setLocation(sellModifyReq.getLocation());
		sellVO.setAddress(sellModifyReq.getAddress());
		sellVO.setUnit(sellModifyReq.getUnit());
		sellVO.setMoney(sellModifyReq.getMoney());
		sellVO.setSuggest(sellModifyReq.getSuggest());
		sellVO.setContent(sellModifyReq.getContent());
		
		sellDao.updateSell(sellVO);
	}
	
}
