package com.axeno.tasktrackerapp.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;



@JsonTypeInfo(
        use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property="type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = Employee.class,name = "EMPLOYEE"),
                @JsonSubTypes.Type(value = Intern.class , name = "INTERN")
        }
        )
public interface Individual {

    String getId();
    String getName();


}