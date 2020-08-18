package com.justdoit.controller;


import com.justdoit.POJOs.DB.Project;
import com.justdoit.POJOs.DB.Task;
import com.justdoit.POJOs.DB.User;
import com.justdoit.POJOs.ProjectSummary;
import com.justdoit.POJOs.ResponseObject;
import com.justdoit.POJOs.TaskPreview;
import com.justdoit.service.ProjectService;
import com.justdoit.service.TaskService;
import com.justdoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// the controller uses methods from the service class
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    // works
    @GetMapping
    // This should be an admin function
    public ResponseObject<List<User>> viewAllUsers() {
        ResponseObject<List<User>> res = new ResponseObject();
        res.setData(userService.listAllUsers());
        return res;
    }

    // works
    @GetMapping("/{userId}")
    // return user info, e.g. name, email, etc
    public ResponseObject<User> viewUser(@PathVariable(name = "userId") int userId){
            ResponseObject<User> res = new ResponseObject();
        res.setData(userService.listByUserId(userId));
        return res;
    }

    // works
    @GetMapping("/{userId}/tasks")
    // return all tasks associated with this user
    public ResponseObject<List<Task>> viewUserTasks(@PathVariable(name = "userId") int userId) {
        ResponseObject<List<Task>> res = new ResponseObject();
        res.setData(userService.listTasksByTaskOwnerId(userId));
        return res;
    }

    // works
    @GetMapping("/{userId}/projects")
    // return all projects that the user is a stakeholder of (including as Project Owner)
    public ResponseObject<List<Project>> viewUserProjects(@PathVariable(name = "userId") int userId) {
        ResponseObject<List<Project>> res = new ResponseObject();
        res.setData(userService.listProjectsByUserId(userId));
        return res;
    }

    // works
    @GetMapping("/{userId}/mainproject/taskpreviews")
    // return all task info related to this projectId
    public ResponseObject<List<TaskPreview>> viewTaskPreviews(@PathVariable(value="userId")int userId) {
        ResponseObject<List<TaskPreview>> res = new ResponseObject<>();
        res.setData(taskService.listTaskPreviews(userId));
        return res;
    }

    // works
    @GetMapping("/{userId}/main/projectsummary")
    // return main project summary associated with this user
    public ResponseObject<List<ProjectSummary>> viewMainProjectSummaryByUserId(@PathVariable(value="userId")int userId) {
        ResponseObject<List<ProjectSummary>> res = new ResponseObject<>();
        List<Integer> mainProjectId = new ArrayList<>();
        mainProjectId.add(userService.listByUserId(userId).getMainProjectId());
        res.setData(projectService.listProjectSummaries(mainProjectId, userId));
        return res;
    }

    // works
    @GetMapping("/{userId}/other/projectsummaries")
    // return all non-main project summaries associated with this user
    public ResponseObject<List<ProjectSummary>> viewProjectSummariesByUserId(@PathVariable(value="userId")int userId) {
        ResponseObject<List<ProjectSummary>> res = new ResponseObject<>();
        List<Integer> otherProjectIds = projectService.listOtherProjectIds(userId);
        res.setData(projectService.listProjectSummaries(otherProjectIds, userId));
        return res;
    }

    // works
    @GetMapping("/{userId}/tasks/{taskId}") // TODO is this needed?
    // return a specific task (associated with this user)
    public ResponseObject<Task> viewUserTasks(@PathVariable(name = "userId") int userId, @PathVariable(name = "taskId") int taskId) {
        ResponseObject<Task> res = new ResponseObject();
        res.setData(taskService.listByTaskId(taskId));
        return res;
    }

//    @PostMapping("/{houseId}")
//    // create/save new user
//    public ResponseObject<User> createUser(@RequestBody User newUser,
//                                           @PathVariable(name = "houseId") int houseId) {
//        ResponseObject<User> res = new ResponseObject();
//        res.setData(userService.saveUser(newUser));
//        return res;
//    }
}