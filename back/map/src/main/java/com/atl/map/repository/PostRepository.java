package com.atl.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atl.map.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

    
    
}
