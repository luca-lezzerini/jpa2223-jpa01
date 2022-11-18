package al.polis.myfirstjpa.controller;

import al.polis.myfirstjpa.model.Student;
import al.polis.myfirstjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampelController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/saveTest")
    public void saveTest() {
        studentService.removeAll();
        Student s;
        s = new Student("Ledio", "Hoxha");
        s = studentService.saveStudent(s);
        s = new Student("Ismail", "Qemali");
        s = studentService.saveStudent(s);
        
    }
}
