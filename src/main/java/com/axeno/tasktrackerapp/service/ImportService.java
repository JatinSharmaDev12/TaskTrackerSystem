package com.axeno.tasktrackerapp.service;

import com.axeno.tasktrackerapp.dto.ImportDataWrapper;
import com.axeno.tasktrackerapp.repository.DataStore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/*
-read JSON
- convert to POJOs
- Populate DataStore
 */
public class ImportService {

    public void importData(File file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ImportDataWrapper importDataWrapper = objectMapper.readValue(file,ImportDataWrapper.class);

        DataStore dataStore = DataStore.getInstance();
        dataStore.setIndividuals(importDataWrapper.getIndividuals());
        dataStore.setPrograms(importDataWrapper.getPrograms());
    }

}
