package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import com.acelerati.management_service.infraestructure.output.entity.projections.ReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, Long>, SaleRepositoryCustom<SaleEntity> {
    @Query(value = "select inventory.id_inventory,SUM(amount) as amount, name as name_product , unit_price, sale_date  from  sale_inventory as saleInve inner join sale on saleInve.id_sale = sale.id_sale inner join inventory on inventory.id_inventory = saleInve.id_inventory where  sale_date  >= :dateBeforeSevenDays and  sale_date <= :dateNow group by id_inventory",nativeQuery = true)
    List<ReportProjection> getReport(@Param ("dateBeforeSevenDays") LocalDate dateBeforeSevenDays, @Param("dateNow") LocalDate dateNow);


}
