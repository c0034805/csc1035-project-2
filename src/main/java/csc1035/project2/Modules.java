package csc1035.project2;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany( mappedBy = "modules" , fetch = FetchType.EAGER)
    private Set<Take> takes = new HashSet<>();

    @OneToMany(mappedBy = "modules", fetch = FetchType.EAGER)
    private Set<Teach> teaches = new HashSet<>();

    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER)
    private Set<ModuleBooking> moduleBookings = new HashSet<>();

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The module ID.
     * @param name The module name.
     * @param credits Credits for the module.
     * @param weeks Number of weeks the module runs for.
     */
    public Modules(String id, String name, int credits, int weeks, Set<ModuleBooking> moduleBookings) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modules modules = (Modules) o;
        return credits == modules.credits &&
                weeks == modules.weeks &&
                Objects.equals(id, modules.id) &&
                Objects.equals(name, modules.name) &&
                Objects.equals(moduleRequirements, modules.moduleRequirements) &&
                Objects.equals(takes, modules.takes) &&
                Objects.equals(teaches, modules.teaches) &&
                Objects.equals(moduleBookings, modules.moduleBookings);
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
            if(t.getId().getMid().equals(getId())) {
                s.add((Students)ic.getById(Students.class, t.getId().getSid()));
            }
        }

        return s;
    }

    public List<Staff> getModuleStaff() {
        List<Staff> s = new ArrayList<>();
        IController ic = new Controller();
        for(Teach t : (List<Teach>)ic.getAll(Teach.class)) {
            if(t.getId().getMid().equals(getId())) {
                s.add((Staff)ic.getById(Staff.class, t.getId().getSid()));
            }
        }

        return s;
    }

    public Set<ModuleBooking> getModuleBookings() {
        return moduleBookings;
    }

    public void setModuleBookings(Set<ModuleBooking> moduleBookings) {
        this.moduleBookings = moduleBookings;
    }

    public ModuleRequirements getModuleRequirements() {
        return moduleRequirements;
    }

    public Set<Take> getTakes() {
        return takes;
    }

    public void setTakes(Set<Take> takes) {
        this.takes = takes;
    }

    public Set<Teach> getTeaches() {
        return teaches;
    }

    public void setTeaches(Set<Teach> teaches) {
        this.teaches = teaches;
    }

    @Override
    public String toString() {
        return "Module{\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Credits: " + credits + "\n" +
                "Weeks: " + weeks + "\n" +
                "}";
    }
}
