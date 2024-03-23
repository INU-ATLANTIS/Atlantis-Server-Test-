package com.atl.map.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atl.map.dto.response.post.CreatePostResponseDto;
import com.atl.map.dto.response.post.GetPostResponseDto;
import com.atl.map.dto.request.post.CreatePostRequestDto;
import com.atl.map.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    
    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<? super GetPostResponseDto> getPost(
        @PathVariable("postId") Integer postId
    ){
        ResponseEntity<? super GetPostResponseDto> response = postService.getPost(postId);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super CreatePostResponseDto> createPost(
        @RequestBody @Valid CreatePostRequestDto requestBody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super CreatePostResponseDto> response = postService.createPost(requestBody, email);
        return response;
    }
}
