package org.example.taskscheduler.repository;

import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.model.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TaskRepository { // An in-memory repository

    private final Map<String, Task> taskStore = new HashMap<String, Task>();

    public Task save(Task task) {
        System.out.println("Loaded data "+ taskStore);
        if (task.getId() == null || (task.getId()).isEmpty()) {
            //generate ID for the task
            task.setId((UUID.randomUUID().toString()) );
        }
        taskStore.put(task.getId(), task);
        return task;
    }

    public List<Task> findAllTasks(Optional<TaskStatus> status) {
        System.out.println("Tasks in repository: " + taskStore.values());

        if (status.isPresent()) {
            return taskStore.values().stream()
                    .filter(task -> task.getStatus() == status.get()).toList();
        } else {
            return new ArrayList<>(taskStore.values());
        }
    }

    public Set<Task> findAllByIds(Set<String> dependencyIds) {
        return dependencyIds.stream()
                .map(taskStore::get)
                .collect(Collectors.toSet());
    }

    public Optional<Task> findById(String taskId) {
        return taskStore.values().stream().filter(taskId::equals).findFirst();
    }

    public Optional<Task> findFirst() {
        return taskStore.values().stream().findFirst();
    }
}
