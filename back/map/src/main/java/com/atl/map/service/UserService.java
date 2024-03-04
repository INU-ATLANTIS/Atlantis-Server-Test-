package com.atl.map.service;

import org.springframework.http.ResponseEntity;

import com.atl.map.dto.response.user.GetUserResponseDto;
import com.atl.map.dto.response.user.GetSignInUserResponseDto;

public interface UserService {
    
    ResponseEntity<? super GetUserResponseDto> getUser(String eamil);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
}
