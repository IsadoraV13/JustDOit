package com.justdoit.controller;


import com.justdoit.POJOs.*;
import com.justdoit.service.TaskService;
import com.justdoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// the controller uses methods from the service class
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    // works
    @GetMapping
    // This should be an admin function
    public ResponseObject<List<User>> viewAllUsers() {
        ResponseObject<List<User>> res = new ResponseObject();
        res.setData(userService.listAllUsers());
        return res;
    }

    // works
    @GetMapping(value="{userId}")
    // return user info, e.g. name, email, etc
    public ResponseObject<User> viewUser(@PathVariable(name = "userId") int userId){
            ResponseObject<User> res = new ResponseObject();
        res.setData(userService.listUserById(userId));
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
    @GetMapping("/{userId}/tasks/{taskId}") // TODO is this needed?
    // return a specific task (associated with this user)
    public ResponseObject<Task> viewUserTasks(@PathVariable(name = "userId") int userId, @PathVariable(name = "taskId") int taskId) {
        ResponseObject<Task> res = new ResponseObject();
        res.setData(taskService.listByTaskId(taskId));
        return res;
    }

    @PostMapping("/{houseId}") // TODO collect houseId upon registration and pass it here
    // create/save new user
    public ResponseObject<User> createUser(@RequestBody User newUser,
                                           @PathVariable(name = "houseId") int houseId) {
        ResponseObject<User> res = new ResponseObject();
        res.setData(userService.saveUser(newUser, houseId));
        return res;
    }
}