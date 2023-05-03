package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	
	@Modifying // INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성시 해당 어노테이션 필요함
	@Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId,:toUserId,now())",nativeQuery=true)
	// 네이티브 쿼리 작성시 nativeQuery=true 옵션 들어가야함
	void mSubscribe(int fromUserId, int toUserId); // void말고 int시 성공 = 변경된 행의 개수 리턴 , 실패 = -1 리턴
	
	@Modifying
	@Query(value="DELETE FEOM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",nativeQuery=true)
	void mUnSubscribe(int fromUserId, int toUserId);
}
