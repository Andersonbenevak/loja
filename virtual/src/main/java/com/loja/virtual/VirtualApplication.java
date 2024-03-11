package com.loja.virtual;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EntityScan("com.loja.virtual.model")
@ComponentScan(basePackages = {"com.loja.virtual"})
@EnableJpaRepositories(basePackages = {"com.loja.virtual.repository"})
@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class VirtualApplication implements AsyncConfigurer {


      	
	public static void main(String[] args) {
		
		/*System.out.println(new BCryptPasswordEncoder().encode("123"));*/

		
		SpringApplication.run(VirtualApplication.class, args);
	}
	
	
	@Bean
	@Override
	public Executor getAsyncExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Assyncrono Thread");
		executor.initialize();
		
		return executor;
	}
	
	

}
