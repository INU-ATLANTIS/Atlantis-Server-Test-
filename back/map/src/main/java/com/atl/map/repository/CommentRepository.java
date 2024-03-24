package com.atl.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atl.map.entity.CommentEntity;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    
    @Transactional
    void deleteByPostId(Integer postId);
}
