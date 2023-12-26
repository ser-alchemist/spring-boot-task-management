package com.aust.task.dto;

import com.aust.task.entity.Task;
import com.aust.task.enums.TaskPriority;
import com.aust.task.enums.TaskStatus;

import java.time.LocalDate;

public class TaskResponse {
    private Long tid;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
    private TaskPriority priority;

    public TaskResponse(Task task){
        this.tid = task.getTid();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }

    public Long getTid() {
        return tid;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }
}
