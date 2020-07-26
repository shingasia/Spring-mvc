package spring.util;

import java.util.Comparator;

import spring.sell.SellVO;

public class SellComparatorByRate implements Comparator<SellVO> {
	
	// suggest/money 의 값이 낮은게 사용자에게는 좋다
	@Override
	public int compare(SellVO o1, SellVO o2) {
		double rate1=o1.getSuggest()/o1.getMoney();
		double rate2=o2.getSuggest()/o2.getMoney();
		
		if(rate1>rate2) {
			return 1;
		}else if(rate1==rate2) {
			return o2.getMoney()-o1.getMoney();
		}else {
			return -1;
		}
	}
	
}
