package com.justdoit.repositories;

import com.justdoit.POJOs.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Query(value = "select projectId from projectStakeholder WHERE userId = ?1", nativeQuery = true)
    List<Integer> findProjectIdsByUserId(int userId);





}
