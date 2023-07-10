package com.gambaru.gambaru.cms.application.port.in;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;

import java.util.List;

public interface FetchBarcodeByStatusUsecase {
    BarcodeEntity fetchFirstbyStatusNotUsed();
    List<BarcodeEntity> fetchManybyStatusNotUsed(int count);
}
