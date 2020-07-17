package com.justdoit.controller;


import com.justdoit.POJOs.Project;
import com.justdoit.POJOs.ResponseObject;
import com.justdoit.POJOs.Task;
import com.justdoit.POJOs.User;
import com.justdoit.service.TaskService;
import com.justdoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// the controller uses methods from the service class
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    // at first (when we don't have contacts, every user can see this - but then only the admin should see this)
    @GetMapping
    public ResponseObject<List<User>> ViewAllUsers() {
        ResponseObject<List<User>> res = new ResponseObject();
        res.setData(userService.listAllUsers());
        return res;
    }

    @GetMapping(value="{userId}")
    public ResponseObject<User> ViewUser(@PathVariable(name = "userId") int userId){
            ResponseObject<User> res = new ResponseObject();
        res.setData(userService.listUserById(userId));
        return res;
    }

    @GetMapping("/{userId}/tasks")
    // this should return all tasks associated with this user
    public ResponseObject<List<Task>> viewUserChats(@PathVariable(name = "userId") int userId) {
        ResponseObject<List<Task>> res = new ResponseObject();
        res.setData(taskService.listTasksByUserId(userId));
        return res;
    }


    @GetMapping("/{userId}/tasks/{taskId}") // new - is this needed?
    // this should return a specific task (associated with this user)
    public ResponseObject<Task> viewUserChats(@PathVariable(name = "userId") int userId, @PathVariable(name = "taskId") int taskId) {
        ResponseObject<Task> res = new ResponseObject();
        res.setData(taskService.listByTaskId(taskId));
        return res;
    }

    @GetMapping("/{userId}/projects") // new
    // this should return all projects that the user is a stakeholder of (including as Project Owner)
    public ResponseObject<List<Project>> viewProjects(@PathVariable(name = "userId") int userId) {
        ResponseObject<List<Project>> res = new ResponseObject();
        res.setData(userService.listProjectsByUserId(userId));
        return res;
    }

}