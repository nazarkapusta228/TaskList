package com.nazarkapusta228.tasklist.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

//@Entity
public class Task {
    private int id;
    private String title;
    private String description;
    private boolean isDone;
    private LocalDate deadline;

    public Task() {}

    public Task( String title, String description, LocalDate deadline, boolean done){

        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isDone = done;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }


    public boolean isDone(){
        return isDone;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public LocalDate getDeadline(){
        return deadline;
    }

    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }





}
