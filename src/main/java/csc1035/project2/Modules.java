package csc1035.project2;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "moduleBooking")
    private List<ModuleBooking> moduleBookings;

    public Modules(String id, String name, int credits, int weeks) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
    }

    public Modules() {
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
}
