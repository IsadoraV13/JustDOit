package com.justdoit.service;

import com.justdoit.POJOs.Project;
import com.justdoit.POJOs.Role;
import com.justdoit.POJOs.User;
import com.justdoit.repositories.RoleRepository;
import com.justdoit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


//the service uses methods from the Repo interface
@Service
public class UserService {


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> listAllUsers() {
        return userRepo.findAll();
    }

    public List<Project> listProjectsByUserId(int userId) {
        return userRepo.findProjectsByUserId(userId);
    }

    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(true);
        Role userRole = roleRepo.findByRoleName("USER");
        user.setUserrole(new HashSet<Role>(Arrays.asList(userRole)));
        userRepo.save(user);
    }

    public User listUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User listUserById(int userId) {
        return userRepo.findOne(userId);
    }

    public String listUserNameById(int userId) {
        return userRepo.findOne(userId).getFirstName();
    }

}
