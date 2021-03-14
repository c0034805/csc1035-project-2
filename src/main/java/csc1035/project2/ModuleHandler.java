package csc1035.project2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * ModuleHandler class to manage student and staff relationships with modules.
 */
public class ModuleHandler {

    private List<Modules> modules;
    private List<Students> students;
    private List<Staff> staff;

    /**
     * ModuleHandler constructor method.
     */
    public ModuleHandler() {
        IController ic = new Controller();
        modules = new ArrayList<Modules>(ic.getAll(Modules.class));
        students = new ArrayList<Students>(ic.getAll(Students.class));
        staff = new ArrayList<Staff>(ic.getAll(Staff.class));
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
}
