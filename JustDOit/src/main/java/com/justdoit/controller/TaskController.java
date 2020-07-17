package com.justdoit.controller;

import com.justdoit.POJOs.ResponseObject;
import com.justdoit.POJOs.Task;
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
    public ResponseObject<List<Task>> ViewAllChats() {
        ResponseObject<List<Task>> res = new ResponseObject();
        res.setData(taskService.listAllTasks());
        return res;
    }



    @PostMapping("/{taskId}/task")
    public ResponseObject<Task> createMessage(@RequestBody Task task) {
        ResponseObject<Task> res = new ResponseObject();
        res.setData(taskService.saveTask(task));
        return res;
    }


    @DeleteMapping("/{taskId}")
    public void deleteChat(@PathVariable(value="taskId")int taskId) throws ObjectNotFoundException {
        taskService.deleteTask(taskId);
    }

}
