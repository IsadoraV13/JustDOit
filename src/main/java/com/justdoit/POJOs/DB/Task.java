package com.justdoit.POJOs;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


// a chat will be either a group chat or a 1on1 chat (for iteration1)
// there will be a table for messages and a chat will be attached every message
// the message will itself have attributes like content, timestamp and senderId

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    private int projectId;
    @NotNull
    private String taskDescription;     // emoji;
    private int taskOwnerUserId;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date taskDeadline;

    // ***************************
    // Add taskReminder
    // ***************************

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp", updatable= false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Column(columnDefinition = "boolean default 0")
    private Boolean isComplete;
    @Column(nullable = true)
    private Integer dependsOn;
    @Column(nullable = true)
    private Integer isDependedOnBy;
    @Column(columnDefinition = "boolean default 1")
    private Boolean taskDeadlineNotificationOn;
    @Column(columnDefinition = "boolean default 1")
    private Boolean taskCompleteNotificationOn;
    @Column(columnDefinition = "boolean default 1")
    private Boolean taskAddedNotificationOn;
    private String taskPriority;
    private String photoUrl;


    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTaskOwnerUserId() {
        return taskOwnerUserId;
    }

    public void setTaskOwnerUserId(int taskOwnerUserId) {
        this.taskOwnerUserId = taskOwnerUserId;
    }


    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean complete) {
        isComplete = complete;
    }

    public Integer getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Integer dependsOn) {
        this.dependsOn = dependsOn;
    }

    public Integer getIsDependedOnBy() {
        return isDependedOnBy;
    }

    public void setIsDependedOnBy(Integer isDependedOnBy) {
        this.isDependedOnBy = isDependedOnBy;
    }

    public Boolean getTaskDeadlineNotificationOn() {
        return taskDeadlineNotificationOn;
    }

    public void setTaskDeadlineNotificationOn(Boolean taskDeadlineNotificationOn) {
        this.taskDeadlineNotificationOn = taskDeadlineNotificationOn;
    }

    public Boolean getTaskCompleteNotificationOn() {
        return taskCompleteNotificationOn;
    }

    public void setTaskCompleteNotificationOn(Boolean taskCompleteNotificationOn) {
        this.taskCompleteNotificationOn = taskCompleteNotificationOn;
    }

    public Boolean getTaskAddedNotificationOn() {
        return taskAddedNotificationOn;
    }

    public void setTaskAddedNotificationOn(Boolean taskAddedNotificationOn) {
        this.taskAddedNotificationOn = taskAddedNotificationOn;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
