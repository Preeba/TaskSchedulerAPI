package org.example.taskscheduler.service;

import org.example.taskscheduler.model.RecurrenceSchedule;
import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.model.TaskStatus;
import org.example.taskscheduler.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    // Inject Task Repository once database is initialized and configured
    // Constructor to pre-populate some tasks for testing purposes
    private final Map<String, Task> tasks = new HashMap<String, Task>();
    @Autowired
    private TaskRepository taskRepository;

//    public TaskService() {
//        // Add some sample tasks
//        Task task1 = new Task("1", "Task 1", "Desc 1", 1, TaskStatus.PENDING, LocalDateTime.now().minusDays(2), RecurrenceSchedule.DAILY, new ArrayList<>(), new ArrayList<>());
//        Task task2 = new Task("2", "Task 2", "Description 2", 2, TaskStatus.IN_PROGRESS, LocalDateTime.now().plusDays(1), RecurrenceSchedule.WEEKLY, new ArrayList<>(), new ArrayList<>());
//
//        tasks.put(task1.getId(), task1);
//        tasks.put(task2.getId(), task2);
//    }


    public Task getTaskById(String id) {
        return tasks.get(id);
    }

    public List<Task> getTasks(TaskStatus status) {
        System.out.println("Fetching tasks with status: " + status);
        return taskRepository.findAllTasks(Optional.ofNullable(status));
//        return tasks.values().stream()
//                .filter(t -> status == null || t.getStatus().equals(status))
//                .collect(Collectors.toList());
    }

    public Task getNextTaskToExecute() {
        return tasks.values().stream()
                .filter(task -> task.getStatus() == TaskStatus.PENDING)
                .findFirst().orElse(null);
    }

    public Task createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        if (savedTask == null) {
            throw new RuntimeException("Error saving task");
        }
        return savedTask;
    }

    public Task updateTask(String id, Task task) {
        // Update task and return the updated task
        Task currentTask = tasks.get(id);
        if (currentTask != null) {
            task.setId(id);
            tasks.put(id, task);
        }
        return task;
    }

    public boolean deleteTask(String id) {
        return tasks.remove(id) != null;
    }

    // Mark the task as completed and return it
    public Task markTaskCompleted(String id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setStatus(TaskStatus.COMPLETED);
        }
        return task;
    }

    // Reschedule task and return it
    public Task rescheduleTask(String id, LocalDateTime nextExecutionTime) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setNextExecutionTime(nextExecutionTime);
        }
        return task;
    }

}
