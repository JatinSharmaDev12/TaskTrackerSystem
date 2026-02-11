package com.axeno.tasktrackerapp.model;

public class Employee extends BaseEntity implements Individual {

    public Employee() {}

    public Employee(String id, String name, String description) {
        super(id, name, description);
    }


}
