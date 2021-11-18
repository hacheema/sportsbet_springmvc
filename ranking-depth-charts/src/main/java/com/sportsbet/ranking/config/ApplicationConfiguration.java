package com.sportsbet.ranking.config;

import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(
    basePackages = "com.sportsbet.ranking")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationConfiguration {
	


    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    /**
     * Data source reader. vv        
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        LOGGER.info("Calling dataSourceReader");
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(System.getenv("jdbc:h2:mem:testdb"));
        dataSource.setUsername(System.getenv("sa"));
        dataSource.setPassword(System.getenv("password"));
        return dataSource;
    }


}
