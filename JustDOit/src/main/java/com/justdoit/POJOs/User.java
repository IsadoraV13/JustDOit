package com.justdoit.POJOs;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

// a user can register as part of a HH or directly on their own (and then become the HH Head)
// all users eventually become HH members, otherwise there is no utility for them to be registered
// the HH Head has the dual role of also being a HH member

@Entity
@Table(name="user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @NotEmpty(message = "Username may not be empty")
    private String userName;
    @Column(columnDefinition = "boolean default 1")
    Boolean isActive;
    @NotEmpty(message = "Passwords are mandatory")
    private String password;
    @NotEmpty(message = "We need an email address to be able to register you")
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicUrl;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "userRole",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "roleId") }
    )
    private Set<Role> userRole;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "userTask",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "taskId") }
    )
    private Set<Task> userTask;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "projectStakeholder",
            joinColumns = {
                    @JoinColumn(name = "userId"),
                    @JoinColumn(name = "projectId"),
                    @JoinColumn(name = "roleId")}
            // will this work?
    )
    private Set<Project> userProject;


    public User() {
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }

    public Set<Task> getUserTask() {
        return userTask;
    }

    public void setUserTask(Set<Task> userTask) {
        this.userTask = userTask;
    }

    public Set<Project> getUserProject() {
        return userProject;
    }

    public void setUserProject(Set<Project> userProject) {
        this.userProject = userProject;
    }
}
