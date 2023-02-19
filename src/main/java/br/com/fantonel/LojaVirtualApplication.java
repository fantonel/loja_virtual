package br.com.fantonel;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EntityScan(basePackages = "br.com.fantonel.model")
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = "br.com.fantonel.repository")
@EnableTransactionManagement
@EnableScheduling
public class LojaVirtualApplication implements AsyncConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualApplication.class, args);
	}
	
	@Override
	@Bean
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