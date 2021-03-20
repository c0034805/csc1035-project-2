package csc1035.project2;

import javax.persistence.*;
import java.util.Objects;

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
    private String bid;

    @OneToOne
    @JoinColumn(name = "Booking_ID")
    @MapsId
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "Module_ID")
    private Modules modules;

    public ModuleBooking( Booking booking, Modules modules ) {
        this.booking = booking;
        this.modules = modules;
    }

    /**
     * The default constructor for Hibernate.
     */
    public ModuleBooking() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleBooking that = (ModuleBooking) o;
        return Objects.equals(bid, that.bid) &&
                Objects.equals(booking, that.booking) &&
                Objects.equals(modules, that.modules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, booking, modules);
    }

    public Booking getBooking() {
        return booking;
    }

    public Modules getModules() {
        return modules;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
}
