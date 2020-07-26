package spring.util;

import java.util.Comparator;

import spring.sell.SellVO;

//전체 게시물 조회(기본적으로 날짜로 내림차순 정렬)이 클래스를 다른 클래스가 extends??
public class SellComparatorByDate implements Comparator<SellVO>{

	@Override
	public int compare(SellVO o1, SellVO o2) {
		
		if(o1.getDate().compareTo(o2.getDate())>0) {
			return -1;
		}else if(o1.getDate().compareTo(o2.getDate())==0) {
			return 0;
		}else {
			return 1;
		}
		
	}
	
}
