package csc1035.project2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A persistent class made to handle the Staff table.
 *
 * The table contains the staff ID, as well as the first and last name
 * of that staff member.
 *
 * This class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
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

    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private Set<StaffBooking> staffBookings = new HashSet<>();

    @OneToMany( mappedBy = "staff" , fetch = FetchType.EAGER)
    private Set<Teach> teaches = new HashSet<>();

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The staff ID.
     * @param firstname The staff member's first name.
     * @param lastname The staff member's last name.
     */
    public Staff(String id, String firstname, String lastname,
                 Set<StaffBooking> staffBookings) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.staffBookings = staffBookings;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Staff() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != Staff.class) return false;
        Staff s = (Staff) o;
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

    public Set<StaffBooking> getStaffBookings() {
        return staffBookings;
    }

    public void setStaffBookings(Set<StaffBooking> staffBookings) {
        this.staffBookings = staffBookings;
    }

    public void setTeaches(Set<Teach> teaches) {
        this.teaches = teaches;
    }

    public Set<Teach> getTeaches() {
        return teaches;
    }

    @Override
    public String toString() {
        return "Staff{\n" +
                "ID: "+ id + "\n" +
                "First name: " + firstname + "\n" +
                "Last name: " + lastname + "\n" +
                "}";
    }
}