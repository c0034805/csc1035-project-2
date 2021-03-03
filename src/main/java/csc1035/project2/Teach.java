package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Teach")
public class Teach {
    @Id
    @Column(name = "Staff_ID", nullable = false)
    private String sid;

    @Column(name = "Module_ID", nullable = false, unique = true)
    private int mid;

    public Teach(String sid, int mid) {
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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }
}
