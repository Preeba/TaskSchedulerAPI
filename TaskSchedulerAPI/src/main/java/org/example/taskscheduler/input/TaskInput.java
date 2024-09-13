package org.example.taskscheduler.input;

import lombok.Data;
import org.example.taskscheduler.model.RecurrenceSchedule;

@Data
public class TaskInput {
    private String name;
    private String description;
    private Integer priority;
    private RecurrenceSchedule recurrence;
}
