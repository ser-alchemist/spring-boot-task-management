package com.aust.task.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

//
    private List<Student> studentList = new ArrayList<>(Arrays.asList(
            new Student(
                    1L, "Maria", "maria.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5), 21),
            new Student(
                    2L, "Marzia", "marzia.jamal@gmail.com",
                    LocalDate.of(2001, Month.JANUARY, 5), 20)
    ));


    public List<Student> getStudent() {
        return studentList;
    }


    public void addStudent(Student student)
    {
        studentList.add(student);
        return;
    }

    public Student updateStudentby(Long id, Student s) {

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                student.setName(s.getName());
                student.setEmail(s.getEmail());
                student.setDob(s.getDob());
                student.setAge(s.getAge());
                return student;
            }
        }
        return null;
    }




    public Student getStudentbyId(Long id) {

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> deleteStudentbyId(Long id) {

        studentList.removeIf(student -> student.getId().equals(id));
//        Iterator<Student> iterator = studentList.iterator();
//        while (iterator.hasNext()) {
//            Student student = iterator.next();
//            if (student.getId().equals(id)) {
//                iterator.remove();
//            }
//        }

        return  studentList;

    }


}
