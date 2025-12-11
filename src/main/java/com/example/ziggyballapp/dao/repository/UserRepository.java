package com.example.ziggyballapp.dao.repository;


import com.example.ziggyballapp.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT username, best_score FROM users ORDER BY best_score DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10Leaderboard();

    UserEntity findUserEntitiesByEmail(String email);

}
