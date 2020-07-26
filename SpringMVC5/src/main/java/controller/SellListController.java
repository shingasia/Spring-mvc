package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.sell.SellSearchService;
import spring.mem.MemberSearchService;
import spring.sell.SellVO;
import spring.util.ExchangeRateCrawler;

@Controller//sellList, myList에 대한 응답 컨트롤러(요청 매핑 view 이름과 동일시 한다)
@RequestMapping(value="/sell/list/*", method= {RequestMethod.GET, RequestMethod.POST})
public class SellListController {
	
	private SellSearchService sellSearchSvc;
	private MemberSearchService memberSearchSvc;
	
	
	public SellListController(SellSearchService sellSearchSvc, MemberSearchService memberSearchSvc) {
		this.sellSearchSvc = sellSearchSvc;
		this.memberSearchSvc = memberSearchSvc;
	}
	
	//login -> sellList에 대한 요청처리
	@RequestMapping(value="/sellList", method= {RequestMethod.GET, RequestMethod.POST})
	public String sellList(Model model, @RequestParam("id") String id) {
		//List<SellVO> list=sellSearchSvc.searchAll();
		model.addAttribute("id", id);
		//model.addAttribute("list", list);
		return "sell/list/sellList";
	}
	
	
	
	//produces 속성은 생략 가능(URL에 확장자.json을 붙이면 데이터가 보인다)
	//application/json, application/xml 타입으로 리턴이 가능하다 produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE}
	//sellList페이지에서 ajax로 요청처리(JSON 배열을 리턴)
	@RequestMapping(value="/sellListByJSON", method= {RequestMethod.GET, RequestMethod.POST}, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SellVO> sellListByJSON(){
		List<SellVO> list=sellSearchSvc.searchAll();
		return list;
	}
	
	@RequestMapping(value="/sellListByJSON_Title", method= {RequestMethod.GET, RequestMethod.POST}, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SellVO> sellListByJSON_Title(@RequestParam("title") String title){
		return sellSearchSvc.searchByTitle(title);
	}
	
	@RequestMapping(value="/sellListByJSON_Unit", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SellVO> sellListByJSON_Unit(@RequestParam("unit") String unit){
		return sellSearchSvc.searchByUnit(unit);
	}
	
	//sell/list/crawlerByTEXT의 응답 처리
	@RequestMapping(value="/crawlerByTEXT", method= RequestMethod.GET)
	@ResponseBody
	public String crawlerByTEXT(@RequestParam("money") String money, @RequestParam("unit") String unit) {
		ExchangeRateCrawler obj=new ExchangeRateCrawler();
		return obj.getExchangeRate(money, unit.split(" ")[1]);//입력한 금액과 단위의 영어 부분을 파라미터로 전달;
	}
	
	//myList
	@RequestMapping(value="/myList", method= {RequestMethod.GET, RequestMethod.POST})
	public String myList(Model model, @RequestParam("id") String id) {
		List<SellVO> myList=sellSearchSvc.searchByMemberId(id);
		model.addAttribute("id", id);
		model.addAttribute("myList", myList);
		return "sell/list/myList";
	}
	
	
	//내용 확인
	@RequestMapping(value="/sellContent", method= {RequestMethod.GET, RequestMethod.POST})
	public String sellContent(
			Model model, HttpServletRequest request, 
			HttpServletResponse response, @ModelAttribute("message") String message) throws UnsupportedEncodingException {
		
		String identifier=request.getParameter("identifier");
		String id=request.getParameter("id");
		SellVO sellVO=sellSearchSvc.searchByIdentifier(identifier);
		model.addAttribute("sellVO", sellVO);
		model.addAttribute("id", id);
		
		
		boolean containCookie=false;
		
		Cookie[] cookies=request.getCookies();//웹 브라우저가 보낸 쿠키가 없으면  null을 리턴
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(URLDecoder.decode(cookies[i].getName(), "UTF-8").equals(identifier)) {
					containCookie=true;//쿠키가 1개이상 존재하고 그 중에서 해당 게시물에 대한 쿠키가 존재한다
				}
			}
		}
		/*
			쿠키의 이름은 아스키 코드의 알파벳과 숫자만을 포함할 수 있다.
			콤마(,), 세미콜론(;), 공백(' ') 등의 문자는 포함할 수 없다.
			출처: 	https://gangzzang.tistory.com/entry/JSP-%EC%BF%A0%ED%82%A4Cookie
					https://uoonleen.tistory.com/65
			
			new Cookie("c4", URLEncoder.encode("ABC가각간", "UTF-8")
			URLDecoder.decode(cookie.getValue(), "UTF-8"))
		*/
		
		
		//쿠키저장(identifier)
		if(cookies.length<10 || containCookie==true) {	//저장되어있는 쿠키가 10개 미만이거나 또는 해당 쿠키가 이미 있으면 추가
			Cookie c=new Cookie(URLEncoder.encode(identifier, "UTF-8"), URLEncoder.encode(identifier, "UTF-8"));
			c.setMaxAge(60*60);		//1시간
			c.setPath("/");			// 모든 경로에서 접근 가능 하도록 설정
			response.addCookie(c);	//추가 또는 갱신
		}
		return "sell/list/sellContent";
	}
	
	
	
	//sellList -> recommend 요청 처리 (id와 myLocation을려 준돌다)
	@RequestMapping(value="/recommend", method= RequestMethod.GET)
	public String recommendList(Model model, @RequestParam(value="id") String id) {
		model.addAttribute("id", id);
		model.addAttribute("myLocation", memberSearchSvc.searchMemberById(id).getLocation());
		model.addAttribute("myAddress", memberSearchSvc.searchMemberById(id).getAddress());
		return "sell/list/recommend";
	}
	
	//recommend -> recommend로 리턴 (id와 myLocation을려 준돌다)
	//request에 id, myLocation, unit, weight 파라미터가 있다
	@RequestMapping(value="/recommendSellOption", method=RequestMethod.GET)
	public String recommendListByOption(
			Model model, HttpServletRequest request, RedirectAttributes ra,
			@ModelAttribute("weight") String weight, @ModelAttribute("unit") String unit) {
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("myLocation", request.getParameter("myLocation"));
		
		//0일 경우 Error
		int rate=Integer.valueOf(request.getParameter("weight"));//30 -> 거리를 (100-30)으로 설정
		int distance=100-rate;
		if(rate==0) {
			rate=1;
		}
		if(distance==0) {
			distance=1;
		}
		try {
			
			List<SellVO> sells=sellSearchSvc.searchByUnit2(request.getParameter("unit"));
			//미터당 거리 1.0086067928842508E-5 [0.000010086067928842508]
			//해당 화폐의 각 게시물과 나 사이의 거리 리스트
			List<Double> disList=sellSearchSvc.figureDistance(request.getParameter("unit"), request.getParameter("myLocation"));
			//해당 화폐의 각 게시물의 (환율-평균환율) 리스트
			List<Double> rateList=sellSearchSvc.figureRate(request.getParameter("unit"));
			
			//25을 곱하고 비율(rate)을 곱한다
			for(int i=0;i<rateList.size();i++) {// i<disList.size() 도 된다
				rateList.set(i, (double)rate*25.0*rateList.get(i));
				disList.set(i, -distance*disList.get(i));//m당 마이너스 점수를 매긴다
			}
			HashMap<SellVO, Double> map =new HashMap<>();
			for(int i=0;i<sells.size();i++) {
				map.put(sells.get(i), disList.get(i)+rateList.get(i));//SellVO와 점수를 쌍으로 저장
			}
			//내림차순 정렬
			model.addAttribute("sellList", sortByValue(map));
			
			//unit으로 검색 -> distance와 rate를 고려해서 Comparator 작성
	//		model.addAttribute("disList", disList.toString());
	//		model.addAttribute("rateList", rateList.toString());
			model.addAttribute("myAddress", memberSearchSvc.searchMemberById(request.getParameter("id")).getAddress());
			return "sell/list/recommend";
		}catch(java.lang.NullPointerException e) {
			ra.addAttribute("id", request.getParameter("id"));
			ra.addAttribute("myLocation", memberSearchSvc.searchMemberById(request.getParameter("id")).getLocation());
			ra.addAttribute("myAddress", memberSearchSvc.searchMemberById(request.getParameter("id")).getAddress());
			return "redirect:/sell/list/recommend";
		}
	}
	public static List sortByValue(final HashMap<SellVO, Double> map) {
		List<SellVO> list=new ArrayList<>();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				Object v1=map.get(o1);
				Object v2=map.get(o2);
				return ((Comparable)v2).compareTo(v1); //내림차순
			}
			
		});
		//Collections.reverse(list);
		return list;
	}
	
}
