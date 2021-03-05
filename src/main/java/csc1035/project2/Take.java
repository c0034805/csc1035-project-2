package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Take")
public class Take {
    @Id
    @Column(name = "Module_ID", nullable = false)
    private String mid;

    @Column(name = "Student_ID", nullable = false)
    private String sid;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Students student;

    @OneToOne(mappedBy = "take")
    private Modules module;

    public Take(String sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    public Take() {
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
