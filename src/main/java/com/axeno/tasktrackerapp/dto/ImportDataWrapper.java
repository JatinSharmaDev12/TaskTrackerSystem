package com.axeno.tasktrackerapp.dto;

import com.axeno.tasktrackerapp.model.Individual;
import com.axeno.tasktrackerapp.model.Program;

import java.util.List;

public class ImportDataWrapper {
    private List<Individual> individuals;
    private List<Program> programs;

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
