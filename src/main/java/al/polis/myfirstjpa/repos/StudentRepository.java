package al.polis.myfirstjpa.repos;

import al.polis.myfirstjpa.model.Course;
import al.polis.myfirstjpa.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
    // QUERY BY METHOD NAME

    // find all students that have a name equals something
    List<Student> findByLastName(String surname);

    // find all students that have a name like something
    List<Student> findByLastNameLike(String surname);

    // JPQL
    @Query("select s from Student s where lastName = :surname")
    List<Student> lookForLastName2(String surname);

    @Query("select s from Student s where lastName like :surname")
    List<Student> lookForSimilarLastName2(String surname);

    @Query("select s from Student s where"
            + " s.course.id = :courseId")
    List<Student> getStudentsListForCourse(Long courseId);
}
