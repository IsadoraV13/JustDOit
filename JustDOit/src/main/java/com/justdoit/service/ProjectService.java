package com.justdoit.service;

import com.justdoit.POJOs.Project;
import com.justdoit.POJOs.ProjectSummary;
import com.justdoit.POJOs.Task;
import com.justdoit.POJOs.User;
import com.justdoit.repositories.ProjectRepository;
import com.justdoit.repositories.TaskRepository;
import com.justdoit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;


//the service uses methods from the Repo interface
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;

    public List<Project> listAllProjects() {
        return projectRepo.findAll();
    }

    // TODO check
    public int listImminentTaskDeadlineWarning(int projectId) {
        List<Task> tasks = taskRepo.findTasksByProjectId(projectId);
        int warnings = 0;
        for (Task task : tasks) {
            Date deadlineDate = new Date(task.getTaskDeadline().getTime());
            long timeLeft = DAYS.between(LocalDate.now(), deadlineDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if(timeLeft < 1)
                warnings ++;
        }
        return warnings;
    }

    public List<ProjectSummary> listProjectSummaries(int userId) {
        List<ProjectSummary> projectSummaries = new ArrayList<>();
        // find user
        User user = userRepo.findOne(userId);
        // find user's list of projects
        List<Integer> projectIds = projectRepo.findProjectIdByUserId(userId);
        System.out.println(projectIds); // this is returning the correct projectIds
        // then find non-Main project and their tasks
        for (Integer projectId : projectIds) {
            if (projectId != user.getMainProjectId()) {
                // for each non-Main projectId, find the project
                // & create a new ProjectSummary object and set its attributes based on that project
                Project project = projectRepo.findOne(projectId);
                ProjectSummary ps = new ProjectSummary();
                ps.setProjectName(project.getProjectName());
                ps.setProjectDeadline(project.getProjectDeadline());
                int warnings = listImminentTaskDeadlineWarning(projectId);
                // TODO this doesn't work for tasks that have already expired
                if (warnings > 1)
                    ps.setWarning("You have " + warnings + " tasks about to miss their deadline");
                else if (warnings == 1)
                    ps.setWarning("You have 1 task about to miss its deadline");
                projectSummaries.add(ps);
            }
        }
        return projectSummaries;
    }

    public Project saveProject(Project newProject) {
        // add ProjectOwner as a ProjectStakeholder
        int projectOwnerUserId = newProject.getProjectOwnerUserId();
        User projectOwner = userRepo.findOne(projectOwnerUserId);
        Set<Project> userProjects = projectOwner.getProjectStakeholder();
        userProjects.add(newProject);
        return projectRepo.save(newProject);
    }

    // ********************
    // Need something here to define project deadline computation
    // ********************

    public Project listByProjectId(int projectId) {
        return projectRepo.findOne(projectId);
    }

    public void deleteProject(int projectId) {
        projectRepo.deleteByProjectId(projectId);
    }


}
