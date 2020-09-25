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


    public void deleteTask(int taskId) {
        taskRepo.deleteByTaskId(taskId);
    }

    public List<TaskPreview> listTaskPreviews(int projectId, int userId) {
        User user = userService.listByUserId(userId);
        List<TaskPreview> taskPreviews = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        if (listTaskByProjectId(projectId) != null) {
            tasks = listTaskByProjectId(projectId);
            for (Task task : tasks) {
            // for each task, create a new TaskPreview object and set its attributes based on that task
            TaskPreview tp = new TaskPreview();
            tp.setTaskId(task.getTaskId());
            tp.setTaskDescription(task.getTaskDescription());
            tp.setTaskDeadline(task.getTaskDeadline());
            tp.setTaskOwner(taskRepo.findTaskOwnerNameByTaskOwnerUserId(task.getTaskOwnerUserId()));
            tp.setTaskPriority(task.getTaskPriority());
            tp.setProfilePicUrl(userService.listByUserId(task.getTaskOwnerUserId()).getProfilePicUrl());
            tp.setIsComplete(task.getIsComplete());
            taskPreviews.add(tp);
            }
        }
        return taskPreviews;
    }

    public List<TaskPreview> listCompletedTaskPreviews(int projectId, int userId) {
        User user = userService.listByUserId(userId);
        List<TaskPreview> taskPreviews = new ArrayList<>();
        List<Task> tasks = listTaskByProjectId(projectId);
        for (Task task : tasks) {
            // for each task, create a new TaskPreview object and set its attributes based on that task
            if (task.getIsComplete() == false) {
                TaskPreview tp = new TaskPreview();
                tp.setTaskId(task.getTaskId());
                tp.setTaskDescription(task.getTaskDescription());
                tp.setTaskDeadline(task.getTaskDeadline());
                tp.setTaskOwner(taskRepo.findTaskOwnerNameByTaskOwnerUserId(task.getTaskOwnerUserId()));
                tp.setTaskPriority(task.getTaskPriority());
                tp.setProfilePicUrl(userService.listByUserId(task.getTaskOwnerUserId()).getProfilePicUrl());
                taskPreviews.add(tp);
            }
        }
        return taskPreviews;
    }

    public Task markTaskComplete(int taskId) {
        Task task = listByTaskId(taskId);
        task.setIsComplete(true);
        taskRepo.saveAndFlush(task);
        return task;
    }
}
