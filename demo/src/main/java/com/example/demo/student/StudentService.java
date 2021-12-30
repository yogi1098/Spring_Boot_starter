package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
//                List.of(
//                new Student(1L,"Yogendra Singh",20, LocalDate.of(2000,01,01),"ysshekhawat1098@gamil.com")
//        );
    }

    public void addNewStudent(Student student){
        Optional<Student> studnetOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studnetOptional.isPresent()){
            throw new IllegalStateException("Email Already present");
        }
         studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean isIdPresent = studentRepository.existsById(studentId);
        if (isIdPresent){
            studentRepository.deleteById(studentId);
        }else {
            throw new IllegalStateException("Given StudentId "+studentId+" is not present in Student DataBase");
        }
    }
    @Transactional
    public void updateStudentData(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student Id "+studentId+" is not present you trying update"));
        if (student!=null){
            if (name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
                student.setName(name);
            }
            if (email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
                Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
                if (optionalStudent.isPresent()){
                    throw new IllegalStateException("Email already present can't update this email");
                }
                student.setEmail(email);
            }
        }
    }
}
