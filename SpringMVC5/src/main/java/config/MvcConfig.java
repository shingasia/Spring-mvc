package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.LoginCheckInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    // Register resource handler for images
//	    registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
//	}
	
	//별다른 로직이 필요없이 단순히 URL 요청처리만 필요할 때 Controller를 다 만들면 힘들다. 따라서 다음과 같이 추가해준다
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/sell/list/sellList").setViewName("sell/list/sellList");
//	}
	
	/*
		Java EE에는 HTTP 요청에 대한 응답을 수행하는 HttpServlet(@WebServlet)이 존재하며 
		이러한 서블릿 실행 전후 시점에 임의의 처리를 가능하게 해주는 Filter(@WebFilter)가 존재한다. 
		필터는 체인 형태로 여러 개를 사용할 수 있다. 
		Spring Web MVC 또한 동일한 기능의 HandlerInterceptor를 제공한다. 
		Filter는 로우 레벨의 처리 로직을, HandlerInterceptor는 회원 인증 검사 등의 비즈니스 레벨의 처리 로직을 작성하는데 적합하다.
	 */
	
	
	//인터셉터 javaConfig 할 때 이렇게 밑에 계속 추가해 주면 된다
	@Bean
	public LoginCheckInterceptor loginCheckInterceptor() {
		return new LoginCheckInterceptor();
	}
	
//	====== Ant 패턴 ======
//	*: 0개 또는 그 이상의 글자
//	?: 1개 글자
//	**: 0개 또는 그 이상의 폴더 경로(파일 포함)
//	ex)
//	@RequestMapping("/member/?*.info"): /member/로 시작하고 확장자가 .info로 끝나는 모든 경로
//	@RequestMapping("/faq/f?00.fq"): /faq/f로 시작하고 1글자가 사이에 위치하고 있는 00.fq로 끝나는 모든 경로
//	@RequestMapping("/folders/**/files"): /folders/로 시작하고 중간에 0개 이상의 중간 경로가 존재하고 /files로 끝나는 모든 경로
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor()).addPathPatterns("/sell/**")
		.excludePathPatterns(new String[] {"/sell/**/sellListByJSON", "/sell/**/commentListByJSON/*"});
//		registry.addInterceptor(new LocaleInterceptor());
//	    registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
//	    registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
	}
	
	
	
}
