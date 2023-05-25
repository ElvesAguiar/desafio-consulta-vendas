package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        List<SummaryDTO> summarys =
                repository.search1(LocalDate.parse("2022-01-01"), LocalDate.parse("2022-06-30"))
                        .stream().map(SummaryDTO::new).toList();

        summarys.forEach(System.out::println);

        List<ReportDTO> reports =
                repository.search2(LocalDate.parse("2022-05-01"), LocalDate.parse("2022-05-31"),"odinson")
                        .stream().map(ReportDTO::new).toList();

        summarys.forEach(System.out::println);

        System.out.println("\n\n\n");

        reports.forEach(System.out::println);

    }
}
