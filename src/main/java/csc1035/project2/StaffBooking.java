package csc1035.project2;

import javax.persistence.*;
import java.util.Objects;

/**
 * A persistent class made to handle the Staff_Booking table, which
 * links the Staff table with the Booking table. It represents the
 * bookings made by individual staff members.
 *
 * The table contains a booking ID and a Staff ID.
 *
 * This Class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Staff_Booking")
public class StaffBooking {

    @Id
    private String bid;

    @OneToOne
    @JoinColumn(name = "Booking_ID")
    @MapsId
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "Staff_ID")
    private Staff staff;

    public StaffBooking( Booking booking, Staff staff ) {
        this.booking = booking;
        this.staff = staff;
    }

    /**
     * The default constructor for Hibernate.
     */
    public StaffBooking() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffBooking that = (StaffBooking) o;
        return Objects.equals(bid, that.bid) &&
                Objects.equals(booking, that.booking) &&
                Objects.equals(staff, that.staff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, booking, staff);
    }

    public Booking getBooking() {
        return booking;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
