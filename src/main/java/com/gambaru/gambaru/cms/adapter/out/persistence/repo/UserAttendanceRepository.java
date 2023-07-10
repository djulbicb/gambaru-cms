package com.gambaru.gambaru.cms.adapter.out.persistence.repo;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserAttendanceEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;

public interface UserAttendanceRepository extends CrudRepository<UserAttendanceEntity, Long> {

}
