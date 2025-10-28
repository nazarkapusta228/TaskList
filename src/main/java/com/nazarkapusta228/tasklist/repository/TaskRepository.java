package com.nazarkapusta228.tasklist.repository;

import com.nazarkapusta228.tasklist.model.Task;

import java.util.List;


public interface TaskRepository {
    public void addTask(Task task);
    public void removeTask(int id);
    public void updateTask(Task task);
    public boolean isDone(int id);
    public List<Task> findTasksByTitle(String titlePart);
    public List<Task> findTasksByDoneStatus(boolean done);
}
