Create a task scheduler that can handle recurring tasks, priority levels, and dependencies between tasks.

A task scheduler with test cases, Java implementation, API suggestions, and data modeling:

#### Problem Statement:
Implement a task scheduler that can handle recurring tasks, priority levels, and dependencies between tasks. The scheduler should have the following features:
1. Add tasks with a name, priority (1-5, 1 being highest), and optional recurring schedule (daily, weekly, monthly).
2. Set dependencies between tasks (Task A must complete before Task B can start).
3. Execute tasks based on their priority and dependencies.
4. Handle recurring tasks by rescheduling them after execution.
5. Provide methods to list all tasks, get next task to execute, and mark a task as completed.

### Test Cases:
1. Add multiple tasks with different priorities and verify they're executed in priority order.
2. Add dependent tasks and verify they're executed in the correct order.
3. Add a recurring task and verify it's rescheduled after execution.
4. Try to add a circular dependency and verify it's rejected.
5. List all tasks and verify the output.


### APIs:
For a production-ready task scheduler, consider using existing libraries or frameworks:
1. Quartz Scheduler: A full-featured, open source job scheduling library that can be integrated into Java applications.
2. Spring Task Scheduler: Part of the Spring Framework, offering scheduling capabilities integrated with Spring's dependency injection.
3. java.util.concurrent.ScheduledExecutorService: A built-in Java interface for scheduling commands to run after a given delay or to execute periodically.


### GraphQL Schema

The schema design incorporates the following features:
1. Task type: Represents a task with properties like id, name, description, priority, status, next execution time, recurrence pattern, and dependencies.
2. TaskStatus enum: Defines possible statuses for a task (PENDING, IN_PROGRESS, COMPLETED, FAILED).
3. RecurrencePattern enum: Defines recurrence patterns for tasks (DAILY, WEEKLY, MONTHLY, CUSTOM).
4. TaskInput: Input type for creating or updating tasks, including optional dependency IDs.

#### Query type:
* task: Retrieve a specific task by ID
* tasks: List tasks, optionally filtered by status
* nextTaskToExecute: Get the next task that should be executed

#### Mutation type:
* createTask: Create a new task
* updateTask: Update an existing task
* deleteTask: Delete a task
* markTaskCompleted: Mark a task as completed
* rescheduleTask: Reschedule a task to a new execution time

## DateTime scalar: Custom scalar for handling date and time values


This schema design follows best practices for GraphQL:
* It's demand-oriented, focusing on the needs of clients that would interact with a task scheduler.
* It uses clear naming conventions (e.g., PascalCase for types, camelCase for fields).
* It provides flexibility through enums and input types.
* It abstracts implementation details, focusing on the essential properties and operations of tasks