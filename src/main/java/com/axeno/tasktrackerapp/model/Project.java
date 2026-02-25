package com.axeno.tasktrackerapp.model;

import java.util.List;
import java.util.Map;

public class Project extends BaseEntity{

    private List<Task> tasks;

    private TaskStatus status;
    private List<String> enrolledIndividualIds;

    public Project() {
    }

    public Project(String id, String name, String description) {
        super(id, name, description);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public List<String> getEnrolledIndividualIds() {
        return enrolledIndividualIds;
    }
}
