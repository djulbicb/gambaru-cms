package com.gambaru.gambaru.cms.model;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.BarcodeRepository;
import com.gambaru.gambaru.cms.application.port.in.FetchBarcodeByStatusUsecase;
import com.gambaru.gambaru.cms.application.port.out.CreateBarcodeUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarcodeService implements FetchBarcodeByStatusUsecase, CreateBarcodeUsecase {
    private final BarcodeRepository barcodeRepository;
    private final com.gambaru.gambaru.cms.model.BuildBarcodeUsecase barcodeBuilder;

    @Override
    public BarcodeEntity fetchFirstbyStatusNotUsed() {
        Optional<BarcodeEntity> byStatus = barcodeRepository.findFirstByStatus(BarcodeEntity.Status.NOT_USED);
        if (byStatus.isEmpty()) {
            BarcodeEntity barcodeEntity = barcodeBuilder.buildNotUsedBarcode();
            return barcodeRepository.save(barcodeEntity);
        }
        return byStatus.get();
    }

    @Override
    public List<BarcodeEntity> fetchManybyStatusNotUsed(int countOfElementsToReturn) {
        Page<BarcodeEntity> byStatus = barcodeRepository.findAllByStatus(BarcodeEntity.Status.NOT_USED, Pageable.ofSize(countOfElementsToReturn));
        if (byStatus.getTotalElements() == countOfElementsToReturn) {
            return byStatus.stream().toList();
        }

        Long amountToCreate = countOfElementsToReturn - byStatus.getTotalElements();
        for (int i = 0; i < amountToCreate; i++) {
            BarcodeEntity barcodeEntity = barcodeBuilder.buildNotUsedBarcode();
            barcodeRepository.save(barcodeEntity);
        }
        Page newPageByStatus = barcodeRepository.findAllByStatus(BarcodeEntity.Status.NOT_USED, Pageable.ofSize(countOfElementsToReturn));
        return newPageByStatus.stream().toList();
    }

    @Override
    public BarcodeEntity createNotUsedBarcode() {
        return null;
    }

    @Override
    public List<BarcodeEntity> createManyNotUsedBarcode(int count) {
        return null;
    }
}
