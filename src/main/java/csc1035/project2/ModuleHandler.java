package csc1035.project2;

import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ModuleHandler class to manage student and staff relationships with modules.
 *
 * <p>This class contains a list of all staff, students and the modules they are teaching/taking
 * within the database. The methods are to be used by the IO to add or remove people from teaching
 * or taking a specific module.</p>
 */
public class ModuleHandler {

    private List<Modules> modules;
    private List<Students> students;
    private List<Staff> staff;
    private List<Take> takes;
    private List<Teach> teaches;

    /**
     * ModuleHandler constructor method.
     */
    public ModuleHandler() {
        IController ic = new Controller();
        modules = new ArrayList<Modules>(ic.getAll(Modules.class));
        students = new ArrayList<Students>(ic.getAll(Students.class));
        staff = new ArrayList<Staff>(ic.getAll(Staff.class));
        takes = new ArrayList<Take>(ic.getAll(Take.class));
        teaches = new ArrayList<Teach>(ic.getAll(Teach.class));
    }

    /**
     * Method to update ModuleHandler after a change to database is made.
     */
    public void refreshModuleHandler() {
        IController ic = new Controller();
        this.setModules( new ArrayList<Modules>(ic.getAll(Modules.class)) );
        this.setStudents( new ArrayList<Students>(ic.getAll(Students.class)) );
        this.setStaff( new ArrayList<Staff>(ic.getAll(Staff.class)) );
        this.setTakes( new ArrayList<Take>(ic.getAll(Take.class)) );
        this.setTeaches( new ArrayList<Teach>(ic.getAll(Teach.class)) );

    }

    /**
     * Method to add student to a module.
     *
     * <p>If the student is already taking the module specified, method
     * does not add to the student's taken module list.</p>
     *
     * @param s Student to take to a module.
     * @param m Module for a student to take.
     * @return If addition was successful.
     */
    public boolean addStudentToModule ( Students s, Modules m ) {
        IController ic = new Controller();
        if( !m.getModuleStudents().contains( s ) ) {

            Set<Modules> tmp = s.getModules();
            tmp.add( m );
            s.setModules( tmp );
            ic.update( s );

            refreshModuleHandler();
            return true;
        }
        return false;
    }

    /**
     * Method to add staff to a module.
     *
     * <p>If the staff is teaching taking the module specified, method
     * does not add to the staff's taught module list.</p>
     *
     * @param s Staff to teach to a module.
     * @param m Module for staff to teach.
     * @return If addition was successful.
     */
    public boolean addStaffToModule ( Staff s, Modules m ) {
        IController ic = new Controller();
        if( !m.getModuleStaff().contains( s ) ) {

            Set<Modules> tmp = s.getModules();
            tmp.add( m );
            s.setModules( tmp );
            ic.update( s );

            refreshModuleHandler();
            return true;
        }
        return false;
    }

    /**
     * Method to remove student from module.
     *
     * @param s Student to stop taking module.
     * @param m Module for student to stop taking.
     * @return If removal was successful.
     */
    public boolean removeStudentFromModule ( Students s, Modules m ) {
        IController ic = new Controller();
        if( m.getModuleStudents().contains( s ) ) {

            Set<Modules> tmp = s.getModules();
            tmp.remove( m );
            s.setModules( tmp );
            ic.update( s );

            refreshModuleHandler();
            return true;
        }
        return false;
    }

    /**
     * Method to remove staff from module.
     *
     * @param s Staf to stop teaching module.
     * @param m Module for staff to stop teaching.
     * @return If removal was successful.
     */
    public boolean removeStaffFromModule ( Staff s, Modules m ) {
        IController ic = new Controller();
        if( m.getModuleStaff().contains( s ) ) {

            Set<Modules> tmp = s.getModules();
            tmp.remove( m );
            s.setModules( tmp );
            ic.update( s );

            return true;
        }
        return false;
    }
    /**
     * <code>modules</code> getter method.
     *
     * @return All modules in database.
     */
    public List<Modules> getModules() {
        return modules;
    }

    /**
     * <code>students</code> getter method.
     *
     * @return All students in database.
     */
    public List<Students> getStudents() {
        return students;
    }

    /**
     * <code>staff</code> getter method.
     *
     * @return All staff in database.
     */
    public List<Staff> getStaff() {
        return staff;
    }

    /**
     * <code>takes</code> getter method.
     *
     * @return All student-module relationships in database.
     */
    public List<Take> getTakes() {
        return takes;
    }

    /**
     * <code>teaches</code> getter method.
     *
     * @return All staff-module relationships in database.
     */
    public List<Teach> getTeaches() {
        return teaches;
    }

    /**
     * <code>modules</code> getter method.
     *
     * @param modules New module list.
     */
    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }

    /**
     * <code>students</code> getter method.
     *
     * @param students New student list.
     */
    public void setStudents(List<Students> students) {
        this.students = students;
    }

    /**
     * <code>staff</code> getter method.
     *
     * @param staff New staff list.
     */
    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    /**
     * <code>takes</code> getter method.
     *
     * @param takes New student-module relationship list.
     */
    public void setTakes(List<Take> takes) {
        this.takes = takes;
    }

    /**
     * <code>teaches</code> getter method.
     *
     * @param teaches New staff-module relationship list.
     */
    public void setTeaches(List<Teach> teaches) {
        this.teaches = teaches;
    }

    /**
     * <code>teaches</code> getter method.
     *
     * @param teaches New staff-module relationship list.
     */
    public void setTeaches(List<Teach> teaches) {
        this.teaches = teaches;
    }
}
