package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
@Slf4j
@EnableTransactionManagement
public class AppConfig {

    // ВЕСЬ КОД НИЖЕ ДО ПОСЛЕДНЕЙ } МОЖНО ЗАКОММНТИРОВАТЬ - Spring Boot ИМЕЕТ АВТОКОНФИГУРИРОВАНЕ
    // ПОЭТОМУ САМ СПРАВИТСЯ С НАСТРОЙКОЙ ПУЛА КОННЕКШЕНОВ И РОЖДЕНИЕМ DataSource
    // ИСПОЛЬЗУЯ ЛИЩЬ ДАННЫЕ НАСТРОЕК ИЗ ФАЙЛА application.properties

    @Bean
    Driver driver(@Value("${spring.datasource.driver-class-name}") String driverClassName) throws ClassNotFoundException  {
        try {
            return (Driver) Class.forName(driverClassName)
                    .getConstructor()
                    .newInstance();
        } catch (InvocationTargetException
                 | NoSuchMethodException
                 | IllegalAccessException
                 | InstantiationException e){
            throw new RuntimeException(e);
        }
    }

    @Bean
    DataSource dataSource(
            @Value("${spring.datasource.url}") String dbUrl,
            @Value("${spring.datasource.user}") String dbUser,
            @Value("${spring.datasource.password}") String dbPassword
    ) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        config.setPoolName("myPool");
        return new HikariDataSource( config );
    }

}
