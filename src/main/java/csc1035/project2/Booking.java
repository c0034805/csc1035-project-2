package csc1035.project2;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Booking_ID", nullable = false)
    private String id;

    @Column(name = "Start", nullable = false)
    private Timestamp start;

    @Column(name = "End", nullable = false)
    private Timestamp end;

    @ManyToOne
    @JoinColumn(nullable = false, name = "Room_Number")
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
     * @param start The beginning of the booking.
     * @param end The end of the booking.
     */
    public Booking(Timestamp start, Timestamp end) {
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
                this.start == b.getStart() &&
                this.end == b.getEnd();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setRoom(Room room) {
        this.room = room;
    }

    public StaffBooking getStaffBooking() {
        return staffBooking;
    }

    public StudentBooking getStudentBooking() {
        return studentBooking;
    }

    public ModuleBooking getModuleBooking() {
        return moduleBooking;
    }

    public void setStaffBooking(StaffBooking staffBooking) {
        this.staffBooking = staffBooking;
    }

    public void setStudentBooking(StudentBooking studentBooking) {
        this.studentBooking = studentBooking;
    }

    public void setModuleBooking(ModuleBooking moduleBooking) {
        this.moduleBooking = moduleBooking;
    }
}
