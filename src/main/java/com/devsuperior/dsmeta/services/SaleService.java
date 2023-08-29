package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
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
	
	public List<SummaryDTO> getSummary(LocalDate min, LocalDate max) {
		List<SummaryDTO> result = repository.getSummary(min, max);
		return result;
	}
	
	public Page<ReportDTO> getReport(Pageable pageable, LocalDate min, LocalDate max, String name) {
		Page<Sale> sales = repository.getReport(pageable, min, max, name);
		
		return sales.map(sale -> {
			return new ReportDTO(sale);
		});
	}
}
