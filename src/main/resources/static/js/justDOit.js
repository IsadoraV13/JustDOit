let baseUrl = '/users';

// hidden element that passes the userId into the URL
let userId = document.getElementById('user_id').value;

//*************************************************
// fetch TaskPreviews for Main Project (left)
//*************************************************
// runs asynchronously as soon as page is loaded (takes time to convert the readable stream to Json)
// in the meantime, other things can load on the page
//(function getTaskPreviews() {
//    fetch(`users/${userId}/mainproject/taskpreviews`)
//        .then(res => res.json())  // fetching url returns a response, which is converted to json
//        .then(resObj => { // json response has a body object with a data property and under that
//                    // an array of taskPreview objects
//            resObj.data.forEach(taskPreviewObj => createTaskPreview(taskPreviewObj));
//            // for each taskPreview obj, create a preview box using the createTaskPreview function (further down)
//        })
//})();

//*********************************************************
// fetch ProjectSummaries
//*********************************************************
(function getMainProjectSummary() {
    let wrap = document.querySelector('#main-project-summary-wrap');
    let taskPreviewIndicator = 0;
    fetch(`users/${userId}/mainproject/projectsummary`)
        .then(res => res.json())
        .then(resObj => {
            resObj.data.forEach(projectSummaryObj => createProjectSummary(projectSummaryObj, wrap, taskPreviewIndicator));
        })
        // add back to body
//        .then(projectSummaryBox => document.querySelector('#main-project-summary-wrap').append(projectSummaryBox));
})();


(function getOtherProjectSummaries() {
    let wrap = document.querySelector('#other-projects-summary-wrap');
    let taskPreviewIndicator = 1;
    fetch(`users/${userId}/otherprojects/projectsummaries`)
        .then(res => res.json())
        .then(resObj => {
            resObj.data.forEach(projectSummaryObj => createProjectSummary(projectSummaryObj, wrap, taskPreviewIndicator));
        })
        // add back to body
//        .then(projectSummaryBox => document.querySelector('#other-projects-summary-wrap').append(projectSummaryBox))    ;

})();


