package com.aust.task.converters;

import com.aust.task.enums.TaskPriority;
import com.aust.task.enums.TaskStatus;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TaskPriorityConverter implements AttributeConverter<TaskPriority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TaskPriority priority) {
        return priority.getCode();
    }

    @Override
    public TaskPriority convertToEntityAttribute(Integer code) {
        return TaskPriority.fromCode(code);
    }
}
