package com.devsuperior.dsmeta.services;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {



	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SummaryDTO> getSummary(String minDate,String maxDate){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if(minDate.equals("")){
			LocalDate result = today.minusYears(1L);
			minDate= result.toString();
		}

		if(maxDate.equals("")){
			maxDate= today.toString();
		}
		List<SummaryProjection> result= repository.search1(LocalDate.parse(minDate),LocalDate.parse(maxDate));
		List<SummaryDTO> dto = result.stream().map(x -> new SummaryDTO(x)).toList();
		return dto;
	}
}
