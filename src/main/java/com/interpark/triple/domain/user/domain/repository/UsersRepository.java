package com.interpark.triple.domain.user.domain.repository;

import com.interpark.triple.domain.user.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {


    @Query(value = "select u from Users u where u.id = :id and u.isActivated = true")
    Optional<Users> findUsersById(@Param("id") Long id);

}
