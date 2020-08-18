package com.justdoit.controller;

import com.justdoit.POJOs.DB.Project;
import com.justdoit.POJOs.ResponseObject;
import com.justdoit.exceptions.CustomTaskException;
import com.justdoit.service.ProjectService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // works
    @GetMapping
    // This should be an admin function
    public ResponseObject<List<Project>> viewAllTasks() {
        ResponseObject<List<Project>> res = new ResponseObject();
        res.setData(projectService.listAllProjects());
        return res;
    }

    // works
    @GetMapping("/{projectId}")
    // return project info, e.g. name, deadline, etc
    public ResponseObject<Project> viewProject(@PathVariable(value="projectId")int projectId) throws CustomTaskException {
        ResponseObject<Project> res = new ResponseObject();
        try {
            res.setData(projectService.listByProjectId(projectId));
        } catch(Exception e){
            throw new CustomTaskException(e.getMessage());
        }
        return res;
    }

    @PostMapping
    // create/save new Project
    public ResponseObject<Project> createProject(@RequestBody Project newProject) {
        ResponseObject<Project> res = new ResponseObject();
        res.setData(projectService.saveProject(newProject));
        return res;
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable(value="projectId")int projectId) throws ObjectNotFoundException {
        projectService.deleteProject(projectId);
    }

}
