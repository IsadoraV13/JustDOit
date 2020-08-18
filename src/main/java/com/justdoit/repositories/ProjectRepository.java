package com.justdoit.repositories;

import com.justdoit.POJOs.DB.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findProjectNameByProjectId(int projectId);

    @Query(value = "SELECT projectId FROM projectStakeholder WHERE userId = ?1", nativeQuery = true)
    List<Integer> findProjectIdByUserId(int userId);

    void deleteByProjectId(int projectId);

}
