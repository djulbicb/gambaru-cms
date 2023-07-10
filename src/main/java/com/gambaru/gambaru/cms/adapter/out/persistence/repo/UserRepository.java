package com.gambaru.gambaru.cms.adapter.out.persistence.repo;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.projections.UserLastLogProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query(value = """
        SELECT u.first_name AS firstName,
               u.last_name AS lastName,
               a.lastAttendanceTimestamp AS lastAttendanceTimestamp,
               p.lastMembershipPaymentTimestamp AS lastMembershipPaymentTimestamp
        FROM user u
        JOIN barcode b ON u.barcode_id = b.barcode_id
        LEFT JOIN
          (SELECT a.barcode_id, MAX(a.timestamp) AS lastAttendanceTimestamp
           FROM user_attendance a
           GROUP BY a.barcode_id) a ON a.barcode_id = b.barcode_id
        LEFT JOIN
          (SELECT p.barcode_id, MAX(p.timestamp) AS lastMembershipPaymentTimestamp
           FROM user_membership_payments p
           GROUP BY p.barcode_id) p ON p.barcode_id = b.barcode_id
        WHERE u.barcode_id = :barcodeId
        """, nativeQuery = true)
    UserLastLogProjection nativeQuery (Long barcodeId);
}








//    SELECT u.first_name AS firstName,
//        u.last_name AS lastName,
//        a.lastAttendanceTimestamp AS lastAttendanceTimestamp,
//        p.lastMembershipPaymentTimestamp AS lastMembershipPaymentTimestamp
//        FROM user u
//        JOIN barcode b ON u.barcode_id = b.barcode_id
//        LEFT JOIN
//        (SELECT a.barcode_id, MAX(a.timestamp) AS lastAttendanceTimestamp
//        FROM user_attendance a
//        WHERE a.barcode_id = 1) a ON a.barcode_id = b.barcode_id
//        LEFT JOIN
//        (SELECT p.barcode_id, MAX(p.timestamp) AS lastMembershipPaymentTimestamp
//        FROM user_membership_payments p
//        WHERE p.barcode_id = 1) p ON p.barcode_id = b.barcode_id
//        WHERE u.barcode_id = 1
