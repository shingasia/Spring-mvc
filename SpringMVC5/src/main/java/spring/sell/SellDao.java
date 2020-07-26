package spring.sell;

import java.util.List;

public interface SellDao {
	
	public void insertSell(SellVO sellVO);//게시물 등록
	
	public List<SellVO> selectAll();//게시물 전체 검색
	public SellVO selectByIdentifier(String identifier);//식별자로 게시물 1개 검색
	public List<SellVO> selectByTitle(String title);//제목으로 검색
	public List<SellVO> selectByMemberId(String memberId);//회원이름으로 게시물 검색(내 게시물 목록)
	public List<SellVO> selectByUnit(String unit);//화폐 단위로 검색
	public List<SellVO> selectByLocation(String location);//특정 장소로 검색
	
	public int unitCount();//화폐 종류가 몇개인지 검색
	public double moneyAverage(String unit);//해당 화폐의 평균 money(판매할 금액)를 리턴
	public double suggestAverage(String unit);//해당 화폐의 평균 suggest(제시 가격)를 리턴
	
	
	//화폐의 종류 이름을 배열이나 List로 리턴
	//select distinct unit from sell;
	public List<String> selectUnits();
	
	public void updateSell(SellVO sellVO);//게시물 수정(게시물 번호를 이용해서 수정)
	public void deleteSell(SellVO sellVO);//게시물 삭제(게시물 번호를 이용해서 삭제)
	
}
