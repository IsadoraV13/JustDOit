package com.justdoit.POJOs;

import java.util.Date;
import java.util.List;

public class ProjectSummary {
    private String projectName;
    private Date projectDeadline;
    private String imminentDeadlineWarning;
    private String passedDeadlineWarning;
    private String recentActivity; // will need a method to pull together text
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

    public String getImminentDeadlineWarning() {
        return imminentDeadlineWarning;
    }

    public void setImminentDeadlineWarning(String imminentDeadlineWarning) {
        this.imminentDeadlineWarning = imminentDeadlineWarning;
    }

    public String getPassedDeadlineWarning() {
        return passedDeadlineWarning;
    }

    public void setPassedDeadlineWarning(String passedDeadlineWarning) {
        this.passedDeadlineWarning = passedDeadlineWarning;
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
