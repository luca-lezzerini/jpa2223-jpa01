package al.polis.myfirstjpa.repos;

import al.polis.myfirstjpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository
        extends JpaRepository<Course, Long> {

    @Query("select s.course from Student s where"
            + " s.id = :studentId")
    Course readCourseForStudent(Long studentId);

}
