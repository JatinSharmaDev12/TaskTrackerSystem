package com.axeno.tasktrackerapp.service;

import com.axeno.tasktrackerapp.dto.ImportDataWrapper;
import com.axeno.tasktrackerapp.exception.ValidationException;
import com.axeno.tasktrackerapp.model.Individual;
import com.axeno.tasktrackerapp.model.Program;
import com.axeno.tasktrackerapp.model.Project;
import com.axeno.tasktrackerapp.model.Task;
import com.axeno.tasktrackerapp.repository.DataStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
-read JSON
- convert to POJOs
- Populate DataStore
 */
public class ImportService {

    public void importData(File file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ImportDataWrapper importDataWrapper = objectMapper.readValue(file, ImportDataWrapper.class);

        validateData(importDataWrapper);

        DataStore dataStore = DataStore.getInstance();
        dataStore.setIndividuals(importDataWrapper.getIndividuals());
        dataStore.setPrograms(importDataWrapper.getPrograms());
    }

    private void validateData(ImportDataWrapper data) {
        if (data.getIndividuals() == null || data.getPrograms() == null) {
            throw new ValidationException("Import data must contain both individuals and programs.");
        }

        // Validate Individual IDs uniqueness
        Set<String> individualIds = new HashSet<>();
        for (Individual ind : data.getIndividuals()) {
            if (!individualIds.add(ind.getId())) {
                throw new ValidationException("Duplicate Individual ID found: " + ind.getId());
            }
        }

        // Validate Program IDs and Project IDs uniqueness
        Set<String> programIds = new HashSet<>();
        Set<String> projectIds = new HashSet<>();

        for (Program program : data.getPrograms()) {
            if (!programIds.add(program.getId())) {
                throw new ValidationException("Duplicate Program ID found: " + program.getId());
            }

            if (program.getProjects() != null) {
                for (Project project : program.getProjects()) {
                    if (!projectIds.add(project.getId())) {
                        throw new ValidationException("Duplicate Project ID found: " + project.getId()); // Global uniqueness for projects
                    }

                    // Validate references in Project
                    validateProjectReferences(project, individualIds);
                }
            }
        }
    }

    private void validateProjectReferences(Project project, Set<String> validIndividualIds) {
        // Validate enrolled individuals
        if (project.getEnrolledIndividualIds() != null) {
            for (String indId : project.getEnrolledIndividualIds()) {
                if (!validIndividualIds.contains(indId)) {
                    throw new ValidationException("Project " + project.getId() + " references unknown Individual ID: " + indId);
                }
            }
        }

        // Validate tasks
        if (project.getTasks() != null) {
            for (Task task : project.getTasks()) {
                if (task.getAssignedIndividualIds() != null) {
                    for (String indId : task.getAssignedIndividualIds()) {
                        if (!validIndividualIds.contains(indId)) {
                            throw new ValidationException("Task " + task.getId() + " in Project " + project.getId() + " references unknown Individual ID: " + indId);
                        }
                    }
                }
            }
        }
    }

}