//******************************************************
// create each project Summary (main or other project)
//******************************************************
function createProjectSummary (projectSummaryObj, wrap, taskPreviewIndicator) {
    // create parent div (projectSummaryBox)
    let projectSummaryBox = document.createElement('div');
    // create sections
    let topWrap = document.createElement('div');
    let midWrap = document.createElement('div');
    let bottomWrap = document.createElement('div');
    // create 4 children div (projectName, projectDeadline, 2 warnings, recent activity)
    // & paragraphs and add content as innerHTML
    let projectName = document.createElement('div');
    let name = document.createElement('p');
    name.innerHTML = projectSummaryObj.projectName;
    projectName.appendChild(name);

    let projectDeadline = document.createElement('div');
    let date = document.createElement('p');
    let options = { weekday: 'short', year: '2-digit', month: 'short', day: 'numeric' };
    date.innerHTML = new Date(projectSummaryObj.projectDeadline).toLocaleDateString("en-GB", options);
    projectDeadline.appendChild(date);

    if (projectSummaryObj.imminentDeadlineWarning !== null) {
        let imminentDeadlineWarning = document.createElement('div');
        // add warning icon
        let warningIcon = document.createElement('div');
        warningIcon.classList.add("bigger-icon-wrap");
        let img = document.createElement('img');
        img.setAttribute("src", "./images/attention.png");
        img.setAttribute("alt", "warning icon");
        warningIcon.appendChild(img);
        imminentDeadlineWarning.appendChild(warningIcon);
        // add warning message
        let imminentWarning = document.createElement('p');
        imminentWarning.innerHTML = projectSummaryObj.imminentDeadlineWarning;
        imminentWarning.classList.add("imminent-warning-msg")
        imminentDeadlineWarning.appendChild(imminentWarning);
        // add classes, attributes and append
        imminentDeadlineWarning.classList.add("warning");
        imminentDeadlineWarning.setAttribute('data-task_id', projectSummaryObj.taskId);
        midWrap.appendChild(imminentDeadlineWarning);
    }

    if (projectSummaryObj.passedDeadlineWarning !== null) {
        let passedDeadlineWarning = document.createElement('div');
        // add warning icon
        let warningIcon = document.createElement('div');
        warningIcon.classList.add("smaller-icon-wrap");
        let img = document.createElement('img');
        img.setAttribute("src", "./images/warning.png");
        img.setAttribute("alt", "warning icon");
        warningIcon.appendChild(img);
        passedDeadlineWarning.appendChild(warningIcon);
        // add warning message
        let passedWarning = document.createElement('p');
        passedWarning.innerHTML = projectSummaryObj.passedDeadlineWarning;
        passedWarning.classList.add("passed-warning-msg")
        passedDeadlineWarning.appendChild(passedWarning);
        // add classes, attributes and append
        passedDeadlineWarning.classList.add("warning");
        passedDeadlineWarning.setAttribute('data-task_id', projectSummaryObj.taskId);
        midWrap.appendChild(passedDeadlineWarning);
    }

    if (projectSummaryObj.recentActivity !== null) {
        let recentActivity = document.createElement('div');
        // add activity message
        let activity = document.createElement('p');
        activity.innerHTML = projectSummaryObj.recentActivity;
        recentActivity.appendChild(activity);
        // add classes, attributes and append
        recentActivity.classList.add("recent-activity");
        recentActivity.setAttribute('data-task_id', projectSummaryObj.taskId);
        midWrap.appendChild(recentActivity);
    }

    // add class
    projectSummaryBox.classList.add("project-summary-box");
    projectName.classList.add("project-name");
    projectDeadline.classList.add("deadline");
    topWrap.classList.add("top-project-summary-wrap");
    midWrap.classList.add("mid-project-summary-wrap");
    bottomWrap.classList.add("task-preview-wrap");
    bottomWrap.setAttribute("id", `${projectSummaryObj.projectName}` + "-task-preview-wrap");
    // set an attribute (to identify that specific project for a click listener [next])
    projectSummaryBox.setAttribute('data-task_id', projectSummaryObj.taskId);
    //projectSummaryBox.addEventListener('click', projectSummaryClick); // TODO add this
    // also add the attribute to all children // TODO is this needed on every element??
    projectName.setAttribute('data-task_id', projectSummaryObj.taskId);
    projectDeadline.setAttribute('data-task_id', projectSummaryObj.taskId);
    //append all children to parent div
    topWrap.appendChild(projectName);
    topWrap.appendChild(projectDeadline);
    projectSummaryBox.appendChild(topWrap);
    projectSummaryBox.appendChild(midWrap);
    projectSummaryBox.appendChild(bottomWrap);
    wrap.appendChild(projectSummaryBox);

    if (taskPreviewIndicator === 0 && projectSummaryObj.taskPreviews !== null ) {
        projectSummaryObj.taskPreviews.forEach(taskPreviewObj => createTaskPreview(taskPreviewObj, projectSummaryObj.projectName));
    }

    if (taskPreviewIndicator === 1 && projectSummaryObj.taskPreviews !== null ) {
        projectSummaryObj.taskPreviews.forEach(taskPreviewObj => createTaskList(taskPreviewObj, projectSummaryObj.projectName));
    }
}

