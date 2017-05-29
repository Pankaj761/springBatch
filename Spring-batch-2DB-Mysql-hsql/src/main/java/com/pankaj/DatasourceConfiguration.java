package com.pankaj;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfiguration {

	@Bean
    @ConfigurationProperties(prefix="spring.seconddatasource")
    public javax.sql.DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    // note the new name: dataSource -> this is the name springBatch is looking for
    @Bean
    @Primary
    @ConditionalOnMissingBean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public javax.sql.DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}


