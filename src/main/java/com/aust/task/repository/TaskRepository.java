package com.aust.task.repository;
import java.time.LocalDate;
import java.util.List;

import com.aust.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aust.task.entity.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findByUser(User user);
    //List<Task> findByTid(Long tid);
    //List<Task> findByDueDate(LocalDate due_date);
    //List<Task> sortByPriority();
    //List<Task> findByStatus(int status);
}
