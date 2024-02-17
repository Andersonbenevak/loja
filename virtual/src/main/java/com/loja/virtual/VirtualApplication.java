package com.loja.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EntityScan("com.loja.virtual.model")
@ComponentScan(basePackages = {"com.loja.virtual"})
@EnableJpaRepositories(basePackages = {"com.loja.virtual.repository"})
@EnableTransactionManagement
@SpringBootApplication
public class VirtualApplication {


      	
	public static void main(String[] args) {
		
		/*System.out.println(new BCryptPasswordEncoder().encode("123"));*/

		
		SpringApplication.run(VirtualApplication.class, args);
	}

}
