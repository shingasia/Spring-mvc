package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import controller.AjaxTestController;
import controller.BasketListController;
import controller.CommentDeleteController;
import controller.CommentListController;
import controller.CommentRegisterController;
import controller.DeleteController;
import controller.JoinController;
import controller.LoginController;
import controller.LogoutController;
import controller.ModifyController;
import controller.RestAPITestController;
import controller.SellDeleteController;
import controller.SellListController;
import controller.SellModifyController;
import controller.SellRegisterController;
import controller.extra.RecentlyVisitController;
import controller.extra.TendencyController;
import spring.basket.BasketDeleteService;
import spring.basket.BasketRegisterService;
import spring.basket.BasketSearchService;
import spring.comment.CommentDaoImpl;
import spring.comment.CommentDeleteService;
import spring.comment.CommentRegisterService;
import spring.comment.CommentSearchService;
import spring.mem.MemberDeleteService;
import spring.mem.MemberJoinService;
import spring.mem.MemberSearchService;
import spring.mem.ModifyPasswordService;
import spring.sell.SellDeleteService;
import spring.sell.SellModifyService;
import spring.sell.SellRegisterService;
import spring.sell.SellSearchService;

@Configuration//XML설정을 포함하려면 @ImportResource({"classpath:/member-config.xml", "classpath:/sell-config.xml"}) locations속성 생략해도 됨
@Import({MemberConfig.class, SellConfig.class, CommentConfig.class, BasketConfig.class})
@ComponentScan(basePackages="spring.exception")
public class ControllerConfig {
	
	//Member
	@Autowired
	private MemberJoinService memberJoinSvc;
	@Autowired
	private MemberDeleteService memberDeleteSvc;
	@Autowired
	private ModifyPasswordService modifyPasswordSvc;
	@Autowired
	private MemberSearchService memberSearchSvc;
	
	
	//Sell
	@Autowired
	private SellRegisterService sellRegisterSvc;
	@Autowired
	private SellSearchService sellSearchSvc;
	@Autowired
	private SellDeleteService sellDeleteSvc;
	@Autowired
	private SellModifyService sellModifySvc;
	
	
	//Comment
	@Autowired
	private CommentRegisterService commentRegisterSvc;
	@Autowired
	private CommentSearchService commentSearchSvc;
	@Autowired
	private CommentDeleteService commentDeleteSvc;
	
	
	//Basket
	@Autowired
	private BasketRegisterService basketRegisterSvc;
	@Autowired
	private BasketSearchService basketSearchSvc;
	@Autowired
	private BasketDeleteService basketDeleteSvc;
	
	
	
	
	
	
	//Test <-RestAPI 공부하려고 테스트 하는 컨트롤러를 빈으로 설정
	@Bean
	public RestAPITestController restApiTestController() {
		return new RestAPITestController();
	}
	@Bean
	public AjaxTestController ajax() {
		return new AjaxTestController();
	}
	
	
	
	
	
	/*===========================================================================================*/
	
	@Bean
	public JoinController joinController() {
		JoinController controller=new JoinController();
		controller.setMemberJoinSvc(memberJoinSvc);
		return controller;
	}
	
	@Bean
	public DeleteController deleteController() {
		DeleteController controller=new DeleteController();
		controller.setMemberDeleteSvc(memberDeleteSvc);
		return controller;
	}
	
	@Bean
	public ModifyController modifyController() {
		return new ModifyController(modifyPasswordSvc);
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController(memberSearchSvc);
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	
	/*===========================================================================================*/
	
	
	@Bean
	public SellRegisterController sellRegisterController() {
		SellRegisterController controller = new SellRegisterController();
		controller.setSellRegisterSvc(sellRegisterSvc);
		return controller;
	}
	
	@Bean
	public SellListController listController() {
		return new SellListController(sellSearchSvc, memberSearchSvc);
	}
	
	@Bean
	public SellDeleteController sellDeleteController() {
		return new SellDeleteController(sellDeleteSvc, sellSearchSvc);
	}
	
	@Bean
	public SellModifyController sellModifyController() {
		SellModifyController controller=new SellModifyController(sellSearchSvc, sellModifySvc);
		return controller;
	}
	
	
	@Bean
	public TendencyController tendencyController() {
		return new TendencyController(sellSearchSvc);
	}
	
	@Bean
	public RecentlyVisitController recentlyVisitController() {
		return new RecentlyVisitController();
	}
	
	
	/*===========================================================================================*/
	@Bean
	public CommentRegisterController commentRegisterController() {
		return new CommentRegisterController(commentRegisterSvc);
	}
	
	@Bean
	public CommentListController commentListController() {
		CommentListController controller=new CommentListController();
		controller.setCommentSearchSvc(commentSearchSvc);
		return controller;
	}
	@Bean
	public CommentDeleteController commentDeleteController() {
		CommentDeleteController controller=new CommentDeleteController();
		controller.setCommentDeleteSvc(commentDeleteSvc);
		return controller;
	}
	
	
	/*===========================================================================================*/
	
	
	@Bean
	public BasketListController basketListController() {
		return new BasketListController(basketSearchSvc, basketRegisterSvc, basketDeleteSvc);
	}
	
}
