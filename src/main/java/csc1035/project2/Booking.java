package csc1035.project2;

import javax.persistence.*;
import java.sql.Timestamp;

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

    public Booking(String num, Timestamp start, Timestamp end) {
        this.num = num;
        this.start = start;
        this.end = end;
    }

    public Booking() {
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
}
