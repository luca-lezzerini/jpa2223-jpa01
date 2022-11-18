package al.polis.myfirstjpa.controller;

import al.polis.myfirstjpa.dto.StudentDto;
import al.polis.myfirstjpa.model.Student;
import al.polis.myfirstjpa.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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

        // read all students 
        List<Student> list = studentService.getAllStudents();
        // id of the 1st
        Long idToLookFor = list.get(0).getId();
        try {
            Student newStud = studentService.findById(idToLookFor);
            System.out.println("Student is " + newStud);
            newStud = studentService.findById(idToLookFor * 2);
            System.out.println("Student is " + newStud);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @PostMapping("/save")
    @ResponseBody
    public StudentDto save(@RequestBody StudentDto dto) {
        Student s = new Student();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setId(dto.getId());
        s = studentService.saveStudent(s);
        StudentDto resp = new StudentDto();
        resp.setFirstName(s.getFirstName());
        resp.setLastName(s.getLastName());
        resp.setId(s.getId());
        return resp;

    }
}
