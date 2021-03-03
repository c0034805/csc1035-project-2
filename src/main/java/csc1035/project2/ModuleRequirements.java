package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Module_Requirements")
public class ModuleRequirements {
    @Id
    @Column(name = "ID",nullable = false)
    private String id;

    @Column(name = "No_of_Lecturers",nullable = false)
    private int lecturers;

    @Column(name = "Lecture_Length",nullable = false)
    private int llength;

    @Column(name = "No_of_Practicals",nullable = false)
    private int practicals;

    @Column(name = "Practical_Length",nullable = false)
    private int plength;

    @OneToOne(mappedBy = "moduleRequirements")
    private Modules modules;

    public ModuleRequirements(String id, int lecturers, int llength, int practicals, int plength) {
        this.id = id;
        this.lecturers = lecturers;
        this.llength = llength;
        this.practicals = practicals;
        this.plength = plength;
    }

    public ModuleRequirements() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLecturers() {
        return lecturers;
    }

    public void setLecturers(int lecturers) {
        this.lecturers = lecturers;
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
}
