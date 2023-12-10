package com.aust.task.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aust.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUid(Long uid);
    List<Task> findByTid(Long tid);
    List<Task> findByDueDate(LocalDate due_date);
    List<Task> sortByPriority();
    List<Task> findByStatus(int status);


}
