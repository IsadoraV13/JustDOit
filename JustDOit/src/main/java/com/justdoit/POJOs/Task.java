package com.justdoit.POJOs;

import javax.persistence.*;
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
    private String taskDescription;     // emoji;
    private int taskOwnerUserId;
    private int taskCreatorUserId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskDeadline;
    // taskReminder
    @Column(columnDefinition = "created default current_timestamp", updatable= false, insertable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(columnDefinition = "lastModified default current_timestamp", updatable= true, insertable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(columnDefinition = "boolean default 0")
    Boolean isComplete; //is there a reason we don't say private here?
    private int dependsOn;
    private int isDependedOnBy;
    @Column(columnDefinition = "boolean default 1")
    Boolean taskDeadlineNotificationOn;
    @Column(columnDefinition = "boolean default 1")
    Boolean taskCompleteNotificationOn;
    private String photoUrl;



    public Task() {
    }



}
