package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(nativeQuery = true , value = "SELECT obj.name AS sellerName " +
            ", SUM(sal.amount) AS total FROM tb_sales sal " +
            "INNER JOIN tb_seller obj ON sal.seller_id = obj.id " +
            "WHERE sal.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.name")
    List<SummaryProjection> search1(LocalDate minDate, LocalDate maxDate);

}
