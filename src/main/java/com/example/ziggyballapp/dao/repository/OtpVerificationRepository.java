package com.example.ziggyballapp.dao.repository;

import com.example.ziggyballapp.dao.entity.OtpVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OtpVerificationRepository extends JpaRepository<OtpVerificationEntity, Long> {

    OtpVerificationEntity findOtpVerificationEntitiesByEmail(String email);
}
