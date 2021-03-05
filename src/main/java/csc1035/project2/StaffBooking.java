package csc1035.project2;

import javax.persistence.*;

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

    public StaffBooking(String bid, String sid) {
        this.bid = bid;
        this.sid = sid;
    }

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
