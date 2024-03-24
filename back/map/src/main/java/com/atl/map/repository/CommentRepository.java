package com.atl.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atl.map.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    
}
