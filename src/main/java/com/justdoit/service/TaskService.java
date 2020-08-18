package com.justdoit.service;

import com.justdoit.POJOs.DB.Task;
import com.justdoit.POJOs.DB.User;
import com.justdoit.POJOs.TaskPreview;
import com.justdoit.repositories.ProjectRepository;
import com.justdoit.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//the service uses methods from the Repo interface
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    // admin only
    public List<Task> listAllTasks() {
        return taskRepo.findAll();
    }

    // used once user clicks on a particular TaskPreview to view or update
    public Task listByTaskId(int taskId) {
        return taskRepo.findOne(taskId);
    }

    public List<Task> listTaskByProjectId(int projectId) {
        return taskRepo.findTasksByProjectId(projectId);
    }

    public Task saveTask(Task newTask) {
        return taskRepo.save(newTask);
    }

    public List<TaskPreview> listTaskPreviews(int userId) {
        User user = userService.listByUserId(userId);
        int projectId = user.getMainProjectId();
        List<TaskPreview> taskPreviews = new ArrayList<>();
        List<Task> tasks = listTaskByProjectId(projectId);
        for (Task task : tasks) {
            // for each task, create a new TaskPreview object and set its attributes based on that task
            TaskPreview tp = new TaskPreview();
            tp.setTaskDescription(task.getTaskDescription());
            tp.setTaskDeadline(task.getTaskDeadline());
            tp.setTaskOwner(taskRepo.findTaskOwnerNameByTaskOwnerUserId(task.getTaskOwnerUserId()));
            tp.setTaskPriority(task.getTaskPriority());
            taskPreviews.add(tp);
        }
        return taskPreviews;
    }

    public void deleteTask(int taskId) {
        taskRepo.deleteByTaskId(taskId);
    }


}
