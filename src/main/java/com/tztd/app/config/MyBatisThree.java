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
@MapperScan(basePackages = "com.tztd.app.mapperThree" , sqlSessionTemplateRef = "sqlSessionTemplateThree")
public class MyBatisThree {

    @Bean(name = "sqlSessionFactoryThree")
    public SqlSessionFactory sqlSessionFactoryThree(@Qualifier("threeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /*@Bean(name = "transactionManagerThree")
    public DataSourceTransactionManager transactionManagerThree(@Qualifier("threeDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "sqlSessionTemplateThree")
    public SqlSessionTemplate sqlSessionTemplateThree(@Qualifier("sqlSessionFactoryThree") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
