package csc1035.project2;

import javax.persistence.*;
import java.util.List;

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

    public Students(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

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
