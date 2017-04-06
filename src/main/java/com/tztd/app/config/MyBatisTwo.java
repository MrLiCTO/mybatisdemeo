package com.tztd.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/4/6.
 */
@Configuration
@MapperScan(basePackages = "com.tztd.app.mapperTwo" , sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class MyBatisTwo {

    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /*@Bean(name = "transactionManagerTwo")
    public DataSourceTransactionManager transactionManagerTwo(@Qualifier("twoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "sqlSessionTemplateTwo")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
