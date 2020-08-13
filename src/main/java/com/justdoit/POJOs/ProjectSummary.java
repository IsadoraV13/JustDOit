package com.justdoit.POJOs;

import java.util.Date;
import java.util.List;

public class ProjectSummary {
    private String projectName;
    private Date projectDeadline;
    private String warning; // will need a method to pull together the warning text
    private String recentActivity; // will also need a method
    private List<TaskPreview> taskPreviews;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectDeadline() {
        return projectDeadline;
    }

    public void setProjectDeadline(Date projectDeadline) {
        this.projectDeadline = projectDeadline;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getRecentActivity() {
        return recentActivity;
    }

    public void setRecentActivity(String recentActivity) {
        this.recentActivity = recentActivity;
    }

    public List<TaskPreview> getTaskPreviews() {
        return taskPreviews;
    }

    public void setTaskPreviews(List<TaskPreview> taskPreviews) {
        this.taskPreviews = taskPreviews;
    }
}
