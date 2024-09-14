package org.example.taskscheduler.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.taskscheduler.model.RecurrenceSchedule;

import java.util.Set;

@Data
@AllArgsConstructor
public class TaskInput {
    private String name;
    private String description;
    private Integer priority;
    private RecurrenceSchedule recurrence;
    private Set<String> dependencyIds;
}
