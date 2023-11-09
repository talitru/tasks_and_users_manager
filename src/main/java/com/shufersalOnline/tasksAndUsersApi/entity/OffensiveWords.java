package com.shufersalOnline.tasksAndUsersApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OffensiveWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String term;

    //getters
    public Long getId(){
        return id;
    }

    public String getTerm(){
        return term;
    }

    //setters
    public void setId(Long id){
        this.id=id;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
