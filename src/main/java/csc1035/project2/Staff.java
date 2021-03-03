package csc1035.project2;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Staff")
public class Staff{
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "First_Name", nullable = false)
    private String firstname;

    @Column(name = "Last_Name", nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "staff")
    private List<StaffBooking> staffBookings;

    @OneToMany(mappedBy = "staff")
    private List<Teach> teach;

    public Staff(String id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Staff() {
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
}