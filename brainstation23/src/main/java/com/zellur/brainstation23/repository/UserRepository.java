package com.zellur.brainstation23.repository;

import com.zellur.brainstation23.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
     User findByEmail(String email);
}
