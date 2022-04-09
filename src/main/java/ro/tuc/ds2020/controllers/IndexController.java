package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.StudentBLL;
import ro.tuc.ds2020.model.Student;


@RestController
@CrossOrigin
public class IndexController {


    StudentBLL studentBLL = new StudentBLL();

    @GetMapping(value = "/")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("City APP Service is running...", HttpStatus.OK);
    }


    @GetMapping("/listStudents")
    public ResponseEntity<String> test() {
        Student student = studentBLL.findStudentById(1245);
        return new ResponseEntity<>( student.toString(), HttpStatus.OK );
    }
}
