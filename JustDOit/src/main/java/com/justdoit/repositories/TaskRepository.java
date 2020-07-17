package com.justdoit.repositories;

import com.justdoit.POJOs.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByTaskId(int taskId);

    void deleteByTaskId(int taskId);

    @Query(value = "select userId from taskchat WHERE chatId = ?1", nativeQuery = true)
    List<Integer> findUserIdinTask(int taskId);


}