//**********************************************
// create task list (other projects)
//**********************************************
function createTaskList(taskPreviewObj, projectName) {
    // create parent div (taskPreviewBox)
    let taskBox = document.createElement('div');
    taskBox.classList.add("task-box");


//    if (taskPreviewObj.taskPriority === "High") {
//        taskBox.classList.add("high-priority")
//    } else if (taskPreviewObj.taskPriority === "Low") {
//    taskBox.classList.add("low-priority")
//    } else {
//    taskBox.classList.add("medium-priority")
//    }

    let taskName = document.createElement('p');
    taskName.innerHTML = taskPreviewObj.taskDescription;
    taskName.classList.add("task-name");
    taskBox.appendChild(taskName);

    let taskDeadline = document.createElement('div');
    let date = document.createElement('p');
    let options = { weekday: 'short', year: '2-digit', month: 'short', day: 'numeric' };
    date.innerHTML = new Date(taskPreviewObj.taskDeadline).toLocaleDateString("en-GB", options);
    taskDeadline.appendChild(date);
    taskDeadline.classList.add("deadline");
    taskBox.appendChild(taskDeadline);
    let today = new Date();
    let deadline = new Date(taskPreviewObj.taskDeadline);
    const diffDays = (deadline - today)/1000/60/60/24;
    if (diffDays <= -1) {
        taskDeadline.classList.add("red-task")
    }
    if (-1 < diffDays && diffDays < 1) {
        taskDeadline.classList.add("amber-task")
    }

    // add back to body
    let selector = `${projectName}-task-preview-wrap`;
    document.getElementById(selector).appendChild(taskBox);
}


//**********************************************
// create each task preview (from main project)
//**********************************************
function createTaskPreview(taskPreviewObj, projectName) {
    // create parent div (taskPreviewBox)
    let taskPreviewBox = document.createElement('div');
    // create 4 children div (description, Owner, deadline, priority)
    // & paragraphs and add content as innerHTML
    let taskDescription = document.createElement('div');
    let description = document.createElement('p');
    description.innerHTML = taskPreviewObj.taskDescription;
    taskDescription.appendChild(description);

    let doneIcon = document.createElement('div');
    let icon = document.createElement('img');
    icon.setAttribute("src", "./images/done.png");
    icon.setAttribute("alt", "mark done");
    doneIcon.appendChild(icon);
    doneIcon.setAttribute('data-task_done', taskPreviewObj.taskId);
    doneIcon.addEventListener('click', taskCompleteClick); //TODO ** check **
    taskDescription.appendChild(doneIcon);

    let taskDeadline = document.createElement('div');
    let date = document.createElement('p');
    let options = { weekday: 'short', year: '2-digit', month: 'short', day: 'numeric' };
    date.innerHTML = new Date(taskPreviewObj.taskDeadline).toLocaleDateString("en-GB", options);
    taskDeadline.appendChild(date);
    let today = new Date();
    let deadline = new Date(taskPreviewObj.taskDeadline);
    const diffDays = (deadline - today)/1000/60/60/24;
    if (diffDays <= -1) {
        taskDeadline.classList.add("red-task")
    }
    if (-1 < diffDays && diffDays < 1) {
        taskDeadline.classList.add("amber-task")
    }

    let taskOwner = document.createElement('div');
    let taskOwnerPhoto = document.createElement('div');
    let img = document.createElement('img');
    img.setAttribute("src", taskPreviewObj.profilePicUrl);
    img.setAttribute("alt", "profile photo");
    taskOwnerPhoto.appendChild(img);
    taskOwner.appendChild(taskOwnerPhoto);

    let owner = document.createElement('p');
    owner.innerHTML = taskPreviewObj.taskOwner;
    taskOwner.appendChild(owner);

    let taskPriority = document.createElement('div');
    let priority = document.createElement('p');
    priority.innerHTML = taskPreviewObj.taskPriority;
    taskPriority.appendChild(priority);

    // create wrap for description and deadline
    let topWrap = document.createElement('div');
    topWrap.appendChild(taskDescription);
    topWrap.appendChild(taskDeadline);

    // create wrap for name and priority
    let bottomWrap = document.createElement('div');
//    bottomWrap.appendChild(taskOwnerPhoto);
    bottomWrap.appendChild(taskOwner);
    bottomWrap.appendChild(taskPriority);

    // add class
    taskPreviewBox.classList.add("task-preview-box");
    taskDescription.classList.add("task-description");
    taskDeadline.classList.add("deadline");
    taskOwnerPhoto.classList.add("img-wrap");
    doneIcon.classList.add("icon-wrap");
    taskOwner.classList.add("task-owner");
    taskPriority.classList.add("task-priority")
    topWrap.classList.add("top-task-preview-wrap");
    bottomWrap.classList.add("bottom-task-preview-wrap");
    // set an attribute called data-task_id with the value of the taskId for that specific task
    // this is used for a click listener [next]) // TODO is this needed on every element??
//    taskPreviewBox.setAttribute('data-task_id', taskPreviewObj.taskId);
//    topWrap.setAttribute('data-task_id', taskPreviewObj.taskId);
    bottomWrap.setAttribute('data-task_id', taskPreviewObj.taskId);
//    taskPreviewBox.addEventListener('click', taskPreviewClick);

    //append sections to parent div
    taskPreviewBox.appendChild(topWrap);
    taskPreviewBox.appendChild(bottomWrap);
    // add back to body
    let selector = `${projectName}-task-preview-wrap`;
    document.getElementById(selector).appendChild(taskPreviewBox);
}

