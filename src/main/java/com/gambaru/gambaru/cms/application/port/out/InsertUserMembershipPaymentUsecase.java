package com.gambaru.gambaru.cms.application.port.out;

import java.util.List;
import java.util.Optional;

public interface InsertUserMembershipPaymentUsecase {
    public Boolean insert(Long barcodeId);
    public Boolean insert(List<Long> barcodeIdList);
}
