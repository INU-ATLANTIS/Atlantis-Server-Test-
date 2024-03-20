package com.atl.map.service.implement;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atl.map.dto.request.post.CreatePostRequestDto;
import com.atl.map.dto.response.ResponseDto;
import com.atl.map.dto.response.post.CreatePostResponseDto;
import com.atl.map.entity.PostEntity;
import com.atl.map.entity.UserEntity;
import com.atl.map.repository.PostRepository;
import com.atl.map.repository.UserRepository;
import com.atl.map.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImplement implements PostService {
    
    
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Override
    public ResponseEntity<? super CreatePostResponseDto> createPost(CreatePostRequestDto dto, int userId) {
        

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (!userEntityOptional.isPresent()) {
            // UserEntity가 존재하지 않는 경우, 적절한 에러 응답 반환
            return CreatePostResponseDto.notExistUser();
        }

        PostEntity postEntity = new PostEntity(dto, userId);
        postRepository.save(postEntity);
        
        try{

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CreatePostResponseDto.success();

    }
    
}
