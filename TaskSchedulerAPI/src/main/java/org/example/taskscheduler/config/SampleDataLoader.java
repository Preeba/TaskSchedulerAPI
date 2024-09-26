package org.example.taskscheduler.config;

import jakarta.annotation.PostConstruct;
import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.model.TaskStatus;
import org.example.taskscheduler.repository.TaskRepository_old;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SampleDataLoader {
/*

    private TaskRepository taskRepo;

    public SampleDataLoader(TaskRepository taskRep) {
        this.taskRepo = taskRep;
    }

    @PostConstruct
    public void loadSampleData() {
        System.out.println("Loading sample data...");  // This should appear in the logs
        Task task1 = new Task();

        task1.setName("Task 1");
        task1.setDescription("Sample Task 1");
        task1.setPriority(1);
        task1.setStatus(TaskStatus.PENDING);
        task1.setNextExecutionTime(LocalDateTime.now().plusDays(1));

        Task task2 = new Task();
        task2.setName("Task 2");
        task2.setDescription("Sample Task 2");
        task2.setPriority(2);
        task2.setStatus(TaskStatus.IN_PROGRESS);
        task2.setNextExecutionTime(LocalDateTime.now().plusDays(1));

        Task task3 = new Task();
        task3.setName("Task 3");
        task3.setDescription("Sample Task 3");
        task3.setPriority(3);
        task3.setStatus(TaskStatus.PENDING);
        task3.setNextExecutionTime(LocalDateTime.now().plusDays(1));

        taskRepo.save(task1);
        taskRepo.save(task2);
        taskRepo.save(task3);
        System.out.println("Sample data loaded!");
    }
*/

}
