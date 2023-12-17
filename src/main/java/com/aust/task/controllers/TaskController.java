package com.aust.task.controllers;

import com.aust.task.entity.Task;
import com.aust.task.entity.User;
import com.aust.task.exception.ResourceNotFoundException;
import com.aust.task.repository.TaskRepository;
import com.aust.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/v1/")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

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


    //for first part only
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id){
        Optional<Task> taskData = taskRepository.findById(id);

        if(taskData.isPresent()){
            return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        System.out.println(task.toString());
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId,
                                                   @RequestBody Task taskDetails) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setUser(taskDetails.getUser());
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

    @GetMapping("/tasks/u/{id}")
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
