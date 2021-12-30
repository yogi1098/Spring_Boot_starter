package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private  final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
       return studentService.getStudent();
    }
    @PostMapping
    public void addStudent(@RequestBody Student student){
         studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "{studentId}")
    public void updateStudentData(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false)String email){
        studentService.updateStudentData(studentId,name,email);
    }

}
