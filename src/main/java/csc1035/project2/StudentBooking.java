package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Student_Booking")
public class StudentBooking {
    @Id
    @Column(name = "Booking_ID", nullable = false)
    private int bid;

    @Column(name = "Student_ID", nullable = false)
    private int sid;

    public StudentBooking(int bid, int sid) {
        this.bid = bid;
        this.sid = sid;
    }

    public StudentBooking() {
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
