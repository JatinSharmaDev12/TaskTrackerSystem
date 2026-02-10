package com.axeno.tasktrackerapp.model;

import java.util.List;

public class Program extends BaseEntity {

    private List<Project> projectList;

    public Program(String id, String name, String description) {
        super(id, name, description);
    }
}
