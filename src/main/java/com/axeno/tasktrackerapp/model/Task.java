package com.axeno.tasktrackerapp.model;

import java.util.List;

public class Task extends BaseEntity{


    private TaskStatus taskStatus=TaskStatus.NotAssigned;
    private List<Employee> assignedEmployeesList;


    public Task(String id, String name, String description) {
        super(id, name, description);
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
