package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.sell.SellDaoImpl;
import spring.sell.SellDeleteService;
import spring.sell.SellModifyService;
import spring.sell.SellRegisterService;
import spring.sell.SellSearchService;

@Configuration
public class SellConfig {
	
	@Bean
	public SellDaoImpl sellDao() {
		SellDaoImpl sellDaoImpl=new SellDaoImpl();
		return sellDaoImpl;
	}
	
	@Bean
	public SellRegisterService sellRegisterSvc() {
		SellRegisterService service=new SellRegisterService();
		service.setSellDao(sellDao());
		return service;
	}
	
	@Bean
	public SellSearchService sellSearchSvc() {
		return new SellSearchService(sellDao());
	}
	
	@Bean
	public SellDeleteService sellDeleteSvc() {
		SellDeleteService service=new SellDeleteService();
		service.setSellDao(sellDao());
		return service;
	}
	
	@Bean
	public SellModifyService sellModifySvc() {
		SellModifyService service=new SellModifyService();
		service.setSellDao(sellDao());
		return service;
	}
	
}
