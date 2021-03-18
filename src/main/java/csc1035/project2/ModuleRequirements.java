package csc1035.project2;

import javax.persistence.*;
import java.util.Date;

/**
 * A persistent class made to handle the Module_Requirements table.
 *
 * The table contains a module ID, the number of lecturers for that
 * module, the number of practicals, as well as its lecture and
 * practical length.
 *
 * This class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 * @author Stefanos Larkou
 */
@Entity
@Table(name = "Module_Requirements")
public class ModuleRequirements {
    @Id
    @Column(name = "ID",nullable = false)
    private String id;

    @Column(name = "No_of_Lectures",nullable = false)
    private int lectures;

    @Column(name = "Lecture_Length",nullable = false)
    private int llength;

    @Column(name = "No_of_Practicals",nullable = false)
    private int practicals;

    @Column(name = "Practical_Length",nullable = false)
    private int plength;

    @Column(name = "Commencing", nullable = false)
    private Date commencing;

    @OneToOne(mappedBy = "moduleRequirements")
    private Modules modules;

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param id The module ID.
     * @param lecturers The number of lecturers.
     * @param llength The lecture length.
     * @param practicals The number of practicals.
     * @param plength The practical length.
     */
    public ModuleRequirements(String id, int lectures, int llength,
                              int practicals, int plength, Date commencing) {
        this.id = id;
        this.lectures = lectures;
        this.llength = llength;
        this.practicals = practicals;
        this.plength = plength;
        this.commencing = commencing;
    }

    /**
     * The default constructor for Hibernate.
     */
    public ModuleRequirements() {
    }

    /**
     * @param o The object for comparison
     * @return returns true if all attributes in both objects are the same, or if they have the same memory address
     */
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || o.getClass() != ModuleRequirements.class) return false;
        ModuleRequirements m = (ModuleRequirements) o;
        return this.id.equals(m.getId()) &&
                this.lectures == m.getLectures() &&
                this.llength == m.getLlength() &&
                this.practicals == m.getPracticals() &&
                this.plength == m.getPlength();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLectures() {
        return lectures;
    }

    public void setLectures(int lecturer) {
        this.lectures = lectures;
    }

    public int getLlength() {
        return llength;
    }

    public void setLlength(int llength) {
        this.llength = llength;
    }

    public int getPracticals() {
        return practicals;
    }

    public void setPracticals(int practicals) {
        this.practicals = practicals;
    }

    public int getPlength() {
        return plength;
    }

    public void setPlength(int plength) {
        this.plength = plength;
    }

    public Date getCommencing() {
        return commencing;
    }

    public void setCommencing(Date commencing) {
        this.commencing = commencing;
    }
}
