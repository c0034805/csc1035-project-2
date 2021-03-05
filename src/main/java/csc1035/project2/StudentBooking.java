package csc1035.project2;

import javax.persistence.*;

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

    public StudentBooking(String bid, String sid) {
        this.bid = bid;
        this.sid = sid;
    }

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
