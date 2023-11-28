package com.aust.task.entity;

import java.time.LocalDate;

public class Task {

    private Long tid;
    private Long uid;
    private String description;
    private int status;
    private LocalDate dueDate;
    private int priority;

    public Task() {
    }

    public Task(Long tid, Long uid, String description, int status, LocalDate dueDate, int priority) {
        this.tid = tid;
        this.uid = uid;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
