package csc1035.project2;

import javax.persistence.*;
import java.util.List;

/**
 * A persistent class made to handle the Staff table.
 *
 * The table contains the staff ID, as well as the first and last name
 * of that staff member.
 *
 * This class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 */
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

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The staff ID.
     * @param firstname The staff member's first name.
     * @param lastname The staff member's last name.
     */
    public Staff(String id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * The default constructor for Hibernate.
     */
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