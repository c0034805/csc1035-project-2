package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Take")
public class Take {
    @Id
    @Column(name = "Student_ID", nullable = false)
    private int sid;

    @Column(name = "Module_ID", unique = true, nullable = false)
    private String mid;

    public Take(int sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    public Take() {
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
