package com.atl.map.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atl.map.dto.request.auth.EmailCheckRequestDto;
import com.atl.map.dto.response.ResponseDto;
import com.atl.map.dto.response.auth.EmailCheckResponseDto;
import com.atl.map.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    @Override
    public ResponseEntity<? super EmailCheckResponseDto> EmailCheck(EmailCheckRequestDto dto) {

        try {

            String userEmail = dto.getEmail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCheckResponseDto.success();
    }

}
