package com.justdoit.service;

import com.justdoit.POJOs.Project;
import com.justdoit.POJOs.Task;
import com.justdoit.POJOs.User;
import com.justdoit.repositories.ProjectRepository;
import com.justdoit.repositories.TaskRepository;
import com.justdoit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


//the service uses methods from the Repo interface
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private UserService userService;

    public List<Task> listAllTasks() {
        return taskRepo.findAll();
    }

    public Task saveTask(Task newTask, int TOId, int projectId) {
        User taskOwner = userRepo.getOne(TOId);
        Project project = projectRepo.getOne(projectId);
        List<Task> tasks = new ArrayList<>();
        tasks.add(newTask);
        taskOwner.setUserTask(new HashSet<Task>(tasks));

        return taskRepo.save(newTask);
    }

    public List<Task> listTasksByUserId(int userId) {
    // userId of logged in user is already available as a parameter in the URL (via the hidden html element)
        User user = userService.listUserById(userId);
        return new ArrayList<Task>(user.getUserTask());
    }

    public Task listByTaskId(int chatId) {
        return taskRepo.findByTaskId(chatId);
    }

    public void deleteTask(int chatId) {
        taskRepo.deleteByTaskId(chatId);
    }


}
