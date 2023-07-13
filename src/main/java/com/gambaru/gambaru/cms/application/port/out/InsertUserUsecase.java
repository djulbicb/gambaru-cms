package com.gambaru.gambaru.cms.application.port.out;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.user.UserEntity;

public interface InsertUserUsecase {
    public UserEntity UserEntity (Long barcodeId, String firstName, String lastName, UserEntity.Gender gender, Long teamId);
}
