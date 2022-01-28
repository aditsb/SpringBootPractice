package com.springboot.practice.core.batch.csvtodb.config;

import com.springboot.practice.core.batch.csvtodb.vo.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Configuration
public class CSVToDBBatchConfiguration {
    @Autowired
    private StepBuilderFactory productStepBuilderFactory;
    @Autowired
    private JobBuilderFactory productJobBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Bean(name = "productBatchJob")
    public Job job() {
        return productJobBuilderFactory.get("Product Job 1").incrementer(new RunIdIncrementer()).start(step()).build();

    }

    @Bean(name = "productBatchStep")
    public Step step() {
        return productStepBuilderFactory.get("step 1").<Product, Product>chunk(1)
                .reader(reader())
                .writer(writer())
                .processor(processor()).build();

    }

    @Bean(name = "productBatchReader")
    public ItemReader<Product> reader() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("product.csv"));
        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer productTokenizer = new DelimitedLineTokenizer();
        productTokenizer.setNames("id", "name", "description", "price");
        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Product.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(productTokenizer);

        reader.setLineMapper(lineMapper);
        return reader;

    }

    @Bean(name = "productBatchWriter")
    public ItemWriter<Product> writer() {
        JdbcBatchItemWriter<Product> productJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        productJdbcBatchItemWriter.setDataSource(dataSource);
        productJdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
        productJdbcBatchItemWriter.setSql("Insert into product (ID,name,description,price)values (:id,:name,:description,:price)");
        return productJdbcBatchItemWriter;
    }

    @Bean(name = "productBatchProcessor")
    public ItemProcessor<Product, Product> processor() {
        return (Product p) -> {
            p.setPrice(p.getPrice() - (p.getPrice() * 10) / 100);
            return p;
        };


    }



}
