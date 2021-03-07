package csc1035.project2;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * A persistent class made to handle the Booking table.
 *
 * The table contains a booking ID, the room number of the room in
 * which the booking is made, as well as when the booking starts and ends.
 *
 * This Class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @Column(name = "Booking_ID", nullable = false)
    private int id;

    @Column(name = "Room_Number", nullable = false)
    private int num;

    @Column(name = "Start", nullable = false)
    private Timestamp start;

    @Column(name = "End", nullable = false)
    private Timestamp end;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Room room;

    @OneToOne
    @JoinColumn(name = "Booking_ID", referencedColumnName = "Booking_ID")
    private StaffBooking staffBooking;

    @OneToOne
    @JoinColumn(name = "Booking_ID", referencedColumnName = "Booking_ID")
    private StudentBooking studentBooking;

    @OneToOne
    @JoinColumn(name = "Booking_ID", referencedColumnName = "Booking_ID")
    private ModuleBooking moduleBooking;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The identification number of the booking.
     * @param num The room number.
     * @param start The beginning of the booking.
     * @param end The end of the booking.
     */
    public Booking(int id, int num, Timestamp start, Timestamp end) {
        this.id = id;
        this.num = num;
        this.start = start;
        this.end = end;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
