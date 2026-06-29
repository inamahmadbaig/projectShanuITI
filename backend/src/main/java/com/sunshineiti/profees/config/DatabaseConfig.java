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
        
        String targetUrl = databaseUrl;
        if (targetUrl == null || targetUrl.trim().isEmpty() || targetUrl.startsWith("jdbc:")) {
            targetUrl = springDatasourceUrl;
        }

        // If deployed on Render with an external DB (like Clever Cloud MySQL or Neon PostgreSQL)
        if (targetUrl != null && !targetUrl.trim().isEmpty() && 
            (targetUrl.startsWith("postgres://") || targetUrl.startsWith("mysql://"))) {
            
            URI dbUri = new URI(targetUrl);
            String username = dbUri.getUserInfo() != null ? dbUri.getUserInfo().split(":")[0] : springDatasourceUsername;
            String password = dbUri.getUserInfo() != null && dbUri.getUserInfo().contains(":") ? dbUri.getUserInfo().split(":")[1] : springDatasourcePassword;
            
            String jdbcPrefix = targetUrl.startsWith("postgres://") ? "jdbc:postgresql://" : "jdbc:mysql://";
            String dbUrl = jdbcPrefix + dbUri.getHost() + (dbUri.getPort() > 0 ? ":" + dbUri.getPort() : "") + dbUri.getPath();
            
            return DataSourceBuilder.create()
                    .url(dbUrl)
                    .username(username)
                    .password(password)
                    .build();
        }
        
        // Fallback to default application.properties (e.g., Local MySQL)
        return DataSourceBuilder.create()
                .url(springDatasourceUrl)
                .username(springDatasourceUsername)
                .password(springDatasourcePassword)
                .build();
    }
}
