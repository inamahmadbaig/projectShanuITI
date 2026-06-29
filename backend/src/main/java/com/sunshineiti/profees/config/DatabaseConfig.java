package com.sunshineiti.profees.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Value("${spring.datasource.url:}")
    private String springDatasourceUrl;

    @Value("${spring.datasource.username:}")
    private String springDatasourceUsername;

    @Value("${spring.datasource.password:}")
    private String springDatasourcePassword;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        
        String hardcodedUrl = "jdbc:mysql://bdpaui3unqbl6yfhnky5-mysql.services.clever-cloud.com:3306/bdpaui3unqbl6yfhnky5?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String hardcodedUser = "u7m72momrlv7ddx3";
        String hardcodedPass = "WVYMjj4c1SCJol3YIB88";
        
        System.out.println("FORCING CONNECTION TO CLEVER CLOUD: " + hardcodedUrl);
        
        return DataSourceBuilder.create()
                .url(hardcodedUrl)
                .username(hardcodedUser)
                .password(hardcodedPass)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
