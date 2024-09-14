package org.example.taskscheduler.repository;

import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testFetchTaskById() {
        Task task = taskRepository.findFirst().orElse(null);
        assertNotNull(task, "Task should not be null");
    }

    @Test
    void findAllTasks() {
        List<Task> tasks = taskRepository.findAllTasks(Optional.of(TaskStatus.PENDING));
        assertEquals(2, tasks.size());
        tasks.forEach(System.out::println);
    }
}