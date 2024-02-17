package com.atl.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atl.map.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, Long> {

    CertificationEntity findByEmail(String email);

    
}