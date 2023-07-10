package com.gambaru.gambaru.cms.model;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;
import org.springframework.stereotype.Service;

@Service
public class BuildBarcodeUsecase {
    public BarcodeEntity buildNotUsedBarcode() {
        return BarcodeEntity.builder()
                .status(BarcodeEntity.Status.NOT_USED)
                .build();
    }
}
