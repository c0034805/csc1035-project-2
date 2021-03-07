package csc1035.project2;

import javax.persistence.*;
import java.util.List;

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
    private int id;

    @Column(name = "First_Name", nullable = false)
    private String firstname;

    @Column(name = "Last_Name", nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "student")
    private List<StudentBooking> studentBookings;

    @OneToMany(mappedBy = "student")
    private List<Take> take;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The student ID.
     * @param firstname The student's first name.
     * @param lastname The student's ast name.
     */
    public Students(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Students() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
