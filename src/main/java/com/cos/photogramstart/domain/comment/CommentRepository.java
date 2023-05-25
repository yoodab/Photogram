package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

// 내가 만든 네이티브 쿼리는 리턴을 int로만 받을 수 있다 
//	@Modifying
//	@Query(value = "INSERT INTO comment(content, imageId, userId, createDate) VALUES(:content, :imageId, :userId, now())", nativeQuery = true)
//	int mSave(String content, int imageId, int userId);
}
