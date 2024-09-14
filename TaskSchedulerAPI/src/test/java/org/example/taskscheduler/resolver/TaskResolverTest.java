package org.example.taskscheduler.resolver;

import org.example.taskscheduler.input.TaskInput;
import org.example.taskscheduler.model.RecurrenceSchedule;
import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.model.TaskStatus;
import org.example.taskscheduler.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskResolverTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskResolver resolver;


    @Test
    void testGetTask() {
        Task mockTask = new Task("1", "Test Task", "Test description", 1, TaskStatus.PENDING, null, RecurrenceSchedule.DAILY, null, null);
        when(taskService.getTaskById("1")).thenReturn(mockTask);
        Task result = resolver.task("1");

        assertNotNull(result);
        assertEquals("Test Task", result.getName());
        assertEquals(TaskStatus.PENDING, result.getStatus());
    }

    @Test
    void testGetTasks() {
        List<Task> mockTasks = List.of(
                new Task("1", "Test Task 1", "Test description 1", 1, TaskStatus.PENDING, null, RecurrenceSchedule.DAILY, null, null),
                new Task("2", "Test Task 2", "Test description 2", 2, TaskStatus.IN_PROGRESS, null, RecurrenceSchedule.WEEKLY, null, null)
        );

        when(taskService.getTasks(TaskStatus.PENDING)).thenReturn(mockTasks);

        List<Task> result = resolver.tasks(TaskStatus.PENDING);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testCreateTask() {
        TaskInput input = new TaskInput("New Task", "New description", 3, RecurrenceSchedule.MONTHLY, Set.of("1", "2"));
        Task createdTask = new Task("3", "New Task", "New description", 3, TaskStatus.PENDING, null, RecurrenceSchedule.MONTHLY, null, null);

        when(taskService.createTask(any(TaskInput.class))).thenReturn(createdTask);

        Task result = resolver.createTask(input);

        assertNotNull(result);
        assertEquals("New Task", result.getName());
        assertEquals(TaskStatus.PENDING, result.getStatus());
    }


}