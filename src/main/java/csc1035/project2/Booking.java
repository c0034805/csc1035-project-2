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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Booking_ID", nullable = false)
    private String id;

    @Column(name = "Room_Number", nullable = false)
    private String num;

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
     * @param num The room number.
     * @param start The beginning of the booking.
     * @param end The end of the booking.
     */
    public Booking(String num, Timestamp start, Timestamp end) {
        this.num = num;
        this.start = start;
        this.end = end;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Booking() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != Booking.class) return false;
        Booking b = (Booking) o;
        return this.id.equals(b.getId()) &&
                this.num.equals(b.getNum()) &&
                this.start == b.getStart() &&
                this.end == b.getEnd();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
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

    public Room getRoom() {
        return room;
    }

    public void setMenu(Room room) {
        this.room = room;
    }
}
