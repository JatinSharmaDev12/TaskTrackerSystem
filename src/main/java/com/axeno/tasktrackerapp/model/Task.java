package com.axeno.tasktrackerapp.model;

import java.util.List;

public class Task extends BaseEntity{


    private TaskStatus status;
    private List<String> assignedIndividualIds;

    public Task() {
    }


    public Task(String id, String name, String description) {
        super(id, name, description);

    }

    public TaskStatus getStatus() {
        return status;
    }

    public List<String> getAssignedIndividualIds() {
        return assignedIndividualIds;
    }



}
