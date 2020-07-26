package spring.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExchangeRateCrawler {
	
	//파라미터로 금액을 받아서 한화로 얼마인지 리턴해주는 메서드
	public String getExchangeRate(String money, String unit) {
		
		try {
			Document doc=Jsoup.connect("https://okbfex.kbstar.com/quics?page=C015690#loading").get();
//			String title=doc.title();
//			System.out.println(title);
			
			Element root=doc.body();
			Elements data= root.select("div > table > tbody[id=\"AllDsp1\"] > tr > td[scope=\"row\"] + td");
			Elements nation = root.select("div > table > tbody[id=\"AllDsp1\"] > tr > td[scope=\"row\"] > a");
			
			double result;
			
			for(int i=0; i< data.size() && i<nation.size();i++) {
				if(nation.get(i).text().contains(unit)) {//unit == USD와 같은 영어
//					System.out.println(nation.get(i).text() + " : "+data.get(i).text());
					//USD(미국 달러) : 1,170.00
					
					//만약에 JPY, IDR, VND 이면 100으로 나눠서 리턴, 그리고 숫자 가운데 ',' 제거
					if(unit.equals("JPY") || unit.equals("IDR") || unit.equals("VND")) {
						result=Double.valueOf(money) * Double.valueOf(data.get(i).text().replace(",", "")) / 100;//금액X단위
					}else {
						result=Double.valueOf(money) * Double.valueOf(data.get(i).text().replace(",", ""));//금액X단위
					}
					return result+" KRW";//문자열로 리턴
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";//없으면 빈 문자열 리턴
	}
	
}
