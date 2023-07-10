package com.gambaru.gambaru.cms.application.port.out;

import java.util.List;

public interface InsertUserAttendanceUsecase {
    public Boolean insert(Long barcodeId);
    public Boolean insert(List<Long> barcodeIdList);
}
