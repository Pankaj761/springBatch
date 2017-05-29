package com.pankaj;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableBatchProcessing
@Import(DatasourceConfiguration.class)
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // note the name
    @Autowired
    public DatasourceConfiguration secondaryDataSource;


    @Bean
    public JdbcCursorItemReader<User> reader()
    {
        JdbcCursorItemReader<User> reader=new JdbcCursorItemReader<>();

        // note the name
        reader.setDataSource(secondaryDataSource.secondaryDataSource());
        reader.setSql("Select first_name from employee");
        reader.setRowMapper(new UserRowMapper());
        return reader;
    }
    
    @Bean
    public UserItemProcessor processor()
    {
        return new UserItemProcessor();
    }

    @Bean
    public Step step1()
    {
        return stepBuilderFactory.get("step1").<User,User>chunk(10)
                .reader(reader())
                .processor(processor())
                .build();
    }

    @Bean
    public Job job1()
    {
        return jobBuilderFactory.get("jobakjkkj")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();

    }

}