package spring.sell;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SellRegisterService {

	private SellDaoImpl sellDao;

	public void setSellDao(SellDaoImpl sellDao) {
		this.sellDao = sellDao;
	}
	
	
	public void registerSell(SellRegisterRequest sellRequest) {
		
		LocalDateTime dateTime=LocalDateTime.now();
		
		
		SellVO obj=new SellVO();
		obj.setTitle(sellRequest.getTitle());
		obj.setId(sellRequest.getId()); //id값은 로그인 완료 후 게시물 등록 페이지로 갈 때 hidden 데이터로 넘겨준다
		obj.setDate(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		obj.setLocation(sellRequest.getLocation());
		obj.setAddress(sellRequest.getAddress());
		obj.setUnit(sellRequest.getUnit());
		obj.setMoney(sellRequest.getMoney());
		obj.setSuggest(sellRequest.getSuggest());
		obj.setContent(sellRequest.getContent());
		obj.setIdentifier(obj.getDate()+obj.getId());//날짜+아이디
		
		sellDao.insertSell(obj);
		
	}
	
}
