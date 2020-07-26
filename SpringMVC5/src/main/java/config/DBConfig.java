package config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {//RootConfig
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		HikariConfig hikariConfig=new HikariConfig();
		hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mariadb://127.0.0.1:3306/spring");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("1234");
		
		HikariDataSource dataSource=new HikariDataSource(hikariConfig);
		
		return dataSource;
	}//DataSource를 스프링 빈으로 등록하고 DB연동 기능을 구현한 빈 객체는 DataSource객체를 주입받아 사용.
	
	
	
//	<!-- SqlsessionFactory setup for MyBatis Database Layer --> 
//	<bean id="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean"> 
//		<property name="dataSource" ref="dataSource"/> 
//		<!-- mybatis 기본설정 --> 
//		<property name="configLocation" value="classpath:/mybatis-config.xml"/> 
//		<!-- mapper 의 위치를 설정 --> 
//		<property name="mapperLocations" value="classpath:/mapper/*Mapper.xml"/> 
//	</bean>

	/*
		/src/main/java 와 /src/main/resources 폴더는 컴파일 되면 webapp/WEB-INF/classes 폴더 아래에 위치하게 됩니다.
		즉 classpath란 webapp/WEB-INF/classes 경로를 의미하게 됩니다.
		따라서 Mybatis 설정 파일은 /src/main/resources 폴더 아래에 작성해야 합니다.
	*/
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/mappers/*.xml"));
		
		//resources폴터 밑에 mybatis(mapper)패키지를 만든다
		//sqlSessionFactory.setConfigLocation(configLocation);"classpath:mybatis/mybatis-config.xml" 
		//sqlSessionFactory.setMapperLocations(mapperLocations);"classpath:mybatis/mapper/**/*.xml" />
		
//		sqlSessionFactory.setConfigLocation(new ClassPathResource("classpath:mybatis/config/mybatis-config.xml"));
//		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/*.xml"));
		
		//classpath가 무슨 경로일까??? 프로젝트 파일 우클릭 -> [properties] -> Deployment Assembly에서 볼 수 있다.
		
		return sqlSessionFactory.getObject();
	}
	
	
//	//옛날방식???
//	<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
//		<constructor-arg index="0" ref="sqlSession"/>
//	</bean>
	
//	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate"
//	    destroy-method="clearCache">
//	    <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"></constructor-arg>
//	</bean>
	
	//옛날방식???
	//SqlSessionTemplate은 SqlSession인터페이스를 상속받았다
	//SqlSessionTemplate는 개발자들이 DAO와 DB를 직접 연결 맺고, 종료할 필요가 없게 해줍니다!
	@Bean(destroyMethod="clearCache")
	@Qualifier("sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	
	//플랫폼 트랜잭션 매니저를 빈으로 등록
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm=new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
}
