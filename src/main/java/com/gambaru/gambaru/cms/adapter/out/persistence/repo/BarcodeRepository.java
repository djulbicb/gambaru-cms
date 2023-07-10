package com.gambaru.gambaru.cms.adapter.out.persistence.repo;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BarcodeRepository extends CrudRepository<BarcodeEntity, Long> {
    @Query(value = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'gambaru' AND TABLE_NAME = 'barcode'", nativeQuery = true)
    Long getNextId();
    Boolean existsByBarcodeId(Long barcodeId);
    List<BarcodeEntity> findByStatus(BarcodeEntity.Status status, Pageable pageable);
    Page<BarcodeEntity> findAllByStatus(BarcodeEntity.Status status, Pageable pageable);
    Optional<BarcodeEntity> findFirstByStatus(BarcodeEntity.Status status);;

}
