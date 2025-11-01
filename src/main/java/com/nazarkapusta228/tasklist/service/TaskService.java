package com.nazarkapusta228.tasklist.service;

import com.nazarkapusta228.tasklist.model.Task;
import com.nazarkapusta228.tasklist.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements TaskRepository {
    private List<Task> tasksList = new ArrayList<>();

    private int nextId = 0;

    @Override
    public void addTask(Task task){
        if (!checkIfTaskWasAdded(task) && checkTask(task)) {
            task.setId(nextId++);
            tasksList.add(task);
            System.out.println("Task was successfully added");
        }
        else {
            System.out.println("Task was not added");
        }
    }


    @Override
    public boolean removeTask(int id) {
        Task task = findTaskByID(id);
        if (task != null) {
            tasksList.remove(task);
            System.out.println("Task removed successfully");
            return true;
        }
        else {
            System.out.println("Task was not removed");
            return false;
        }
    }


    @Override
    public void updateTask(Task task) {
        Task existingTask = findTaskByID(task.getId());

        if (existingTask == null) {
            System.out.println("Task not found");
            return;
        }

        Task temp = new Task(task.getTitle(), task.getDescription(), task.getDeadline(), task.isDone());
        temp.setId(task.getId());

        if (!checkTask(temp)) {
            System.out.println("Invalid task data, update failed");
            return;
        }

        existingTask.setTitle(temp.getTitle());
        existingTask.setDescription(temp.getDescription());
        existingTask.setDeadline(temp.getDeadline());
        existingTask.setIsDone(temp.isDone());

        System.out.println("Task updated successfully");
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


//    public void clearTasks() {
//        tasksList.clear();
//        nextId = 0;
//    }


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
        return !task.getDeadline().isBefore(LocalDate.now());
    }





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

    public boolean callcheckIfTaskWasAdded(Task task){
        return checkIfTaskWasAdded(task);
    }



    public Task findTaskByID(int id){
        for(Task t : tasksList) {
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

}
