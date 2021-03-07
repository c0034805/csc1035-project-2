package csc1035.project2;

import javax.persistence.*;
import java.util.List;

/**
 * A persistent class made to handle the Rooms table.
 *
 * The table contains the room number, the room type, the room's max
 * capacity and its max capacity with social distancing rules in place.
 *
 * This class connects the attributes to their respective columns in the
 * table, makes the necessary relationships with other tables and
 * contains the relevant getter and setter methods.
 */
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

    @OneToMany(mappedBy = "room")
    private List<Room> rooms;

    /**
     * The default constructor for Hibernate.
     */
    public Room() {}

    /**
     * The constructor that connects the parameter values with the field
     * variables.
     *
     * @param num The room number.
     * @param type The room type.
     * @param cap The room's capacity.
     * @param sd_cap The room's capacity with social distancing rules in place.
     */
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
