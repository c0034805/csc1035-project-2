package csc1035.project2;

import javax.persistence.*;

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
    @Column(name = "Booking_ID", nullable = false)
    private String bid;

    @Column(name = "Staff_ID",nullable = false)
    private String sid;

    @OneToOne(mappedBy = "staffBooking")
    private Booking booking;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Staff staff;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     * @param bid The booking ID.
     * @param sid The staff member's ID.
     */
    public StaffBooking(int bid, String sid) {
        this.bid = bid;
        this.sid = sid;
    }

    /**
     * The default constructor for Hibernate.
     */
    public StaffBooking() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
