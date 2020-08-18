package com.justdoit.repositories;

import com.justdoit.POJOs.DB.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    void deleteByTaskId(int taskId);

    List<Task> findTasksByTaskOwnerUserId(int userId);

    @Query(value = "SELECT firstName FROM user WHERE userId = ?1", nativeQuery = true)
    String findTaskOwnerNameByTaskOwnerUserId(int userId);

    @Query(value = "SELECT * FROM task WHERE projectId = ?1 ORDER BY taskDeadline DESC", nativeQuery = true)
    List<Task> findTasksByProjectId(int projectId);

}
