package com.axeno.tasktrackerapp.model;

import java.util.List;

public class Task {

    private String taskId;
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private List<Employee> assignedEmployeesList;
}
