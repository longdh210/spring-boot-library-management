// package com.example.library_management.service.Processor;

// import javax.sql.DataSource;
// import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import org.springframework.batch.core.job.Job;
// import org.springframework.batch.core.job.builder.JobBuilder;
// import org.springframework.batch.core.repository.JobRepository;
// import org.springframework.batch.core.step.Step;
// import org.springframework.batch.core.step.builder.StepBuilder;
// import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
// import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
// import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
// import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import com.example.library_management.dto.AuthorDto;

// @Configuration
// public class BatchConfiguration {
// @Bean
// public FlatFileItemReader<AuthorDto> reader() {
// return new FlatFileItemReaderBuilder<AuthorDto>().name("authorItemReader")
// .resource(new ClassPathResource("author.csv")).delimited().names("name", "dayOfBirth")
// .targetType(AuthorDto.class).build();
// }

// @Bean
// public AuthorItemProcessor processor() {
// return new AuthorItemProcessor();
// }

// @Bean
// public JdbcBatchItemWriter<AuthorDto> writer(DataSource dataSource) {
// return new JdbcBatchItemWriterBuilder<AuthorDto>()
// .sql("INSERT INTO author (name, dayOfBirth) VALUES (:name, :dayOfBirth)")
// .dataSource(dataSource).beanMapped().build();
// }

// @Bean
// public Job importUserJob(JobRepository jobRepository, Step step1,
// JobCompletionNotificationListener listener) {
// return new JobBuilder(jobRepository).listener(listener).start(step1).build();
// }

// @Bean
// public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
// FlatFileItemReader<AuthorDto> reader, AuthorItemProcessor processor,
// JdbcBatchItemWriter<AuthorDto> writer) {
// return new StepBuilder(jobRepository).<AuthorDto, AuthorDto>chunk(3)
// .transactionManager(transactionManager).reader(reader).processor(processor).writer(writer)
// .build();
// }
// }
