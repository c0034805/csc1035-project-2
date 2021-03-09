package csc1035.project2;

import javax.persistence.*;

/**
 * A persistent class made to handle the Module_Booking table, which
 * links the Modules table with the Booking table. It represents the
 * bookings made for the different modules.
 *
 * The table contains a booking ID and a module ID.
 *
 * This Class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Module_Booking")
public class ModuleBooking {
    @Id
    @Column(name = "Booking_ID", nullable = false)
    private String bid;

    @Column(name = "Module_ID", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Modules module;

    @OneToOne(mappedBy = "moduleBooking")
    private Booking booking;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The module ID.
     * @param bid The Booking ID.
     */
    public ModuleBooking(String id, String bid) {
        this.id = id;
        this.bid = bid;
    }

    /**
     * The default constructor for Hibernate.
     */
    public ModuleBooking() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != ModuleBooking.class) return false;
        ModuleBooking b = (ModuleBooking) o;
        return this.id.equals(b.getId()) && this.bid.equals(b.getBid());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
