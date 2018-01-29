package com.blogger.datasource.local;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "spring.datasource")
@MapperScan(basePackages = "com.blogger.mapper", sqlSessionTemplateRef  = "localSqlSessionTemplate")
public class LocalDataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Autowired
    ApplicationArguments args;

    @Bean(name = "localDataSource")
    @Primary
    public DataSource getDataSource(){
        System.out.println("-------初始化druid------");
        DruidDataSource datasource = new DruidDataSource();
//
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

//    @Bean(name = "localDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.local")
//    @Primary
    public DataSource localDataSource() {


        return DataSourceBuilder.create().build();
    }

    @Bean(name = "localSqlSessionFactory")
    @Primary
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.blogger.model");

        String tConfigPath;
        List<String> argList = args.getNonOptionArgs();
        Resource location = null;
        if ((1 <= argList.size()) && (argList.get(0).equals("debug"))){
            location = new ClassPathResource("/mybatis.xml");
        }else if (1 <= argList.size()) {
            tConfigPath = "file:///" + argList.get(0) + "/mybatis.xml";
            ResourceLoader loader = new DefaultResourceLoader();
            location = loader.getResource(tConfigPath);
        }

        bean.setConfigLocation(location);
        return bean.getObject();
    }

    @Bean(name = "localTransactionManager")
    @Primary
    public DataSourceTransactionManager localTransactionManager(@Qualifier("localDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "localSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("localSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
