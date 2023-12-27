package com.aust.task.controllers;

import static java.util.stream.Collectors.toList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.aust.task.payload.response.TaskResponse;
import com.aust.task.entity.Task;
import com.aust.task.entity.User;
import com.aust.task.enums.TaskPriority;
import com.aust.task.enums.TaskStatus;
import com.aust.task.exception.ResourceNotFoundException;
import com.aust.task.repository.TaskRepository;
import com.aust.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/v1/")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    /*
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }*/
    /* without pagination

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        try{
            List<Task> tasks = new ArrayList<Task>();
            tasks.addAll(taskRepository.findAll());
            if (tasks.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

/*    @GetMapping("/tasks")
    public Page<TaskResponse> list(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdOn").descending());
        Page<Task> pageResult = taskRepository.findAll(pageRequest);
        List<TaskResponse> tasks = pageResult
                .stream()
                .map(TaskResponse::new)
                .collect(toList());
        //System.out.println("API HIT!");
        return new PageImpl<>(tasks, pageRequest, pageResult.getTotalElements());
    }*/

    @GetMapping("/tasks/{filter}/sort/{sortBy}/{type}")
    public Page<TaskResponse> tasksWithConstraints(@PathVariable("filter") String filter,
                                                   @PathVariable("sortBy") String sortBy,
                                                   @PathVariable("type") String type,
                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                                   Principal principal) {
        //System.out.println("here");
        PageRequest pageRequest;

        //principal getName == username

        //System.out.println("Principal: " + principal.getName());
        User user = userRepository.findByUname(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + principal.getName()));

        if(sortBy.equals("date")){
            if(type.equals("asc")){
                pageRequest = PageRequest.of(page, size, Sort.by("dueDate").ascending());
            }
            else if(type.equals("desc")){
                pageRequest = PageRequest.of(page, size, Sort.by("dueDate").descending());
            }
            else {
                pageRequest = PageRequest.of(page, size);
            }
        }
        else if(sortBy.equals("priority")){
            if(type.equals("asc")){
                pageRequest = PageRequest.of(page, size, Sort.by("priority").ascending().and(Sort.by("createdOn").descending()));
            }
            else if(type.equals("desc")){
                pageRequest = PageRequest.of(page, size, Sort.by("priority").descending().and(Sort.by("createdOn").descending()));
            }
            else {
                pageRequest = PageRequest.of(page, size);
            }
        }
        else if(sortBy.equals("default")){
            if(type.equals("asc")){
                pageRequest = PageRequest.of(page, size, Sort.by("createdOn").ascending());
            }
            else if(type.equals("desc")){
                pageRequest = PageRequest.of(page, size, Sort.by("createdOn").descending());
            }
            else {
                pageRequest = PageRequest.of(page, size);
            }
        }
        else {
            pageRequest = PageRequest.of(page, size);
        }

        Page<Task> pageResult;
        switch (filter){
            case "active":
                //System.out.println("HIT!");
                pageResult = taskRepository.findByUserAndStatus(pageRequest, user, TaskStatus.ACTIVE);
                break;
            case "valid":
                //System.out.println("HIT!");
                pageResult = taskRepository.findByUserAndDueDateGreaterThanEqual(pageRequest, user, LocalDate.now());
                break;
            case "active&valid":
                //System.out.println("HIT!");
                pageResult = taskRepository.findByUserAndStatusAndDueDateGreaterThanEqual(pageRequest, user, TaskStatus.ACTIVE, LocalDate.now());
                break;
            default:
                //pageResult = taskRepository.findAll(pageRequest);
                pageResult = taskRepository.findByUser(pageRequest, user);
                break;
        }

        List<TaskResponse> tasks = pageResult
                .stream()
                .map(TaskResponse::new)
                .collect(toList());
        return new PageImpl<>(tasks, pageRequest, pageResult.getTotalElements());
    }

    //for first part only
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id,
                                            Principal principal){

        User user = userRepository.findByUname(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + principal.getName()));
        Optional<Task> taskData = taskRepository.findByUserAndTid(user, id);

        if(taskData.isPresent()){
            return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task,
                           Principal principal) {
        //System.out.println(task.toString());
        User user = userRepository.findByUname(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + principal.getName()));

        task.setCreatedOn(LocalDateTime.now(ZoneId.of("GMT+06:00")));
        task.setUser(user);
        return taskRepository.save(task);
    }

    @PostMapping("/tasks/bulk")
    public ResponseEntity<Void> bulkCreate() {
        System.out.println("API HIT! BULK Create request");
        long minDay = LocalDate.of(2019, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 12, 31).toEpochDay();

        for (int i = 1; i <= 100; i++) {
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            taskRepository.save(new Task("Task "+i,
                                    TaskStatus.values()[ThreadLocalRandom.current().nextInt(TaskStatus.values().length)],
                                    LocalDate.ofEpochDay(randomDay),
                                    TaskPriority.values()[ThreadLocalRandom.current().nextInt(TaskPriority.values().length)],
                                    LocalDateTime.now(ZoneId.of("GMT+06:00")) ));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/tasks/bulk/u/{uid}")
    public ResponseEntity<Void> bulkCreateForUser(@PathVariable Long uid) {
        User user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("API HIT! BULK Create request");
        long minDay = LocalDate.of(2019, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 12, 31).toEpochDay();

        for (int i = 1; i <= 20; i++) {
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            taskRepository.save(new Task("Task "+i+" u: "+user.getUid(),
                    TaskStatus.values()[ThreadLocalRandom.current().nextInt(TaskStatus.values().length)],
                    LocalDate.ofEpochDay(randomDay),
                    TaskPriority.values()[ThreadLocalRandom.current().nextInt(TaskPriority.values().length)],
                    LocalDateTime.now(ZoneId.of("GMT+06:00")),
                    user));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable(value = "id") Long taskId,
                                           @RequestBody Task taskDetails,
                                            Principal principal) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
        User user = userRepository.findByUname(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + principal.getName()));

        Optional<Task> task1 = taskRepository.findByUserAndTid(user, taskId);

        if(task1.isPresent()){
            task.setDescription(taskDetails.getDescription());
            task.setDueDate(taskDetails.getDueDate());
            task.setStatus(taskDetails.getStatus());
            task.setPriority(taskDetails.getPriority());
            task.setUser(user);
            final Task updatedTask = taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @PutMapping("/tasks/makeComplete/{id}")
    public ResponseEntity<Task> updateTaskComplete(@PathVariable(value = "id") Long taskId,
                                                   @RequestBody Task taskDetails) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        task.setStatus(TaskStatus.COMPLETED);
        final Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }




    //////////////////////////////////////////////////////////////

   /* @GetMapping("/tasks/u/{id}")
    public ResponseEntity<List<Task>> getTaskByUserId(@PathVariable("id") long id){

        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()){
            User user = userData.get();
            System.out.println(user.getUname());
            try{
                List<Task> tasks = new ArrayList<Task>();
                tasks.addAll(taskRepository.findByUser(user));
                if (tasks.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(tasks, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
*/
    @PostMapping("/tasks/u/{id}")
    public ResponseEntity<Task> createTask(@PathVariable Long id, @RequestBody Task task){
        try{
            //System.out.println(task.toString());
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            task.setUser(user);
            taskRepository.save(task);
            //Task _task = userRepository.save(new Task(task. );
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
