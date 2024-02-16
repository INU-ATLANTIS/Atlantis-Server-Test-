package com.atl.map.service;

import org.springframework.http.ResponseEntity;

import com.atl.map.dto.response.auth.EmailCheckResponseDto;
import com.atl.map.dto.request.auth.EmailCheckRequestDto;

public interface AuthService {

    ResponseEntity<? super EmailCheckResponseDto> EmailCheck(EmailCheckRequestDto dto);
}
