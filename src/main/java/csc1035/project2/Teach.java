package csc1035.project2;

import javax.persistence.*;

/**
 * A persistent class made to handle the Teach table, which
 * links the Staff table with the Modules table. It represents which
 * modules are taught by which staff members.
 *
 * The table contains a module ID and a staff ID.
 *
 * This Class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Teach")
public class Teach {
    @Id
    @Column(name = "Module_ID", nullable = false)
    private String mid;

    @Column(name = "Staff_ID", nullable = false)
    private String sid;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Staff staff;

    @OneToOne(mappedBy = "teach")
    private Modules module;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param sid The staff ID.
     * @param mid The module ID.
     */
    public Teach(String sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Teach() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != Teach.class) return false;
        Teach t = (Teach) o;
        return this.mid.equals(t.getMid()) && this.sid.equals(t.getSid());
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
