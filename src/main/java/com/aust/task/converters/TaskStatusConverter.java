package com.aust.task.converters;
import com.aust.task.enums.TaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TaskStatus status) {
        return status.getCode();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer code) {
        return TaskStatus.fromCode(code);
    }
}
