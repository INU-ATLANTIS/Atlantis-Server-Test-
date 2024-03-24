package com.atl.map.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atl.map.dto.request.post.CreatePostRequestDto;
import com.atl.map.dto.request.post.PostCommentRequestDto;
import com.atl.map.dto.response.ResponseDto;
import com.atl.map.dto.response.post.CreatePostResponseDto;
import com.atl.map.dto.response.post.GetPostResponseDto;
import com.atl.map.dto.response.post.PostCommentResponseDto;
import com.atl.map.dto.response.post.PutFavoriteResponseDto;
import com.atl.map.entity.CommentEntity;
import com.atl.map.entity.FavoriteEntity;
import com.atl.map.entity.ImageEntity;
import com.atl.map.entity.PostEntity;
import com.atl.map.entity.UserEntity;
import com.atl.map.repository.CommentRepository;
import com.atl.map.repository.FavoriteRepository;
import com.atl.map.repository.ImageRepository;
import com.atl.map.repository.PostRepository;
import com.atl.map.repository.UserRepository;
import com.atl.map.repository.resultSet.GetPostResultSet;
import com.atl.map.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImplement implements PostService {
    
    
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    

    @Transactional
    @Override
    public ResponseEntity<? super CreatePostResponseDto> createPost(CreatePostRequestDto dto, String email) {
        

        try{
            boolean existedEmail = userRepository.existsByEmail(email);
            if(!existedEmail) return CreatePostResponseDto.notExistUser();

            UserEntity userEntity = userRepository.findByEmail(email);
            PostEntity postEntity = new PostEntity(dto, userEntity.getUserId());
            postRepository.save(postEntity);

            int postId = postEntity.getPostId();
            List<String> postImageList = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();
            if(postImageList!=null){
                for(String image: postImageList)
                {
                    ImageEntity imageEntity = new ImageEntity(postId, image);
                    imageEntities.add(imageEntity);
                }
                imageRepository.saveAll(imageEntities);
            }

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CreatePostResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetPostResponseDto> getPost(Integer postId) {
     
        GetPostResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();
        try{
 
            resultSet = postRepository.getPost(postId);
            if(resultSet == null) return GetPostResponseDto.notExistPost();
            imageEntities = imageRepository.findByPostId(postId);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPostResponseDto.success(resultSet, imageEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer postId, String email) {
    
        try{

            boolean existedEmail =userRepository.existsByEmail(email);
            if(!existedEmail) return PutFavoriteResponseDto.notExistUser();

            PostEntity postEntity = postRepository.findByPostId(postId);
            if(postEntity == null) return PutFavoriteResponseDto.noExistPost();

            Integer userId = userRepository.findByEmail(email).getUserId();
            FavoriteEntity favoriteEntity = favoriteRepository.findByPostIdAndUserId(postId, userId);
            if(favoriteEntity==null){
                favoriteEntity = new FavoriteEntity(userId, postId);
                favoriteRepository.save(favoriteEntity);
                postEntity.increaseLikeCount();
            }else{
                favoriteRepository.delete(favoriteEntity);
                postEntity.decreaseLikeCount();
            }

            postRepository.save(postEntity);


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer postId, String email) {
    
        try{

            PostEntity postEntity = postRepository.findByPostId(postId);
            if(postEntity == null) return PostCommentResponseDto.notExistPost();

            boolean existedEmail = userRepository.existsByEmail(email);
            if(!existedEmail) return PostCommentResponseDto.notExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, postId, userRepository.findByEmail(email).getUserId());
            commentRepository.save(commentEntity);
            postEntity.increaseCommentCount();
            postRepository.save(postEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();
    }
    
}
