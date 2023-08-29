package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj WHERE obj.date BETWEEN :min AND :max GROUP BY obj.seller.name")
	List<SummaryDTO> getSummary(LocalDate min, LocalDate max);
	
	@Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj.date BETWEEN :min AND :max AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')", 
			countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller WHERE obj.date BETWEEN :min AND :max AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
	Page<Sale> getReport(Pageable page, LocalDate min, LocalDate max, String name);
	
}
