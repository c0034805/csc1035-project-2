package csc1035.project2;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Booking(int num, Timestamp start, Timestamp end) {
        this.num = num;
        this.start = start;
        this.end = end;
    }

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
