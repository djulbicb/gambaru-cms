package com.gambaru.gambaru.cms.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "user_membership_payments")
public class UserMembershipPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipPaymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcodeId")
    private BarcodeEntity barcode;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

    private BigDecimal money;
}
