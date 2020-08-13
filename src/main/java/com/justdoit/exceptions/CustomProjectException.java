package com.justdoit.exceptions;

public class CustomProjectException extends Exception{

    private int projectId;

    public CustomProjectException(String message, int projectId) {
        super(message);
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
