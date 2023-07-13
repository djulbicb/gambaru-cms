package com.gambaru.gambaru.cms.model;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserAttendanceEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.user.UserEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserAttendanceRepository;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserRepository;
import com.gambaru.gambaru.cms.application.port.out.InsertUserAttendanceUsecase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService implements InsertUserAttendanceUsecase {

    private final UserAttendanceRepository userAttendanceRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public Boolean insert(Long barcodeId) {
        Optional<UserEntity> byBarcodeIdOpt = userRepository.findByBarcodeId(barcodeId);
        if (byBarcodeIdOpt.isPresent()) {
            UserEntity userEntity = byBarcodeIdOpt.get();
            userEntity.setLastAttendanceTimestamp(LocalDateTime.now());

            UserAttendanceEntity newAttendance = UserAttendanceEntity.builder()
                    .barcode(userEntity.getBarcode())
                    .timestamp(LocalDateTime.now())
                    .build();

            userRepository.save(userEntity);
            userAttendanceRepository.save(newAttendance);
            return true;
        }
        return false;
    }

    @Override
    public Boolean insert(List<Long> barcodeIdList) {
        return null;
    }
}
