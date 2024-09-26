package org.example.taskscheduler.repository;

import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom query methods
    List<Task> findByStatus(TaskStatus status);
}