//************************************************************
// TODO click listener for a click on each taskPreview
//************************************************************
// and then creates opens a task pane for the task_id clicked
//function taskPreviewClick(event) {
//    let taskId = event.target.dataset.task_id; // 'task_id' is the data attribute we assigned in createTaskPreview
//    // to the various elements of the preview box (these are the targets of the click event) - each data attribute
//    // holds the corresponding task ids of each preview
//    fetch(`/tasks/${taskId}`)
//    .then(res => res.json())
//    .then(resObj => {
//        // to only get the chat messages relevant to this chat in the chatBubble wrapper, reset the innerHTML to null every time
//        document.getElementById('project-summary-wrapper').innerHTML = "";
//        document.getElementById("send-message").dataset.chat_id = taskId;
//        resObj.data.forEach(chatBubbleObj => {
//            createChatBubble(chatBubbleObj)
//        })
//    })
//    // only allow form input after clicking on a chatPreview
//    document.getElementById("new-message").removeAttribute("disabled");
//
//    // change chat Pic to match chat clicked on: find the closest 'individual-msg-preview-box' element to the event target
//    // and set the src attribute of chat-photo>img to the src of that closest element
//    document.querySelector("#chat-photo > img").setAttribute("src", event.target.closest('.individual-msg-preview-box').querySelector('img').src);
//}

//************************************************************
// click listener for a click on each task's 'done' button
//************************************************************
// and then creates opens a task pane for the task_id clicked
function taskCompleteClick(event) {
    let taskId = event.currentTarget.dataset.task_done; // 'task_done' is the data attribute assigned to the done icon and
    // holds the taskId

    fetch(`/tasks/${taskId}/complete`)
        .then(res => res.json());
    location.reload();
    // change chat Pic to match chat clicked on: find the closest 'individual-msg-preview-box' element to the event target
    // and set the src attribute of chat-photo>img to the src of that closest element
//    document.querySelector("#chat-photo > img").setAttribute("src", event.target.closest('.individual-msg-preview-box').querySelector('img').src);
}

//************************************************************
// submit listener that waits for a user to enter a newMessage
//************************************************************
//prevents the default behaviour and prevents the page re-loading 20.27
//let messageForm = document.getElementById("save-task");
//
//messageForm.addEventListener('submit', function (event) {
//    event.preventDefault();
//    let newMessage = document.getElementById('new-task').value;
//    let userId = document.getElementById('user_id').value;
//    let newMsgObj = { // our API documentation says we need a senderId, a chatId, and a message content (& timestamp??)
//        senderId: parseInt(userId),
//        chatId: event.target.dataset.chat_id,
//        content: newMessage
//    }
//    createChatBubble(newMsgObj);
//    sendNewMessage(newMsgObj);
//    document.getElementById('new-message').value = ""; //resets form value
//});

