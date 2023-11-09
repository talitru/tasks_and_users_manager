package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.entity.OffensiveWords;
import com.shufersalOnline.tasksAndUsersApi.repository.OffensiveWordsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class OffensiveWordsLoader {
    @Autowired
    private OffensiveWordsRepository offensiveWordsRepository;

    @PostConstruct
    public ResponseEntity<String> loadOffensiveWords(){
        //read the file and save the terms to database
        // each line ia a row in the table
        try (BufferedReader reader = new BufferedReader(new FileReader("offensiveWords.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                OffensiveWords term = new OffensiveWords();
                term.setTerm(line);
               offensiveWordsRepository.save(term);
            }
            return ResponseEntity.ok("Offensive words loaded successfully");
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace(); // Log the exception

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed loading offensive words to the database");
        }
    }

}
