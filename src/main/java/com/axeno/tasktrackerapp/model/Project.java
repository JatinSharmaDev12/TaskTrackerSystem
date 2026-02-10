package com.axeno.tasktrackerapp.model;

import java.util.List;
import java.util.Map;

public class Project extends BaseEntity{

    private List<Task> tasksList;


    public Project(String id, String name, String description) {
        super(id, name, description);
    }
}
