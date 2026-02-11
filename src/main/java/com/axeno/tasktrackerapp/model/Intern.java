package com.axeno.tasktrackerapp.model;

public class Intern extends BaseEntity implements Individual {
    public Intern() {
    }

    public Intern(String id, String name, String description) {
        super(id, name, description);
    }

}
