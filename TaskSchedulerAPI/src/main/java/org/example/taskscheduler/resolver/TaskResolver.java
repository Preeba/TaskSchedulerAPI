package org.example.taskscheduler.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.input.TaskInput;
import org.example.taskscheduler.model.TaskStatus;
import org.example.taskscheduler.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class TaskResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private TaskService taskService;

    public Task task(String id) {
        return taskService.getTaskById(id);
    }

    public List<Task> tasks(TaskStatus status) {
        return taskService.getTasks(status);
    }

    public Task nextTaskToExecute() {
        return taskService.getNextTaskToExecute();
    }

    public Task createTask(TaskInput input) {
        Task task = new Task();
        task.setName(input.getName());
        task.setDescription(input.getDescription());
        task.setPriority(input.getPriority());
        task.setRecurrence(input.getRecurrence());
        // Handle dependencies
        Task savedTask = taskService.createTask(task);
        // Ensure the savedTask is not null
        if (savedTask == null) {
            throw new RuntimeException("Failed to create task");
        }
        return savedTask;
    }

    public Task updateTask(String id, TaskInput input) {
        Task task = new Task();
        task.setName(input.getName());
        task.setDescription(input.getDescription());
        task.setPriority(input.getPriority());
        task.setRecurrence(input.getRecurrence());
        // Handle dependencies
        return taskService.updateTask(id, task);
    }

    public boolean deleteTask(String id) {
        return taskService.deleteTask(id);
    }

    public Task markTaskCompleted(String id) {
        return taskService.markTaskCompleted(id);
    }

    public Task rescheduleTask(String id, LocalDateTime nextExecutionTime) {
        return taskService.rescheduleTask(id, nextExecutionTime);
    }
}
