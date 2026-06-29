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
        
        String dUrl = databaseUrl != null ? databaseUrl.trim() : "";
        String sUrl = springDatasourceUrl != null ? springDatasourceUrl.trim() : "";

        // Remove markdown backticks if the user accidentally copied them
        dUrl = dUrl.replace("`", "").replace("text", "").trim();
        sUrl = sUrl.replace("`", "").replace("text", "").trim();
        
        String targetUrl = !dUrl.isEmpty() && !dUrl.startsWith("jdbc:") ? dUrl : sUrl;

        // If we have a URL but it's not starting with the correct scheme, throw a very clear error
        if (targetUrl != null && !targetUrl.isEmpty() && !targetUrl.startsWith("jdbc:") 
            && !targetUrl.startsWith("postgres://") && !targetUrl.startsWith("mysql://")) {
            throw new IllegalArgumentException("\n\n❌ ERROR: Your DATABASE_URL or SPRING_DATASOURCE_URL is incorrect!\n" +
                "You entered: '" + targetUrl + "'\n" +
                "It MUST start with 'mysql://' or 'postgres://'. Please check your Render Environment Variables.\n\n");
        }

        // If deployed on Render with an external DB (like Clever Cloud MySQL or Neon PostgreSQL)
        if (targetUrl != null && !targetUrl.isEmpty() && 
            (targetUrl.startsWith("postgres://") || targetUrl.startsWith("mysql://"))) {
            
            URI dbUri = new URI(targetUrl);
            String username = dbUri.getUserInfo() != null ? dbUri.getUserInfo().split(":")[0] : springDatasourceUsername;
            String password = dbUri.getUserInfo() != null && dbUri.getUserInfo().contains(":") ? dbUri.getUserInfo().split(":")[1] : springDatasourcePassword;
            
            String jdbcPrefix = targetUrl.startsWith("postgres://") ? "jdbc:postgresql://" : "jdbc:mysql://";
            String dbUrl = jdbcPrefix + dbUri.getHost() + (dbUri.getPort() > 0 ? ":" + dbUri.getPort() : "") + dbUri.getPath();
            
            // Append necessary parameters for MySQL to prevent "Public Key Retrieval" and SSL errors
            if (jdbcPrefix.equals("jdbc:mysql://")) {
                dbUrl += "?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            }
            
            System.out.println("Initializing Database with URL: " + dbUrl + " and username: " + username);

            return DataSourceBuilder.create()
                    .url(dbUrl)
                    .username(username)
                    .password(password)
                    .driverClassName(targetUrl.startsWith("postgres://") ? "org.postgresql.Driver" : "com.mysql.cj.jdbc.Driver")
                    .build();
        }
        
        System.out.println("FALLBACK: Initializing Local Database with URL: " + springDatasourceUrl);
        // Fallback to default application.properties (e.g., Local MySQL)
        return DataSourceBuilder.create()
                .url(springDatasourceUrl)
                .username(springDatasourceUsername)
                .password(springDatasourcePassword)
                .build();
    }
}
