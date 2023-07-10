package com.gambaru.gambaru.cms.adapter.in.web;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserAttendanceEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserMembershipPaymentEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.BarcodeRepository;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserAttendanceRepository;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserMembershipPaymentRepository;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserRepository;
import data.DataLibrary;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@Slf4j
@AllArgsConstructor
public class InitDatabaseController {
    private final UserRepository userRepository;
    private final BarcodeRepository barcodeRepository;
    private final UserAttendanceRepository attendanceRepository;
    private final UserMembershipPaymentRepository membershipPaymentRepository;
    DataLibrary dl = DataLibrary.getSerbianData();

    LocalDateTime randomDT () {
        LocalDateTime startDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.now();

        long days = startDateTime.until(endDateTime, ChronoUnit.DAYS);
        long randomDay = ThreadLocalRandom.current().nextLong(days + 1);

        return startDateTime.plusDays(randomDay);
    }
    @GetMapping("/init/users/{count}")
    public void initDatabase (@PathVariable int count) {
        List<UserEntity> users = new ArrayList<>();
        for (int i = 0; i < count/2; i++) {
            LocalDateTime localDateTime = randomDT();
            BarcodeEntity barcode = BarcodeEntity.builder()
                    .status(BarcodeEntity.Status.ASSIGNED)
                    .build();
            BarcodeEntity save = barcodeRepository.save(barcode);
            users.add(
                    UserEntity.builder()
                            .teamName("kickbox")
                            .firstName(dl.getFirstNameRandom())
                            .lastName(dl.getLastName())
                            .lastMembershipPaymentTimestamp(localDateTime)
                            .lastAttendanceTimestamp(localDateTime)
                            .createdAt(localDateTime)
                            .barcode(barcode)
                            .build()
            );
        }
        for (int i = 0; i < count/2; i++) {
            LocalDateTime localDateTime = randomDT();
            BarcodeEntity barcode = BarcodeEntity.builder()
                    .status(BarcodeEntity.Status.ASSIGNED)
                    .build();
            BarcodeEntity save = barcodeRepository.save(barcode);
            users.add(
                    UserEntity.builder()
                            .teamName("judo")
                            .firstName(dl.getFirstNameRandom())
                            .lastName(dl.getLastName())
                            .lastMembershipPaymentTimestamp(localDateTime)
                            .lastAttendanceTimestamp(localDateTime)
                            .createdAt(localDateTime)
                            .barcode(save)
                            .build()
            );
        }

        userRepository.saveAll(users);
    }

    @GetMapping("/init/attendance/{count}")
    public void initAttendance (@PathVariable int count) {
        Iterable<UserEntity> all = userRepository.findAll();
        List<UserAttendanceEntity> attendanceEntities = new ArrayList<>();
        for (UserEntity userEntity : all) {
            for (int i = 0; i < count; i++) {
                UserAttendanceEntity build = UserAttendanceEntity.builder()
                        .barcode(userEntity.getBarcode())
                        .timestamp(randomDT())
                        .build();
                attendanceEntities.add(build);
            }
        }

        attendanceRepository.saveAll(attendanceEntities);
    }

    @GetMapping("/init/membership/{count}")
    public void initMembership (@PathVariable int count) {
        Iterable<UserEntity> all = userRepository.findAll();
        List<UserMembershipPaymentEntity> attendanceEntities = new ArrayList<>();
        for (UserEntity userEntity : all) {
            for (int i = 0; i < count; i++) {
                UserMembershipPaymentEntity build = UserMembershipPaymentEntity.builder()
                        .barcode(userEntity.getBarcode())
                        .timestamp(randomDT())
                        .money(BigDecimal.ONE)
                        .build();
                attendanceEntities.add(build);
            }
        }

        membershipPaymentRepository.saveAll(attendanceEntities);
    }
}
