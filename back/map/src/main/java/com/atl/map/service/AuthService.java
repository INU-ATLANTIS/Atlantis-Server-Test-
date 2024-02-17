package com.atl.map.service;

import org.springframework.http.ResponseEntity;

import com.atl.map.dto.response.auth.EmailCheckResponseDto;
import com.atl.map.dto.request.auth.CheckCertificationRequestDto;
import com.atl.map.dto.response.auth.CheckCertificationResponseDto;
import com.atl.map.dto.request.auth.EmailCertificationRequestDto;
import com.atl.map.dto.response.auth.EmailCertificationResponseDto;
import com.atl.map.dto.request.auth.EmailCheckRequestDto;

public interface AuthService {

    ResponseEntity<? super EmailCheckResponseDto> emailCheck(EmailCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertificaion(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
}
