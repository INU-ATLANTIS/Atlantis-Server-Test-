package com.atl.map.entity;

import java.sql.Date;

import jakarta.persistence.Column;
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
@Entity(name="user")
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "userId")
    private Long userId;
    @Column(name = "email") // 데이터베이스의 실제 컬럼명 지정
    private String email;
    private String password;
    private Date createDate;
    private Date updateDate;
    private String nickname;
    private String role;
}
