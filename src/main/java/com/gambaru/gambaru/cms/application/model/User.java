package com.gambaru.gambaru.cms.application.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder(toBuilder = true)
public class User {
    private Long barcodeId;
    private String teamName;
    private String firstName;
    private String lastName;
    private LocalDateTime lastAttendanceTimestamp;
    private LocalDateTime lastMembershipPaymentTimestamp;
}
