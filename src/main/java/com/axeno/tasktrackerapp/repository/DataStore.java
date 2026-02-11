package com.axeno.tasktrackerapp.repository;

import com.axeno.tasktrackerapp.model.Individual;
import com.axeno.tasktrackerapp.model.Program;

import java.util.ArrayList;
import java.util.List;

public class DataStore{
    private static DataStore dataStore;



    private List<Individual> individuals;
    private List<Program> programs;

    private DataStore(){
        individuals = new ArrayList<>();
        programs= new ArrayList<>();
    }

    public static DataStore getInstance()
    {
        if(dataStore==null)
        {
            dataStore= new DataStore();
        }
        return dataStore;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
}
