package com.justdoit.controller;

import com.justdoit.POJOs.DB.Task;
import com.justdoit.POJOs.ResponseObject;
import com.justdoit.service.TaskService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    // This should be an admin function
    public ResponseObject<List<Task>> viewAllTasks() {
        ResponseObject<List<Task>> res = new ResponseObject();
        res.setData(taskService.listAllTasks());
        return res;
    }

    // works
    @GetMapping("/{taskId}")
    // return task info, e.g. description, deadline, etc
    public ResponseObject<Task> viewTask(@PathVariable(value="taskId")int taskId) {
        ResponseObject<Task> res = new ResponseObject();
        res.setData(taskService.listByTaskId(taskId));
        return res;
    }

    @PostMapping
    // create/save new task
    public ResponseObject<Task> createTask(@RequestBody Task newTask) {
        ResponseObject<Task> res = new ResponseObject();
        res.setData(taskService.saveTask(newTask));
        return res;
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable(value="taskId")int taskId) throws ObjectNotFoundException {
        taskService.deleteTask(taskId);
    }

}
