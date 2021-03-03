package csc1035.project2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @Column(name = "Room_Number")
    private String num;

    @Column(name = "Type")
    // TODO: Stop using string IDs for things like this. This should be an enum or a class.
    private String type;

    @Column(name = "Max_Capacity")
    private int cap;

    @Column(name = "Social_Distancing_Capacity")
    private int sd_cap;

    public Room() {}

    public Room(String num, String type, int cap, int sd_cap) {
        this.num = num;
        this.type = type;
        this.cap = cap;
        this.sd_cap = sd_cap;
    }

    public String getNum() {
        return num;
    }

    public String getType() {
        return type;
    }

    public int getCap() {
        return cap;
    }

    public int getSd_cap() {
        return sd_cap;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void setSd_cap(int sd_cap) {
        this.sd_cap = sd_cap;
    }
}
