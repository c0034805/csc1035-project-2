package csc1035.project2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A persistent class made to handle the Students table.
 *
 * The table contains the student ID, as well as the first and last name
 * of that student.
 *
 * This class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Students")
public class Students {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "First_Name", nullable = false)
    private String firstname;

    @Column(name = "Last_Name", nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "student")
    private List<StudentBooking> studentBookings;

    @ManyToMany
    @JoinTable(
            name = "Take",
            joinColumns = {@JoinColumn(name = "Student_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Module_ID")}
    )
    private Set<Modules> modules = new HashSet<>();

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The student ID.
     * @param firstname The student's first name.
     * @param lastname The student's ast name.
     */
    public Students(String id, String firstname, String lastname,
                    List<StudentBooking> studentBookings) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentBookings = studentBookings;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Students() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != Staff.class) return false;
        Students s = (Students) o;
        return this.id.equals(s.getId()) && this.firstname.equals(s.getFirstname()) && this.lastname.equals(s.getLastname());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<StudentBooking> getStudentBookings() {
        return studentBookings;
    }

    public void setStudentBookings(List<StudentBooking> studentBookings) {
        this.studentBookings = studentBookings;
    }

    public Set<Modules> getModules() {
        return modules;
    }

    public void setModules(Set<Modules> modules) {
        this.modules = modules;
    }
}
