-- Create the TaskStatus enum
CREATE TABLE TaskStatus (
    status VARCHAR(50) PRIMARY KEY
);

-- Insert values for TaskStatus
INSERT INTO TaskStatus (status) VALUES ('PENDING'), ('IN_PROGRESS'), ('COMPLETED'), ('FAILED');

-- Create the RecurrenceSchedule enum
CREATE TABLE RecurrenceSchedule (
    schedule VARCHAR(50) PRIMARY KEY
);

-- Insert values for RecurrenceSchedule
INSERT INTO RecurrenceSchedule (schedule) VALUES ('DAILY'), ('WEEKLY'), ('MONTHLY'), ('CUSTOM');

-- Create the Task table
CREATE TABLE Task (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    priority INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    next_execution_time TIMESTAMP,
    recurrence VARCHAR(50),
    CONSTRAINT fk_status FOREIGN KEY (status) REFERENCES TaskStatus (status),
    CONSTRAINT fk_recurrence FOREIGN KEY (recurrence) REFERENCES RecurrenceSchedule (schedule)
);

-- Create the Task Dependencies table for many-to-many relationships
CREATE TABLE Task_Dependencies (
    task_id BIGINT,
    dependency_id BIGINT,
    CONSTRAINT fk_task FOREIGN KEY (task_id) REFERENCES Task (id),
    CONSTRAINT fk_dependency FOREIGN KEY (dependency_id) REFERENCES Task (id)
);

-- Insert sample tasks
INSERT INTO Task (name, description, priority, status, next_execution_time, recurrence)
VALUES ('Task 1', 'Description of Task 1', 1, 'PENDING', '2024-09-15 10:00:00', 'DAILY');

INSERT INTO Task (name, description, priority, status, next_execution_time, recurrence)
VALUES ('Task 2', 'Description of Task 2', 2, 'IN_PROGRESS', '2024-09-16 12:00:00', 'WEEKLY');

INSERT INTO Task (name, description, priority, status, next_execution_time, recurrence)
VALUES ('Task 3', 'Description of Task 3', 3, 'COMPLETED', NULL, 'MONTHLY');

-- Insert task dependencies (if Task 2 depends on Task 1)
INSERT INTO Task_Dependencies (task_id, dependency_id)
VALUES (2, 1);
