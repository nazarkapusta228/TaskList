package com.nazarkapusta228.tasklist.service;

import com.nazarkapusta228.tasklist.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TaskServiceTest {
    TaskService service;


    @BeforeEach
    void setup() {
        service = new TaskService();
    }

    @Test
    void addValidTask() {
        Task task = new Task("Test", "Description", LocalDate.now().plusDays(1), false);
        Task task1 = new Task("Dishes", "Do the dishes", LocalDate.now().plusDays(2), false);

        service.addTask(task);
        service.addTask(task1);

        assertEquals(1, service.findTasksByTitle("Test").size());
        assertEquals(1, service.findTasksByTitle("Dishes").size());
    }


    @Test
    void addInvalidTask() {
        Task task = new Task("", "Description", LocalDate.now().plusDays(1), false);
        Task task1 = new Task("Dishes", "", LocalDate.now().plusDays(2), false);
        Task task2 = new Task("Task", "Description", LocalDate.now().minusDays(1), false);

        service.addTask(task);
        service.addTask(task1);
        service.addTask(task2);

        assertEquals(0, service.findTasksByTitle("").size());
        assertEquals(0, service.findTasksByTitle("Dishes").size());
        assertEquals(0, service.findTasksByTitle("Task").size());
    }


    @Test
    void removeExistingTask(){
        Task task = new Task("Task1", "Description", LocalDate.now().plusDays(1), false);

        service.addTask(task);
        service.removeTask(0);

        assertEquals(0, service.findTasksByTitle("Task1").size());
    }


    @Test
    void removeUnexistingTask(){
        service.removeTask(0);

        assertEquals(0, service.findTasksByTitle("").size());

    }


    @Test
    void updateTaskCorrectly(){
        Task task = new Task("Task1", "Description", LocalDate.now().plusDays(1), false);

        service.addTask(task);

        task.setTitle("Task2");
        task.setDeadline(LocalDate.now().plusDays(3));
        task.setDescription("Des");
        task.setIsDone(true);

        service.updateTask(task);

        assertEquals(1, service.findTasksByTitle("Task2").size());
        assertEquals(0, service.findTasksByTitle("Task1").size());

    }


    @Test
    void updateNullTask(){
        Task task = new Task("", "Description", LocalDate.now().plusDays(1), false);

        service.updateTask(task);

        assertEquals(0, service.findTasksByTitle("").size());
    }

    @Test
    void updateTaskWithInvalidInfo() {
        TaskService service = new TaskService();
        Task task0 = new Task("Task0", "Description0", LocalDate.now().plusDays(1), false);
        service.addTask(task0);

        Task invalidUpdate = new Task("Task0", "", LocalDate.now().plusDays(1), false);
        invalidUpdate.setId(task0.getId());

        service.updateTask(invalidUpdate);

        assertEquals("Description0", service.findTaskByID(task0.getId()).getDescription(),
                "The description of Task0 was changed incorrectly");
    }


    @Test
    void getValidTaskStatusForExistingTask() {
        TaskService service = new TaskService();
        Task task = new Task("Task", "Des", LocalDate.now().plusDays(1), false);
        service.addTask(task);

        boolean status = service.getTaskStatus(task.getId());
        assertEquals(false, status, "Task should not be marked as done when created");
    }

    @Test
    void getTaskForUnexistingTask() {
        TaskService service = new TaskService();

        boolean status = service.getTaskStatus(0);

        assertEquals(false, status, "Method should return false when task with given ID does not exist");
    }



    @Test
    void findDoneTasks() {
        TaskService service = new TaskService();
        Task task1 = new Task("Task1", "Desc1", LocalDate.now().plusDays(1), true);
        Task task2 = new Task("Task2", "Desc2", LocalDate.now().plusDays(1), false);
        service.addTask(task1);
        service.addTask(task2);

        List<Task> doneTasks = service.findTasksByDoneStatus(true);

        assertEquals(1, doneTasks.size());
        assertTrue(doneTasks.get(0).isDone());
    }

    @Test
    void findNotDoneTasks() {
        TaskService service = new TaskService();
        Task task1 = new Task("Task1", "Desc1", LocalDate.now().plusDays(1), true);
        Task task2 = new Task("Task2", "Desc2", LocalDate.now().plusDays(1), false);
        service.addTask(task1);
        service.addTask(task2);

        List<Task> notDoneTasks = service.findTasksByDoneStatus(false);

        assertEquals(1, notDoneTasks.size());
        assertFalse(notDoneTasks.get(0).isDone());
    }

    @Test
    void checkIfTaskWasAdded(){
        Task task1 = new Task("task", "description", LocalDate.now().plusDays(1), false);
        service.addTask(task1);
        Task task2 = new Task("task", "description", LocalDate.now().plusDays(1), false);
        boolean taskWasAdded = service.callcheckIfTaskWasAdded(task2);
        assertEquals(true,taskWasAdded, "Task was not added" );

    }

    @Test
    void checkIfTaskWasNotAdded() {
        Task task1 = new Task("task", "description", LocalDate.now().plusDays(1), false);
        service.addTask(task1);

        // task2 має інший title, тому не повинен вважатись "вже доданим"
        Task task2 = new Task("task1", "description", LocalDate.now().plusDays(1), false);

        boolean taskWasAdded = service.callcheckIfTaskWasAdded(task2);

        assertEquals(false, taskWasAdded, "Task should not be marked as already added");
    }


}