package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner {

	@Autowired
	SaleRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				List<SummaryProjection> list =
				repository.search1(LocalDate.parse("2022-01-01"),LocalDate.parse("2022-06-30"));
				List<SummaryDTO> result = list.stream().map(x -> new SummaryDTO(x)).toList();
				for (SummaryDTO obj : result){
					System.out.println(obj);
				}


	}
}
