package com.aust.task.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.aust.task.entity.User;
import com.aust.task.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aust.task.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    Page<Task> findByUser(Pageable pageable, User user);
    Page<Task> findByUserAndStatus(Pageable pageable, User user, TaskStatus status);
    Page<Task> findByUserAndDueDateGreaterThanEqual(Pageable pageable, User user, LocalDate date);
    Page<Task> findByUserAndStatusAndDueDateGreaterThanEqual(Pageable pageable, User user, TaskStatus status, LocalDate date);

    Optional<Task> findByUserAndTid(User user, Long tid);


    Page<Task> findByStatus(Pageable pageable, TaskStatus status);
    Page<Task> findByDueDateGreaterThanEqual(Pageable pageable, LocalDate date);
    Page<Task> findByStatusAndDueDateGreaterThanEqual(Pageable pageable, TaskStatus status, LocalDate date);




    //List<Task> findByTid(Long tid);
    //List<Task> findByDueDate(LocalDate due_date);
    //List<Task> sortByPriority();
    //List<Task> findByStatus(int status);
}
