package com.atl.map.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atl.map.common.CertificationNumber;
import com.atl.map.dto.request.auth.CheckCertificationRequestDto;
import com.atl.map.dto.request.auth.EmailCertificationRequestDto;
import com.atl.map.dto.request.auth.EmailCheckRequestDto;
import com.atl.map.dto.response.ResponseDto;
import com.atl.map.dto.response.auth.CheckCertificationResponseDto;
import com.atl.map.dto.response.auth.EmailCertificationResponseDto;
import com.atl.map.dto.response.auth.EmailCheckResponseDto;
import com.atl.map.entity.CertificationEntity;
import com.atl.map.provider.EmailProvider;
import com.atl.map.repository.CertificationRepository;
import com.atl.map.repository.UserRepository;
import com.atl.map.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final EmailProvider emailProvider;

    @Override
    public ResponseEntity<? super EmailCheckResponseDto> emailCheck(EmailCheckRequestDto dto) {

        try {

            String userEmail = dto.getEmail();
            boolean isExistEmail = userRepository.existsByEmail(userEmail);
            if (isExistEmail) return EmailCheckResponseDto.duplicateEmail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertificaion(EmailCertificationRequestDto dto) {
        
        try{
            
            String email = dto.getEmail();
            
            boolean isExistEmail = userRepository.existsByEmail(email);
            if (isExistEmail) return EmailCertificationResponseDto.duplicateEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessd = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccessd) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(email, certificationNumber);
            certificationRepository.save(certificationEntity);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try{
            
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            
            CertificationEntity certificationEntity = certificationRepository.findByEmail(email);
            
            //인증 메일을 보낸적 없을 때
            if(certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email)
            && certificationEntity.getCertificationNumber().equals(certificationNumber);

            if(!isMatched) return CheckCertificationResponseDto.certificationFail();

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

}
