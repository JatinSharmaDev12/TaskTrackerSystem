package com.axeno.tasktrackerapp.service;

import com.axeno.tasktrackerapp.model.*;
import com.axeno.tasktrackerapp.repository.DataStore;

import java.util.ArrayList;
import java.util.List;

public class QueryService {


   // List<Individual> individuals = DataStore.getInstance().getIndividuals();
    List<Program> programs = DataStore.getInstance().getPrograms();
    /**
      *Find All Programs where an Individual is Enrolled
     */

    public List<Program> getProgramsByIndividual(String individualId)
    {
        List<Program> programsByInd=new ArrayList<>();
        for(Program program : programs)
        {
            for(Project project : program.getProjects())
            {
                for(String ids : project.getEnrolledIndividualIds())
                {
                    if(ids.equals(individualId))
                    {
                        programsByInd.add(program);
                    }
                }
            }
        }
        return programsByInd;
    }
    /**
    Find All Projects where an Individual is Enrolled
     */
   public  List<Project> getProjectsByIndividual(String individualId)
   {
       List<Project> projectsByInd=new ArrayList<>();
       for(Program program : programs)
       {
           for(Project project : program.getProjects())
           {
               for(String ids : project.getEnrolledIndividualIds())
               {
                   if(ids.equals(individualId))
                   {
                       projectsByInd.add(project);
                   }
               }
           }
       }
       return projectsByInd;

   }

    /**
     * Find All Pending Tasks for a Individual
     */
    public List<Task> getTotalPendingTasksByIndividual(String individualId)
    {
        List<Task> pendingTasks=new ArrayList<>();
        for(Program program : programs)
        {
            for(Project project : program.getProjects())
            {
                for(Task task : project.getTasks())
                {
                    if(task.getStatus()== TaskStatus.PENDING)
                    {
                        for(String ids : task.getAssignedIndividualIds())
                        {
                            if(ids.equals(individualId)) pendingTasks.add(task);
                        }
                    }

                }
            }
        }
        return pendingTasks;
    }
   public  List<Task> getPendingTasksByUserAndProgram(String individualId, String programId)
    {
        List<Task> pendingTasks=new ArrayList<>();
        for(Program program : programs) {
            if (program.getId().equals(programId)) {
                for (Project project : program.getProjects()) {
                    for (Task task : project.getTasks()) {
                        if (task.getStatus() == TaskStatus.PENDING) {
                            for (String ids : task.getAssignedIndividualIds()) {
                                if (ids.equals(individualId)) pendingTasks.add(task);
                            }
                        }

                    }
                }
            }
        }
        return pendingTasks;
    }

    public List<Task> getPendingTasksByProject(String projectId)
    {

        List<Task> pendingTasks=new ArrayList<>();
        for(Program program : programs) {
                for (Project project : program.getProjects()) {
                    if (project.getId().equals(projectId)) {
                    for (Task task : project.getTasks()) {
                        if (task.getStatus() == TaskStatus.PENDING) {
                            pendingTasks.add(task);
                        }
                    }
                }
            }
        }
        return pendingTasks;
    }


    public String getProgramNameByProject(String projectId)
    {
        String programByProject = null;
        for(Program program : programs) {
            for (Project project : program.getProjects()) {
                if(project.getId().equals(projectId)) {
                  programByProject=program.getName();
                }
            }
        }
        return programByProject;
    }

}
