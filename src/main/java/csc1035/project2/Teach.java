package csc1035.project2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @EmbeddedId
    private TeachID id = new TeachID();

    @ManyToOne
    @MapsId( "sid" )
    private Staff staff;

    @ManyToOne
    @MapsId( "mid" )
    private Modules modules;

    public Teach() {

    }

    public Teach ( Staff s, Modules m ) {
        this.staff = s;
        this.modules = m;
    }

    public TeachID getId() {
        return id;
    }

    public Staff getStaff() {
        return staff;
    }

    public Modules getModules() {
        return modules;
    }

    public void setId(TeachID id) {
        this.id = id;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    @Embeddable
    public static class TeachID implements Serializable {

        private String sid;
        private String mid;

        public TeachID() {

        }

        public TeachID ( String sid, String mid ) {
            super();
            this.sid = sid;
            this.mid = mid;
        }

        public String getSid() {
            return sid;
        }

        public String getMid() {
            return mid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeachID teachID = (TeachID) o;
            return Objects.equals(sid, teachID.sid) &&
                    Objects.equals(mid, teachID.mid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sid, mid);
        }
    }
}
