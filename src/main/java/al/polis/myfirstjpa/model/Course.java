package al.polis.myfirstjpa.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"students"})
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String description;
    
    @OneToMany(mappedBy = "course")
    List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
    
    
}
