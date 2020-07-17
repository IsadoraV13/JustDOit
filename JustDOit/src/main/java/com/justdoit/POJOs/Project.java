package com.justdoit.POJOs;

import javax.persistence.*;


// a project has 0 or more tasks
// users are added indirectly to projects as project stakeholders (generic/view only) or task owners (specific)
// a user must first be added as a project stakeholder before they can be a task creator
// but they can be added directly as a task owner, without first being a stakeholder
// the project owner is by default also a project stakeholder (2 roles)hgfx
// a stakeholder is an umbrella category, so all task owners are stakeholders by default (2 roles)
// when a user stops being a task owner under a project, they remain as a stakeholder unless removed

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;
    private String projectName;
    private int projectOwnerUserId;
    private String projectDeadline;
    @Column(columnDefinition = "boolean default 0")
    private Boolean isArchived;




    public Project() {
    }



}
