package controller.extra;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.sell.SellSearchService;
import spring.sell.SellVO;

@Controller
public class RecentlyVisitController {
	
	@Autowired
	private SellSearchService sellSearchSvc;
	
	//최근 방문했던 쿠키 배열을 리턴
	@RequestMapping(value="/sell/recently/recentlyVisit", method=RequestMethod.GET)
	public ModelAndView recentlyVisitPages(@RequestParam(value="id") String id, HttpServletRequest request) throws UnsupportedEncodingException {
		
		//쿠키배열
		Cookie[] cookies=request.getCookies();
		List<SellVO> record=new ArrayList<>();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				record.add(sellSearchSvc.searchByIdentifier(URLDecoder.decode(cookies[i].getName(), "UTF-8")));
			}
		}
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("record" , record);
		mav.addObject("id", id);
		mav.setViewName("sell/recently/recentlyVisit");
		return mav;
	}
	
	//쿠키 삭제
	@RequestMapping(value="/sell/recently/deleteRecords", method=RequestMethod.GET)
	public String deleteRecords(Model model,
			@RequestParam(value="id") String id, 
			HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies=request.getCookies();
		
		for(int i=0;i<cookies.length;i++) {
			cookies[i].setMaxAge(0);// 유효시간을 0으로 설정
			cookies[i].setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
			response.addCookie(cookies[i]);// 응답 헤더에 추가해서 없어지도록 함
		}
		model.addAttribute("id", id);
		return "sell/recently/recentlyVisit";
	}
	
	
}
