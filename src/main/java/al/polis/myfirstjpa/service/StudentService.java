package al.polis.myfirstjpa.service;

import al.polis.myfirstjpa.exception.NotFoundException;
import al.polis.myfirstjpa.model.Student;
import java.util.List;

public interface StudentService {

    Student saveStudent(Student s);

    void removeStudent(Student s);

    void removeAll();
    
    Student findById(Long id) throws NotFoundException;
    
    List<Student> getAllStudents();
}
