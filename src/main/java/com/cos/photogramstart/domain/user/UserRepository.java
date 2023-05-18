package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속시 어노테이션 없어도 IoC자동 등록된다.
public interface UserRepository extends JpaRepository<User, Integer>{

}
