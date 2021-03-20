package csc1035.project2;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Objects;

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
    private String bid;

    @OneToOne
    @JoinColumn(name = "Booking_ID")
    @MapsId
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "Student_ID")
    private Students student;

    public StudentBooking(Booking booking, Students student) {
        this.booking = booking;
        this.student = student;
    }

    /**
     * The default constructor for Hibernate.
     */
    public StudentBooking() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBooking that = (StudentBooking) o;
        return Objects.equals(bid, that.bid) &&
                Objects.equals(booking, that.booking) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, booking, student);
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
