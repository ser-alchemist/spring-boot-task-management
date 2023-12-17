package com.aust.task.enums;

public enum TaskPriority {
    HIGH(0, "HIGH"),
    MEDIUM(1, "MEDIUM"),
    LOW(2, "LOW");

    private final int code;
    private final String label;

    TaskPriority(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static TaskPriority fromCode(int code) {
        for (TaskPriority priority : TaskPriority.values()) {
            if (priority.code == code) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid priority code: " + code);
    }

}
