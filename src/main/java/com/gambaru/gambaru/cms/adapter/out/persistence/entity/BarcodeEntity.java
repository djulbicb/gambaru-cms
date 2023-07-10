package com.gambaru.gambaru.cms.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "barcode")
public class BarcodeEntity {
    public static enum Status {
        NOT_USED, ASSIGNED, DEACTIVATED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barcodeId;
    @Enumerated(EnumType.STRING)
    private BarcodeEntity.Status status;


}

