package com.justdoit.service;


import com.justdoit.POJOs.DB.*;
import com.justdoit.repositories.ProjectRepository;
import com.justdoit.repositories.RoleRepository;
import com.justdoit.repositories.TaskRepository;
import com.justdoit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


//the service uses methods from the Repo interface
@Service
public class UserService {


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private HouseService houseService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> listAllUsers() {
        return userRepo.findAll();
    }

    // this is used to show the Project Tiles
    public List<Project> listProjectsByUserId(int userId) {
        // find projectIds associated with user
        List<Integer> projectIds = userRepo.findProjectIdsByUserId(userId);
        List<Project> projects = new ArrayList<>();
        // for each projectId, find its project object
        for (int projectId : projectIds) {
            projects.add(projectRepo.findOne(projectId));
        }
        return projects;
    }

    // this is needed for filtered view only
    public List<Task> listTasksByTaskOwnerId(int userId) {
        // userId of logged in user is already available as a URL parameter (via the hidden html element)
        return taskRepo.findTasksByTaskOwnerUserId(userId);
    }

    public User saveUser(User user, House house) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setUserRole(new HashSet<Role>(Arrays.asList(roleRepo.findByRoleName("houseMember"))));
        user.setHouseMember(new HashSet<House>(Arrays.asList(houseService.listByHouseName(house.getHouseName()))));
        return userRepo.save(user);
    }

    public User listUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User listByUserId(int userId) {
        return userRepo.findOne(userId);
    }

    public String listUserNameById(int userId) {
        return userRepo.findOne(userId).getFirstName();
    }

}
