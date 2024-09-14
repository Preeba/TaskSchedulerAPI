package org.example.taskscheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Task> dependencies = new HashSet<Task>();
    private Set<Task> depender = new HashSet<>();
}
