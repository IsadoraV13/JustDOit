package com.justdoit.repositories;

import com.justdoit.POJOs.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findById(int projectId); // is this ever going to be used? Consider deleting

}
