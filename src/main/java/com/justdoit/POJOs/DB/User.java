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
    private String username;
    @Column(columnDefinition = "boolean default 1")
    Boolean enabled;
    @NotEmpty(message = "Passwords are mandatory")
    private String password;
    @NotEmpty(message = "We need an email address to be able to register you")
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicUrl;
    private int mainProjectId;
    //TODO need to find a way to:
    // 1) define first project as default Main project (can be changed by user *only* once there is a second one)
    // 2) not allow more that one Project to be set as Main at a time
    // 3) always have a Main project (cannot switch all to non-Main)

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "userRole",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "roleId") }
    )
    private Set<Role> userRole;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "projectStakeholder",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "projectId") }
    )
    private Set<Project> projectStakeholder;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "houseMember",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "houseId") }
    )
    private Set<House> houseMember;


    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public int getMainProjectId() {
        return mainProjectId;
    }

    public void setMainProjectId(int mainProjectId) {
        this.mainProjectId = mainProjectId;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }

    public Set<Project> getProjectStakeholder() {
        return projectStakeholder;
    }

    public void setProjectStakeholder(Set<Project> projectStakeholder) {
        this.projectStakeholder = projectStakeholder;
    }

    public Set<House> getHouseMember() {
        return houseMember;
    }

    public void setHouseMember(Set<House> houseMember) {
        this.houseMember = houseMember;
    }
}
