package com.aust.task.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public List<Student> getStudent() {
        return studentService.getStudent();
    }

    @RequestMapping(path = "/show/{id}", method = RequestMethod.GET)
    public Student getStudentbyId(@PathVariable("id") Long id) {
        return studentService.getStudentbyId(id);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public List<Student> createStudent(@RequestBody Student student){

        studentService.addStudent(student);
        return studentService.getStudent();
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public List<Student> upStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        studentService.updateStudentby(id, student);
        return studentService.getStudent();
    }

    @RequestMapping(path = "/del/{id}", method = RequestMethod.DELETE)
    public List<Student> delStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentbyId(id);
        return studentService.getStudent();
    }

}
