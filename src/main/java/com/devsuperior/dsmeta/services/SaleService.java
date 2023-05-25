package com.devsuperior.dsmeta.services;


import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public List<SummaryDTO> getSummary(String minDate, String maxDate) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        minDate = minDate.isEmpty() ? today.minusYears(1L).toString() : minDate;

        maxDate = maxDate.isEmpty() ? today.toString() : maxDate;

        List<SummaryProjection> result = repository.search1(LocalDate.parse(minDate), LocalDate.parse(maxDate));

        List<SummaryDTO> summarys = result.stream().map(SummaryDTO::new).toList();


        return summarys;
    }

    public Page<ReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        minDate = minDate.isEmpty() ? today.minusYears(1L).toString() : minDate;

        maxDate = maxDate.isEmpty() ? today.toString() : maxDate;

        List<ReportProjection> result = repository.search2(LocalDate.parse(minDate), LocalDate.parse(maxDate), name);
        List<ReportDTO> reports = result.stream().map(ReportDTO::new).toList();

        Page<ReportDTO> reportPage = new PageImpl<>(reports, pageable, pageable.getPageNumber());

        return reportPage;

    }
}
