package com.atl.map.service;

import org.springframework.http.ResponseEntity;

import com.atl.map.dto.response.post.*;
import com.atl.map.dto.request.post.*;

public interface PostService {
    ResponseEntity<? super GetPostResponseDto> getPost(Integer postId);
    ResponseEntity<? super CreatePostResponseDto> createPost(CreatePostRequestDto dto, String email);
}
