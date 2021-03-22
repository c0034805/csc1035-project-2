package csc1035.project2;

import java.util.List;

/**
 * This class encapsulates all the information about a particular session in a weekly timetable.
 */
public class Session {
    /**
     * This enum should be used instead of using integer offsets from monday for allocating a day for a session.
     */
    enum day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    private Room room;
    private List<Students> students;
    // We assume exactly one staff member is needed per class
    private Staff staff;
    private int start;
    private int end;
    private day day_of_week;

    public Session() {}

    public Session(Room room, List<Students> students, Staff staff, int start, int end, day day_of_week) {
        this.room = room;
        this.students = students;
        this.staff = staff;
        this.start = start;
        this.end = end;
        this.day_of_week = day_of_week;
    }

    public Room getRoom() {
        return room;
    }

    public List<Students> getStudents() {
        return students;
    }

    public Staff getStaff() {
        return staff;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public day getDay_of_week() {
        return day_of_week;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setDay_of_week(day day_of_week) {
        this.day_of_week = day_of_week;
    }
}
