package org.example.taskscheduler.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.taskscheduler.model.RecurrenceSchedule;

@Data
@AllArgsConstructor
public class TaskInput {
    private String name;
    private String description;
    private Integer priority;
    private RecurrenceSchedule recurrence;
}
