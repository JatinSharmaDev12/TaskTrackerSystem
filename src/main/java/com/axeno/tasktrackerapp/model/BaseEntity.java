package com.axeno.tasktrackerapp.model;

public abstract class BaseEntity {

    private String id ;
    private String name;
    private String description;

    public BaseEntity(){};
    public BaseEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
