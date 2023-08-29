package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.utils.Utils;

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
	public ResponseEntity<Page<ReportDTO>> getReport(
			Pageable pageable, 
			@RequestParam(defaultValue = "", name = "minDate") String minDate, 
			@RequestParam(defaultValue = "", name = "maxDate") String maxDate, 
			@RequestParam(defaultValue = "", name = "name") String name) {
		
		LocalDate max;
		LocalDate min;

		if(maxDate.equals(""))
			max = Utils.currentDate();
		else
			max = Utils.convStringToLocalDate(maxDate);

		if(minDate.equals(""))
			min = Utils.dateMinusYear(max, 1l);
		else
			min = Utils.convStringToLocalDate(minDate);
		
		Page<ReportDTO> report = service.getReport(pageable, min, max, name);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(
			@RequestParam(defaultValue = "", name = "minDate") String minDate, 
			@RequestParam(defaultValue = "", name = "maxDate") String maxDate) {

		LocalDate max;
		LocalDate min;

		if(maxDate.equals(""))
			max = Utils.currentDate();
		else
			max = Utils.convStringToLocalDate(maxDate);

		if(minDate.equals(""))
			min = Utils.dateMinusYear(max, 1l);
		else
			min = Utils.convStringToLocalDate(minDate);
		
		List<SummaryDTO> summary = service.getSummary(min, max);
		return ResponseEntity.ok(summary);
	}
}
