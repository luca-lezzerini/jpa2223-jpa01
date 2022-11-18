package al.polis.myfirstjpa.service.impl;

import al.polis.myfirstjpa.model.Student;
import al.polis.myfirstjpa.repos.StudentRepository;
import al.polis.myfirstjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student s) {
        return studentRepository.save(s);
    }

    @Override
    public void removeStudent(Student s) {
        studentRepository.delete(s);
    }

    @Override
    public void removeAll() {
        studentRepository.deleteAllInBatch();
    }

}
