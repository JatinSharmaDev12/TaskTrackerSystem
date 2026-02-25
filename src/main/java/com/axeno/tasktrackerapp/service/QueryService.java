package com.axeno.tasktrackerapp.service;

import com.axeno.tasktrackerapp.model.*;
import com.axeno.tasktrackerapp.repository.DataStore;

import java.util.ArrayList;
import java.util.List;

public class QueryService {

    private final DataStore dataStore;

    public QueryService() {
        this.dataStore = DataStore.getInstance();
    }

    /*
     Find all programs where a given Individual is enrolled
     */
    public List<Program> getProgramsForIndividual(String individualId) {

        List<Program> result = new ArrayList<>();

        for (Program program : dataStore.getPrograms()) {
            for (Project project : program.getProjects()) {
                if (isIndividualEnrolledInProject(project, individualId)) {
                    result.add(program);
                    break;
                }
            }
        }

        return result;
    }

    /*
     Find all projects where a given Individual is enrolled
     */
    public List<Project> getProjectsForIndividual(String individualId) {

        List<Project> result = new ArrayList<>();

        for (Program program : dataStore.getPrograms()) {
            for (Project project : program.getProjects()) {
                if (isIndividualEnrolledInProject(project, individualId)) {
                    result.add(project);
                }
            }
        }

        return result;
    }

    /*
     Helper: check if individual enrolled in project
     */
    private boolean isIndividualEnrolledInProject(Project project, String individualId) {
        return project.getEnrolledIndividualIds() != null && project.getEnrolledIndividualIds().contains(individualId);
    }

    /*
     Helper: check if individual assigned to task
     */
    private boolean isIndividualAssignedToTask(Task task, String individualId) {
        return task.getAssignedIndividualIds() != null && task.getAssignedIndividualIds().contains(individualId);
    }

    /*
     Helper: check if task is pending
     */
    private boolean isTaskPending(Task task) {
        return task.getStatus() != TaskStatus.COMPLETED;
    }

    /*
     Identify total pending tasks for a given user
     */
    public int getTotalPendingTasksForIndividual(String individualId) {

        int count = 0;

        for (Program program : dataStore.getPrograms()) {
            for (Project project : program.getProjects()) {
                for (Task task : project.getTasks()) {
                    if (isIndividualAssignedToTask(task, individualId)
                            && isTaskPending(task)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /*
     Identify total pending tasks for a given user in a specific program
     */
    public int getTotalPendingTasksForIndividualInProgram(String individualId,
                                                           String programId) {

        int count = 0;

        for (Program program : dataStore.getPrograms()) {
            if (program.getId().equals(programId)) {

                for (Project project : program.getProjects()) {
                    for (Task task : project.getTasks()) {
                        if (isIndividualAssignedToTask(task, individualId)
                                && isTaskPending(task)) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    /*
     Identify total pending tasks for a given project
     */
    public int getTotalPendingTasksForProject(String projectId) {

        int count = 0;

        for (Program program : dataStore.getPrograms()) {
            for (Project project : program.getProjects()) {
                if (project.getId().equals(projectId)) {

                    for (Task task : project.getTasks()) {
                        if (isTaskPending(task)) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    /*
     Identify the program name for a given project
     */
    public String getProgramNameForProject(String projectId) {
        String programName = null;
        for (Program program : dataStore.getPrograms()) {
            for (Project project : program.getProjects()) {
                if (project.getId().equals(projectId)) {
                    programName=program.getName();
                }
            }
        }

        return programName;
    }
}
