package com.aust.task.enums;

public enum TaskStatus {
    ACTIVE(0, "ACTIVE"),
    INACTIVE(1, "INACTIVE"),
    COMPLETED(2, "COMPLETED"),
    CANCELLED(3, "CANCELLED");

    private final int code;
    private final String label;

    TaskStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static TaskStatus fromCode(int code) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }

}
