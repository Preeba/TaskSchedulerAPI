package org.example.taskscheduler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private int priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private LocalDateTime nextExecutionTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecurrenceSchedule recurrence;

    @ManyToMany
    @JoinTable(name="Task_Dependencies", joinColumns = @JoinColumn(name="task_id"), inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    private Set<Task> dependencies = new HashSet<Task>();

    @ManyToMany(mappedBy = "dependencies")
    private Set<Task> depender = new HashSet<>();
}
