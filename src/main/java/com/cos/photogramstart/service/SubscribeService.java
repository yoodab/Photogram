package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em;
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {
		
		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("select u.id userId, u.username, u.profileImageUrl,  ");
		sb.append("if( (select true from subscribe where fromUserId = ? and toUserId = u.id), true, false) subscribeState, ");  // principalDetails.user.id
		sb.append("if(u.id = ?, true, false) equalState "); // principalDetails.user.id
		sb.append("from subscribe f inner join user u on u.id = f.toUserId ");
		sb.append("where f.fromUserId = ? "); // pageUserId
		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		System.out.println("쿼리 : "+query.getResultList().get(0));
		
		// 쿼리 실행 (qlrm라이브러리 필요 = DTO에 DB결과를 매핑하기 위해)
		JpaResultMapper result  = new JpaResultMapper();
		List<SubscribeDto> subscribeRespDtos = result.list(query, SubscribeDto.class);
		return subscribeRespDtos;
	}
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}

	}

	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
	
	
}
