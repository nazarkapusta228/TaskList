package com.nazarkapusta228.tasklist.service;

import com.nazarkapusta228.tasklist.model.Task;
import com.nazarkapusta228.tasklist.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements TaskRepository {
    private List<Task> tasksList = new ArrayList<>();


    @Override
    public void addTask(Task task){
        if (checkTask(task) & !checkIfTaskWasAdded(task)) {
            tasksList.add(task);
            System.out.println("Task was successfully added");
        }
    }


    @Override
    public void removeTask(int id){

    }

    @Override
    public void updateTask(Task task){

    }

    @Override
    public boolean isDone(int id){
        return true;
    }

    @Override
    public List<Task> findTasksByTitle(String titlePart){
        return null;
    }

    @Override
    public List<Task> findTasksByDoneStatus(boolean done){
        return null;
    }





    // methods for checking if the task that is being added has all its required fields filled
    // and whether the task was successfully added to the list
    private boolean checkTask(Task task){
        if(task.getDescription().isEmpty() || task.getTitle().isEmpty()
                || !checkDeadline(task)  ){
            return false;
        }
        return true;

    }

    private boolean checkDeadline(Task task){
        if(!task.getDeadline().isBefore(LocalDate.now())){
            return false;
        }
        return true;
    }



    //at the moment unused
    private boolean checkIfTaskWasAdded(Task task){
        for(Task t : tasksList){
            if(t.getTitle().equals(task.getTitle())
                    && t.getDeadline().equals(task.getDeadline())
                    && t.getDescription().equals(task.getDescription())){

                return true;
            }

        }
        return false;
    }

}
