package csc1035.project2;

import javax.persistence.*;

/**
 * A persistent class made to handle the Student_Booking table, which
 * links the Students table with the Booking table. It represents the
 * bookings made by individual students.
 *
 * The table contains a booking ID and a student ID.
 *
 * This Class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Student_Booking")
public class StudentBooking {
    @Id
    @Column(name = "Booking_ID", nullable = false)
    private String bid;

    @Column(name = "Student_ID", nullable = false)
    private String sid;

    @OneToOne(mappedBy = "studentBooking")
    private Booking booking;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Students student;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param bid The booking ID.
     * @param sid The student ID.
     */
    public StudentBooking(int bid, int sid) {
        this.bid = bid;
        this.sid = sid;
    }

    /**
     * The default constructor for Hibernate.
     */
    public StudentBooking() {
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
