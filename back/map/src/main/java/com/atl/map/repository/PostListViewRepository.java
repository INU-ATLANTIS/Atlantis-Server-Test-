package com.atl.map.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.atl.map.entity.PostListViewEntity;

@Repository
public interface PostListViewRepository extends JpaRepository<PostListViewEntity, Integer> {
    
    List<PostListViewEntity> findByOrderByWriteDatetimeDesc();

}
