package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Staff_Booking")
public class StaffBooking {
    @Id
    @Column(name = "Booking_ID", nullable = false)
    private int bid;

    @Column(name = "Staff_ID",nullable = false)
    private String sid;

    public StaffBooking(int bid, String sid) {
        this.bid = bid;
        this.sid = sid;
    }

    public StaffBooking() {
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
