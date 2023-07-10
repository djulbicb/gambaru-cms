package com.gambaru.gambaru.cms.application.port.out;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;

import java.util.List;

public interface CreateBarcodeUsecase {
    BarcodeEntity createNotUsedBarcode();
    List<BarcodeEntity> createManyNotUsedBarcode(int count);

}
