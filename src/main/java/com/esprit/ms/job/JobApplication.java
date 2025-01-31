package com.esprit.ms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}

	@Autowired
	private JobRepository jobRepository;


	@Bean
	ApplicationRunner init() {
		return (args) -> {
// save
			jobRepository.save(new Job("job 1","Mariem", false));
			jobRepository.save(new Job("job 2","Sarra", true));
			jobRepository.save(new Job("job 3","Mohamed", true));
			jobRepository.save(new Job("job 4","Maroua", false));

// fetch
			jobRepository.findAll().forEach(System.out::println);

		};

	}
	}


