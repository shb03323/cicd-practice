package practice.cicd.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@RequiredArgsConstructor
public class DbConfig {

    /**
     * application.yml 설정으로 자동등록되는 datasource
     */
    private final DataSource datasource;

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(datasource);
    }
}
