package csc1035.project2;

import javax.persistence.*;

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

    public Teach(String sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    public Teach() {
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
