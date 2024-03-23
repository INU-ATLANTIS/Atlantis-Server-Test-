package com.atl.map.repository.resultSet;

public interface GetPostResultSet {
    Integer getPostId();
    String getTitle();
    String getContent();
    String getWriteDatetime(); // SQL 쿼리의 결과 타입에 따라 Java의 LocalDateTime 또는 String 타입으로 받을 수 있습니다.
    Integer getWriterUserId();
    String getWriterNickname();
    String getWriterProfileImage();
}
