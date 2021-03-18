package csc1035.project2;

import java.sql.Date;
import java.util.*;

public class TimetableHandler {
    RoomHandler rh;

    public TimetableHandler() {
        IController ic = new Controller();
        //modules = new ArrayList<Modules>(ic.getAll(Modules.class));
    }

    /**
     * Make bookings for school timetable sessions
     *
     * @param wb Date of monday in week to book for
     */
    public void makeTimetableBookings(Date wb) {

    }

    /**
     * Generate a weekly recurring timetable for all specified modules
     *
     * @param sd Allocate rooms at diminished capacity
     */
    public List<Session> generateSchoolTimetable(boolean sd, List<String> mod) {
        IController ic = new Controller();
        List<Modules> modules = new ArrayList<>();
        for (String id : mod) {
            modules.add((Modules) ic.getById(Modules.class, id));
        }

        List<Session> classes = new ArrayList<>();
        for (Modules m : modules) {
            List<Integer> blocks = new ArrayList<>();
            for (int i = 0; i < m.getModuleRequirements().getLectures(); ++i) {
                blocks.add(m.getModuleRequirements().getLlength());
            }
            for (int i = 0; i < m.getModuleRequirements().getPracticals(); ++i) {
                blocks.add(m.getModuleRequirements().getPlength());
            }

            for (int i = 0; i < blocks.size(); ++i) {
                List<Students> students = m.getModuleStudents();
                List<Staff> staff = m.getModuleStaff();
                List<Room> rooms = rh.getRooms();
                // Use largest rooms first to minimize staff requirements
                rooms.sort(Comparator.comparing(Room::getCap).reversed());

                while (!students.isEmpty() && !staff.isEmpty() && !rooms.isEmpty()) {
                    Session s = new Session();
                    int effective_cap = sd ? rooms.get(0).getSd_cap() : rooms.get(0).getCap();
                    List<Students> for_class = students.size() >= effective_cap ? students.subList(0, effective_cap - 1) : students;
                    s.setRoom(rooms.get(0));
                    s.setStudents(for_class);
                    s.setStaff(staff.get(0));

                    // Remove allocated room, students, and staff from lists
                    staff.remove(0);
                    rooms.remove(0);
                    for (Students st : for_class) {
                        students.remove(st);
                    }

                    classes.add(s);
                }
            }
        }

        return classes;
    }
}
