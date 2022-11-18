package al.polis.myfirstjpa.repos;

import al.polis.myfirstjpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository 
        extends JpaRepository<Student, Long>{

}
