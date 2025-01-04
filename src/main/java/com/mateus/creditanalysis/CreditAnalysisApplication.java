package com.mateus.creditanalysis;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.CreditAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@SpringBootApplication
public class CreditAnalysisApplication {

	private CreditAnalysisService creditAnalysisService;

	private Proposals proposals;

	public static void main(String[] args) {
		SpringApplication.run(CreditAnalysisApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args ->  {
			creditAnalysisService.analysis(proposals);
		};
	}
}
