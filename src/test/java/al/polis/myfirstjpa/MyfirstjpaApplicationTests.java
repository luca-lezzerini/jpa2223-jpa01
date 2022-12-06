package al.polis.myfirstjpa;

import al.polis.myfirstjpa.model.Course;
import al.polis.myfirstjpa.model.Student;
import al.polis.myfirstjpa.repos.CourseRepository;
import al.polis.myfirstjpa.repos.StudentRepository;
import al.polis.myfirstjpa.service.StudentService;
import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@Log4j2
class MyfirstjpaApplicationTests {

    // Our scope is testing service
    @Autowired
    StudentService studentService;

    // and we will use repository for assertions (i.e. checking everything is fine)
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    
    @BeforeAll
    public void setUpTestEnvironment() {
        createTestDataSet();
    }
    
    @AfterAll
    public void tearDownClass() {
    }
    
    @Test
    @Order(1)
    void testQueryByMethodName() {
        log.debug("Started firstTestMethod");
        try {
            testStudentQueries();
            
        } catch (Exception e) {
            log.debug("Exception met in testing", e);
        }
        log.debug("Ended firstTestMethod");
    }
    
    private void testStudentQueries() {
        var foundStudents = studentRepository.findByLastName("Hoxha");
        Assertions.assertTrue(foundStudents.size() == 1, "The size of the resulting set is wrong (!= 1)");
        Assertions.assertTrue(foundStudents.get(0).getLastName().equals("Hoxha"), "The last name of the found student is wrong");
        foundStudents = studentRepository.lookForLastName2("Tano");
        Assertions.assertTrue(foundStudents.size() == 1, "The size of the resulting set is wrong (!= 1)");
        Assertions.assertTrue(foundStudents.get(0).getLastName().equals("Tano"), "The last name of the found student is wrong");
        foundStudents = studentRepository.findByLastNameLike("%o%");
        Assertions.assertTrue(foundStudents.size() == 2, "The size of the resulting set is wrong (!= 2)");
        foundStudents = studentRepository.lookForSimilarLastName2("%i%");
        Assertions.assertTrue(foundStudents.size() == 2, "The size of the resulting set is wrong (!= 1)");
    }
    
    private void createTestDataSet() {
        // action to be tested
        studentRepository.deleteAllInBatch();
        courseRepository.deleteAllInBatch();

        // check the result
        var remainedInDb = studentRepository.count();
        Assertions.assertTrue(remainedInDb == 0);

        // create a test data set
        var students = new ArrayList<Student>();
        students.add(new Student("Ledio", "Hoxha"));
        students.add(new Student("Ina", "Tano"));
        students.add(new Student("Oresti", "Elezi"));
        students.add(new Student("Jonada", "Halidini"));

        // test the save service
        for (Student student : students) {
            var s = studentService.saveStudent(student);
            Optional<Student> testStudent = studentRepository.findById(s.getId());
            Assertions.assertFalse(testStudent.isEmpty());
        }

        // create the courses
        Course jap = courseRepository.save(new Course("JAP", "Java Advanced Programming", "Use Java as better as possible"));
        Course pyt = courseRepository.save(new Course("PYT", "Python Programming", "Use Python everywhere"));

        // associate courses and students
        var oStudents = studentRepository.findByLastNameLike("%o%");
        for (Student oStudent : oStudents) {
            oStudent.setCourse(jap);
            studentRepository.save(oStudent);
//            jap.getStudents().add(oStudent);
//            courseRepository.save(jap);
        }
        var iStudents = studentRepository.findByLastNameLike("%i%");
        for (Student iStudent : iStudents) {
            iStudent.setCourse(pyt);
            studentRepository.save(iStudent);
        }

        // look for students for JAP
        var japStudents = studentRepository.getStudentsListForCourse(jap.getId());
        System.out.println("JAP Students are in number of " + japStudents.size());
        for (Student japStudent : japStudents) {
            System.out.println("Found JAP student " + japStudent.getFirstName());
        }
        
        // llok for a course for a student
        var oneJapStudent = japStudents.get(0);
        Course cx = courseRepository.readCourseForStudent(oneJapStudent.getId());
        System.out.println("The course is " + cx);
    }
    
}
