package com.atl.map.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atl.map.dto.response.ResponseDto;
import com.atl.map.dto.response.user.GetSignInUserResponseDto;
import com.atl.map.dto.response.user.GetUserResponseDto;
import com.atl.map.entity.UserEntity;
import com.atl.map.repository.UserRepository;
import com.atl.map.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
   
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try{

            userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return GetSignInUserResponseDto.noExistUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);

    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String eamil) {
       
        UserEntity userEntity = null;

        try{

           userEntity = userRepository.findByEmail(eamil);
           if(userEntity == null) return GetUserResponseDto.noExistUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }
    
}
