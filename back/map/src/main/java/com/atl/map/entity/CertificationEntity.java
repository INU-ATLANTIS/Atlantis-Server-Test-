package com.atl.map.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="certification")
@Table(name="certification")
public class CertificationEntity {
    
    @Id
    @GeneratedValue
    private Long userId;
    private String email;
    private String certificationNumber;
}
