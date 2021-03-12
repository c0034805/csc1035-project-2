package csc1035.project2;

import javax.persistence.*;

/**
 * A persistent class made to handle the Take table, which
 * links the Students table with the Modules table. It represents which
 * modules are taken by which students.
 *
 * The table contains a module ID and a student ID.
 *
 * This Class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Take")
public class Take {
    @Id
    @Column(name = "Module_ID", nullable = false)
    private String mid;

    @Column(name = "Student_ID", nullable = false)
    private String sid;

    @ManyToOne
    @JoinColumn(nullable = false,insertable = false, updatable = false)
    private Students student;

    @OneToOne(mappedBy = "take")
    private Modules module;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param sid The student ID.
     * @param mid The module ID.
     */
    public Take(String sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    /**
     * The default constructor for Hibernate.
     */
    public Take() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != Take.class) return false;
        Take t = (Take) o;
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

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
