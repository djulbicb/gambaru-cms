package com.gambaru.gambaru.cms.adapter.out.persistence.repo;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserMembershipPaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserMembershipPaymentRepository extends CrudRepository<UserMembershipPaymentEntity, Long> {
}
