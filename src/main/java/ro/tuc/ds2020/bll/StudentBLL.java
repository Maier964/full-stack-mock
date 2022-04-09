package ro.tuc.ds2020.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ro.tuc.ds2020.bll.validators.EmailValidator;
import ro.tuc.ds2020.bll.validators.StudentAgeValidator;
import ro.tuc.ds2020.bll.validators.Validator;
import ro.tuc.ds2020.dao.StudentDAO;
import ro.tuc.ds2020.model.Student;


public class StudentBLL {

    private List<Validator<Student>> validators;
    private StudentDAO studentDAO;

    public StudentBLL() {
        validators = new ArrayList<Validator<Student>>();
        validators.add(new EmailValidator());
        validators.add(new StudentAgeValidator());

        studentDAO = new StudentDAO();
    }

    public Student findStudentById(int id) {
        Student st = studentDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

}
