package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A persistent class made to handle the Modules table.
 *
 * The table contains the module ID, the module name, the credits for
 * the module and the number of weeks the module runs for.
 *
 * This class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Modules")
public class Modules {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Credits", nullable = false)
    private int credits;

    @Column(name = "Weeks", nullable = false)
    private int weeks;

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private ModuleRequirements moduleRequirements;

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "Module_ID")
    private Take take;

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "Module_ID")
    private Teach teach;

    @OneToMany(mappedBy = "module", cascade=CascadeType.ALL)
    private List<ModuleBooking> moduleBookings;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The module ID.
     * @param name The module name.
     * @param credits Credits for the module.
     * @param weeks Number of weeks the module runs for.
     */
    public Modules(String id, String name, int credits, int weeks, List<ModuleBooking> moduleBookings) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
        this.moduleBookings = moduleBookings;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Modules() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != Modules.class) return false;
        Modules m = (Modules) o;
        return this.id.equals(m.getId()) &&
                this.name.equals(m.getName()) &&
                this.credits == m.getCredits() &&
                this.weeks == m.getWeeks();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public List<Students> getModuleStudents() {
        List<Students> s = new ArrayList<>();
        IController ic = new Controller();
        for(Take t : (List<Take>)ic.getAll(Take.class)) {
            if(t.getMid().equals(getId())) {
                s.add((Students)ic.getById(Students.class, t.getSid()));
            }
        }

        return s;
    }

    public List<Staff> getModuleStaff() {
        List<Staff> s = new ArrayList<>();
        IController ic = new Controller();
        for(Teach t : (List<Teach>)ic.getAll(Teach.class)) {
            if(t.getMid().equals(getId())) {
                s.add((Staff)ic.getById(Staff.class, t.getSid()));
            }
        }

        return s;
    }

    public List<ModuleBooking> getModuleBookings() {
        return moduleBookings;
    }

    public void setModuleBookings(List<ModuleBooking> moduleBookings) {
        this.moduleBookings = moduleBookings;
    }

    public ModuleRequirements getModuleRequirements() {
        return moduleRequirements;
    }
}
