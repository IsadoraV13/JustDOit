package com.justdoit.repositories;

import com.justdoit.POJOs.Project;
import com.justdoit.POJOs.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<Project> findProjectsByUserId(int userId);


}
