package com.gambaru.gambaru.cms.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "user")
public class UserEntity {
    public static enum Gender {
        MALE, FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci")
    private String firstName;

    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String teamName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barcodeId")
    private BarcodeEntity barcode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamId")
    private TeamEntity team;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastAttendanceTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastMembershipPaymentTimestamp;
}
