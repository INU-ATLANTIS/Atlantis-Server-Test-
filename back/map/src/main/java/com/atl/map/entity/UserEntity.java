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

@Getter //멤버들의 게터 메소드 생성
@NoArgsConstructor  // 매개변수 없는 생성자
@AllArgsConstructor 
@Entity(name="user")
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long uesrId;
    private String email;
    private String password;
    private Date createDate;
    private Date updateDate;
    private String nickname;
}
