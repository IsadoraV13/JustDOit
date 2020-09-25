package com.justdoit.service;

import com.justdoit.POJOs.DB.Project;
import com.justdoit.POJOs.DB.Task;
import com.justdoit.POJOs.DB.User;
import com.justdoit.POJOs.ProjectSummary;
import com.justdoit.exceptions.CustomProjectException;
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
    @Autowired
    private TaskService taskService;

    public List<Project> listAllProjects() {
        return projectRepo.findAll();
    }

    public Project listByProjectId(int projectId) throws CustomProjectException {
        try {
            return projectRepo.findOne(projectId);
        }catch (Exception e){
            throw new CustomProjectException("unable to find this project", projectId);
        }
    }

    // TODO check
    public List<Integer> listImminentTaskDeadlineWarning(int projectId) {
        List<Task> tasks = taskRepo.findTasksByProjectId(projectId);
        int imminent = 0;
        int passed = 0;
        List<Integer> warnings = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getIsComplete() == false) {
                Date deadlineDate = new Date(task.getTaskDeadline().getTime());
                long timeLeft = DAYS.between(LocalDate.now(), deadlineDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                if (timeLeft <= 2 && timeLeft >= 0)
                    imminent++;
                if (timeLeft < 0)
                    passed++;
            }
        }
        warnings.add(0, imminent);
        warnings.add(1, passed);
        return warnings;
    }

    public List<Integer> listOtherProjectIds(int userId) {
        // find user
        User user = userRepo.findOne(userId);
        // find user's list of projects
        List<Integer> projectIds = projectRepo.findProjectIdByUserId(userId);
        // then find non-Main projects
        List<Integer> otherProjectIds = new ArrayList<>();
        for (Integer projectId : projectIds) {
            if (projectId != user.getMainProjectId())
                otherProjectIds.add(projectId);
        }
        return otherProjectIds;
    }

    public List<ProjectSummary> listProjectSummaries(List<Integer> projectIds, int userId) {
        List<ProjectSummary> projectSummaries = new ArrayList<>();
        for (Integer projectId : projectIds) {
            // for each non-Main projectId, find the project
            // & create a new ProjectSummary object and set its attributes to those of that project
            Project project = projectRepo.findOne(projectId);
            ProjectSummary ps = new ProjectSummary();
            ps.setProjectId(project.getProjectId());
            ps.setProjectName(project.getProjectName());
            ps.setProjectDeadline(project.getProjectDeadline());
            List<Integer> warnings = listImminentTaskDeadlineWarning(projectId);
            // TODO check that this works for all scenarios
            if (warnings.get(0) > 0)
                ps.setImminentDeadlineWarning("This project has 1 or more tasks with an imminent deadline");
            if (warnings.get(1) > 0)
                ps.setPassedDeadlineWarning("This project has 1 or more tasks with a missed deadline");
            ps.setTaskPreviews(taskService.listTaskPreviews(projectId, userId));
            projectSummaries.add(ps);
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



    public void deleteProject(int projectId) {
        projectRepo.deleteByProjectId(projectId);
    }


}
