package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {


	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public List<ReportDTO> getReport(@RequestParam(name = "minDate", defaultValue = "") String minDate,
									 @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
									 @RequestParam(name="name",defaultValue = "") String name) {

		return service.getReport(minDate,maxDate,name);
	}

	@GetMapping(value = "/summary")
	public List<SummaryDTO> getSummary(@RequestParam(name = "minDate", defaultValue = "") String minDate,
									   @RequestParam(name = "maxDate", defaultValue = "") String maxDate) {

		return service.getSummary(minDate,maxDate);
	}
}
