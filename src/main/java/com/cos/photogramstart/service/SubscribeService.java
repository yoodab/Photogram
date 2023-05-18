package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;

	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		subscribeRepository.mSubscribe(fromUserId, toUserId);
	}

	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
