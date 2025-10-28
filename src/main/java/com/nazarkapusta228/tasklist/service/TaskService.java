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
    public void removeTask(int id) {
        Task task = findTaskByID(id);
        if (task != null) {
            tasksList.remove(task);
            System.out.println("Task removed successfully");
        }
        else {
            System.out.println("Task was not removed");
        }
    }


    @Override
    public void updateTask(Task task) {
         Task existingTask = findTaskByID(task.getId());
         if (existingTask!=null & checkTask(task)){
            existingTask.setDeadline(task.getDeadline());
            existingTask.setDescription(task.getDescription());
            existingTask.setTitle(task.getTitle());
            existingTask.setIsDone(task.isDone());
         }
         else {
             System.out.println("Task not found");
         }

    }
    @Override
    public boolean getTaskStatus(int id){
        Task task = findTaskByID(id);
        if(task != null){
            return task.isDone();
        }
        return false;
    }

    @Override
    public List<Task> findTasksByTitle(String titlePart){
        List<Task> tasksFoundByTitlePart = new ArrayList<>();
        for(Task task : tasksList) {
            if(task.getTitle().toLowerCase().contains(titlePart.toLowerCase())){
                tasksFoundByTitlePart.add(task);
            }
        }
        return tasksFoundByTitlePart;
    }

    @Override
    public List<Task> findTasksByDoneStatus(boolean done) {
        List<Task> tasksFoundByDoneStatus = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.isDone() == done) {
                tasksFoundByDoneStatus.add(task);
            }

        }
        return tasksFoundByDoneStatus;
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




    private Task findTaskByID(int id){
        for(Task t : tasksList){
            if(t.getId() == id){
                return t;
            }
        }
         return null;
    }

}
