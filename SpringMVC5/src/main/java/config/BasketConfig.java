package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.basket.BasketDao;
import spring.basket.BasketDaoImpl;
import spring.basket.BasketDeleteService;
import spring.basket.BasketRegisterService;
import spring.basket.BasketSearchService;

@Configuration
public class BasketConfig {
	
	@Bean
	public BasketDaoImpl basketDao() {
		return new BasketDaoImpl();
	}
	
	@Bean
	public BasketSearchService basketSearchSvc() {
		return new BasketSearchService(basketDao());
	}
	
	@Bean
	public BasketRegisterService basketRegisterSvc() {
		return new BasketRegisterService(basketDao());
	}
	
	@Bean
	public BasketDeleteService basketDeleteSvc() {
		return new BasketDeleteService(basketDao());
	}
}
