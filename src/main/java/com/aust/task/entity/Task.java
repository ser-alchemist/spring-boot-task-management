package com.aust.task.entity;

import com.aust.task.converters.TaskPriorityConverter;
import com.aust.task.converters.TaskStatusConverter;
import com.aust.task.enums.TaskPriority;
import com.aust.task.enums.TaskStatus;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long tid;

    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Convert(converter = TaskStatusConverter.class)
    private TaskStatus status;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "priority")
    @Convert(converter = TaskPriorityConverter.class)
    private TaskPriority priority;


    @ManyToOne
    @JoinColumn(name = "uid_", referencedColumnName = "uid_")
    private User user;

    public Task() {
    }

    public Task(Long tid, String description, TaskStatus status, LocalDate dueDate, TaskPriority priority, User user) {
        this.tid = tid;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
        this.user = user;
    }

    public Task(String description, TaskStatus status, LocalDate dueDate, TaskPriority priority) {

        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;

    }

    public Long getTid() {
        return tid;
    }

/*
    public void setTid(Long tid) {
        this.tid = tid;
    }
*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
