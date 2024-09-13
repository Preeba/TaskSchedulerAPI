package org.example.taskscheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String id;
    private String name;
    private String description;
    private int priority;
    private TaskStatus status;
    private LocalDateTime nextExecutionTime;
    private RecurrenceSchedule recurrence;
    private List<Task> dependencies;
    private List<Task> depender;
}
