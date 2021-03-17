package csc1035.project2;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
@Table ( name = "Take" )
public class Take {
    @EmbeddedId
    private TakeID id = new TakeID();

    @ManyToOne
    @MapsId( "sid" )
    private Students students;

    @ManyToOne
    @MapsId( "mid" )
    private Modules modules;

    public Take() {

    }

    public Take ( Students s, Modules m ) {
        this.students = s;
        this.modules = m;
    }
    public TakeID getId() {
        return id;
    }

    public Students getStudents() {
        return students;
    }

    public Modules getModules() {
        return modules;
    }

    public void setId(TakeID id) {
        this.id = id;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    @Embeddable
    public static class TakeID implements Serializable {

       private String sid;
       private String mid;

       public TakeID() {

       }

       public TakeID ( String sid, String mid ) {
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
           TakeID takeID = (TakeID) o;
           return Objects.equals(sid, takeID.sid) &&
                   Objects.equals(mid, takeID.mid);
       }

       @Override
       public int hashCode() {
           return Objects.hash(sid, mid);
       }
   }
}
