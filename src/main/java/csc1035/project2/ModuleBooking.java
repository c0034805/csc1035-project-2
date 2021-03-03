package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Module_Booking")
public class ModuleBooking {
    @Id
    @Column(name = "Booking_ID", nullable = false)
    private int bid;

    @Column(name = "Module_ID", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Modules module;

    @OneToOne(mappedBy = "moduleBooking")
    private Booking booking;

    public ModuleBooking(String id, int bid) {
        this.id = id;
        this.bid = bid;
    }

    public ModuleBooking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
}
