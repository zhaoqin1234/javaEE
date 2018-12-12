package com.hrxt.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ApplicationMybatisConfig {
	
	//这里在测试的时候使用Autowired的方式无法注入
	//@Autowired
	//DataSource dataSource;
	//以参数传进的方式，springboot会根据引用自动地注入dataSource
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		//ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//Resource[] mapperXmlResource =  resolver.getResources("classpath:mapper/*/*Mapper.xml");
		
		sqlSessionFactory.setDataSource(dataSource);
		//sqlSessionFactory.setMapperLocations(mapperXmlResource);
		sqlSessionFactory.setTypeAliasesPackage("com.hrxt.pojo");
		return sqlSessionFactory;
	}
	
/*	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		
		msc.setBasePackage("boot.dao");
		msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		
		return msc;
		
	}*/
	

}
