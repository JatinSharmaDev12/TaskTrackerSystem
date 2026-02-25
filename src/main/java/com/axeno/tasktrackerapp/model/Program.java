package com.axeno.tasktrackerapp.model;

import java.util.List;

public class Program extends BaseEntity {

    private List<Project> projects;

    public Program() {
    }

    public Program(String id, String name, String description) {
        super(id, name, description);
    }

    public List<Project> getProjects() {
        return projects;
    }

}
