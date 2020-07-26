package spring.sell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spring.util.SellComparatorByDate;
import spring.util.SellComparatorByRate;
/*
	static <T> int	binarySearch(List<? extends Comparable<? super T>> list, T key)
	static <T> int	binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
*/
public class SellSearchService {
	
	private SellDaoImpl sellDao;

	public SellSearchService(SellDaoImpl sellDao) {
		this.sellDao = sellDao;
	}
	
	//게시물 전체 검색
	public List<SellVO> searchAll(){
		List<SellVO> list=sellDao.selectAll();
		Collections.sort(list, new SellComparatorByDate());
		return list;
	}
	//식별자로 게시물 1개 검색
	public SellVO searchByIdentifier(String identifier) {
		SellVO result=sellDao.selectByIdentifier(identifier);
		return result;
	}
	//제목으로 검색
	public List<SellVO> searchByTitle(String title){
		return sellDao.selectByTitle(title);
	}
	//아이디로 검색
	public List<SellVO> searchByMemberId(String id){
		List<SellVO> list=sellDao.selectByMemberId(id);
		return list;
	}
	//화폐 단위로 검색
	public List<SellVO> searchByUnit(String unit){
		if(unit.equals("ALL")) {
			return this.searchAll();
		}
		List<SellVO> list=sellDao.selectByUnit(unit);
		Collections.sort(list, new SellComparatorByRate());
		return list;
	}
	//화폐 단위로 검색(정렬 x)
	public List<SellVO> searchByUnit2(String unit){
		if(unit.equals("ALL")) {
			return this.searchAll();
		}
		List<SellVO> list=sellDao.selectByUnit(unit);
		return list;
	}
	//위치로 검색
	public List<SellVO> searchByLocation(String location){
		List<SellVO> list=sellDao.selectByLocation(location);
		return list;
	}
	
	
	
	
	//화폐의 종류가 몇개인지 리턴    <- 제대로 작동됨
	public int countByUnit() {
		return sellDao.unitCount();
	}
	//화폐의 종류를 리턴
	public List<String> searchUnits(){
		return sellDao.selectUnits();
	}
	
	//화폐의 종류별 평균 money를 저장해둔 리스트
	public List<Double> averagesOfMoney(){
		List<Double> list=new ArrayList<Double>();
		List<String> units=sellDao.selectUnits();
		for(int i=0;i<units.size();i++){
			list.add(Double.valueOf(sellDao.moneyAverage(units.get(i))));
		}
		return list;
	}
	//화폐의 종류별 평균 money를 저장해둔 리스트
	public List<Double> averagesOfSuggest(){
		List<Double> list=new ArrayList<Double>();
		List<String> units=sellDao.selectUnits();
		for(int i=0;i<units.size();i++){
			list.add(Double.valueOf(sellDao.suggestAverage(units.get(i))));
		}
		return list;
	}
	
	
	//myLocation과 각 거리를 계산해서 거리의 list를 리턴하는 메서드
	public List<Double> figureDistance(String unit, String myLocation){
		List<SellVO> list=sellDao.selectByUnit(unit);
		List<Double> distance=new ArrayList<>();
		String[] latlon2=myLocation.split(", ");//나의 위치
		for(int i=0;i<list.size();i++) {
			String[] latlon1=list.get(i).getLocation().split(", ");//위도, 경도
			double latitude1 = Double.parseDouble(latlon1[0]);
			double longitude1 = Double.parseDouble(latlon1[1]);
			
			double latitude2=Double.parseDouble(latlon2[0]);
			double longitude2=Double.parseDouble(latlon2[1]);
			distance.add(Math.sqrt((latitude1-latitude2)*(latitude1-latitude2)+(longitude1-longitude2)*(longitude1-longitude2)));
		}
		return distance;
	}
	
	//해당 화폐의 각 게시물의 환율(money/suggest)을 평균과의 차이를 계산해서 리턴 -> sellDao.moneyAverage(), suggestAverage() 사용
	public List<Double> figureRate(String unit){
		List<SellVO> list=sellDao.selectByUnit(unit);
		List<Double> rates=new ArrayList<>();
		double totalRate=sellDao.moneyAverage(unit) / sellDao.suggestAverage(unit);
		for(int i=0;i<list.size();i++) {
			double temp = (double)list.get(i).getMoney()/ (double)list.get(i).getSuggest();//게시물의 money/suggest
			// temp - totalRate 를 저장
			rates.add(temp-totalRate);//+에 가까울 수록 좋은거다
		}
		return rates;
	}
	
}