//**************************************
// a POST request to post/send a new msg
//**************************************
function createNewTask(newTask) { // this task object contains attributes stated in lines 59-61
    let postParams = {
        method: 'POST', // GET, POST, PUT, DELETE, etc
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            'Access-Control-Allow-Headers': "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With",
            'Access-Control-Allow-Origin': "*"
        },
        body: JSON.stringify(newTask) // converts it to String to post
    }
    let taskId = newTask.projectId;
    fetch(`/projects/${projectId}/task`, postParams)
         .then(res => res.json())
}


//************************************************************
// click listener to add HouseMembers to project
//************************************************************
//let addProjectStakeholder = document.getElementById("add-stakeholder-btn");
//let addProjectStakeholderModalBody = document.getElementById("add-stakeholder-modal-body");
//addProjectStakeholderProjectModalBody.innerHTML = "now loading...";
//addProjectStakeholder.addEventListener('click', findStakeholdersToAdd)


//************************************************************
// function to determine who we can add as a project stakeholder
//************************************************************
function findStakeholdersToAdd() {
    let remainingUsers =[];
    const p1 = fetch(`${baseUrl}`); //fetch all users
    const p2 = fetch(`${baseUrl}/${projectId}/users/`); //fetch all users who are already stakeholders

    Promise.all([p1, p2])
        .then(results => Promise.all(results.map(res => res.json())))
        .then(resp => {
            let allUsers = resp[0].data;
            let existingStakeholders = resp[1].data

            // compare the 2 arrays
            for(var i = 0; i < allUsers.length; i ++) {
                if(existingStakeholders.indexOf(allUsers[i].userId) === -1){
                    remainingUsers.push(allUsers[i]);
                }
            }
            console.log(remainingUsers)
            populateAddStakeholderDropDown(remainingUsers)
        })
}


//************************************************************
// populate list of users we can add as stakeholder
//************************************************************
function populateAddStakeholderDropDown(remainingUsers) {
// we create a form element because we want to add a Submit event listener and it's harder to do with a number of Strings??
    let form = document.createElement('form');
    form.id = `add-stakeholder-form`;

    let formString = ``;
    formString += `<input id="new-stakeholder" type="text" list="users-list" class="form-control">`;
    formString += `<datalist id="users-list">`
    remainingUsers.forEach(user => { //below: data-value gets passed, but users only see firstName, lastName
        formString += `<option data-value="${user.userId}" value="${user.firstName} ${user.lastName}"></option>`
    })
    formString += `</datalist>`
    formString += `<input type="submit" class="btn btn-success">`
    form.innerHTML = formString;
    form.addEventListener('submit', newStakeholderSubmit)
    addProjectStakeholderModalBody.innerHTML = "";
    addProjectStakeholderModalBody.appendChild(form);
}
//TODO ********** update this **************
function newStakeholderSubmit(e){
    e.preventDefault()
    let options = document.getElementById('users-list').options;
    console.log(document.getElementById('users-list').options)
    console.log(e.target.elements)
    let val = e.target.elements["new-stakeholder"].value
    console.log(val)
    let chatName;
    let duoUserId;
    Array.from(options).forEach(option => {
        if (option.value === val) { //what does this line do?
            chatName = option.getAttribute('value');
            duoUserId = option.getAttribute('data-value');
        }
    })

    let postData = {
        "chatName": chatName,
        "chatPhotoUrl": 'https://cdn.pixabay.com/photo/2019/08/11/18/48/icon-4399681_960_720.png'
    };

    let postParams = {
            method: 'POST', //*GET, POST, PUT, DELETE, etc
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Headers': "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With",
                'Access-Control-Allow-Origin': "*"
            },
            body: JSON.stringify(postData)
        }

        fetch(`/chats/${userId}/${duoUserId}`, postParams)
             .then(res => res.json())
}

