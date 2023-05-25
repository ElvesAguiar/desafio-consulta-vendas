package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(nativeQuery = true, value = "SELECT obj.name AS sellerName " +
            ", SUM(sal.amount) AS total FROM tb_sales sal " +
            "INNER JOIN tb_seller obj ON sal.seller_id = obj.id " +
            "WHERE sal.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.name")
    List<SummaryProjection> search1(LocalDate minDate, LocalDate maxDate);

    @Query(nativeQuery = true, value = "SELECT sal.id, sal.date, sal.amount, obj.name AS sellerName FROM tb_sales sal " +
            "INNER JOIN tb_seller obj ON sal.seller_id = obj.id " +
            "WHERE sal.date BETWEEN  :minDate AND :maxDate " +
            "AND UPPER(obj.name) LIKE UPPER(CONCAT('%' ,:name,'%')) ")
    List<ReportProjection> search2(LocalDate minDate, LocalDate maxDate, String name);


}
