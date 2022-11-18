package al.polis.myfirstjpa.service;

import al.polis.myfirstjpa.model.Student;

public interface StudentService {

    Student saveStudent(Student s);

    void removeStudent(Student s);

    void removeAll();
}
