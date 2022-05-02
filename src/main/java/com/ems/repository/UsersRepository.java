package com.ems.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ems.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	@Transactional
    @Modifying
    @Query("UPDATE Users u SET u.score=:score WHERE u.email=:email")
    void updateScore(@Param("email") String email, @Param("score") int score);
    
	@Transactional
    @Modifying
    @Query("UPDATE Users u SET u.status=0 WHERE u.email=:email")
    void updateStatusOFFLINE(@Param("email") String email);
    
	@Transactional
    @Modifying
    @Query("UPDATE Users u SET u.status=1 WHERE u.email=:email")
    void updateStatus(@Param("email") String email);
    
}
