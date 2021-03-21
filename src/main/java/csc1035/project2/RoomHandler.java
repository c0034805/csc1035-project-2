package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * RoomHandler class to be used by the UI in order to perform tasks related room booking.
 *
 * <p>This class contains a list of all the rooms, bookings and entity-specific bookings
 * that are within the database. These lists update whenever a method that makes a change
 * to the database is called. This class also contains methods to retrieve booking and
 * room information that is to be used in the main UI.</p>
 */
public class RoomHandler {

    private List<Room> rooms;
    private List<Booking> bookings;
    private List<ModuleBooking> moduleBookings;
    private List<StudentBooking> studentBookings;
    private List<StaffBooking> staffBookings;

    /**
     * RoomHandler constructor method.
     */
    public RoomHandler() {
        IController ic = new Controller();
        rooms = new ArrayList<Room>(ic.getAll(Room.class));
        bookings = new ArrayList<Booking>(ic.getAll(Booking.class));
        moduleBookings = new ArrayList<ModuleBooking>(ic.getAll(ModuleBooking.class));
        staffBookings = new ArrayList<StaffBooking>(ic.getAll(StaffBooking.class));
        studentBookings = new ArrayList<StudentBooking>(ic.getAll(StudentBooking.class));
    }

    /**
     * Method to update RoomHandler after a change to database is made.
     */
    public void refreshRoomHandler() {
        IController ic = new Controller();
        this.setRooms( new ArrayList<Room>(ic.getAll(Room.class)) );
        this.setBookings( new ArrayList<Booking>(ic.getAll(Booking.class)) );
        this.setModuleBookings( new ArrayList<ModuleBooking>(ic.getAll(ModuleBooking.class)) );
        this.setStaffBookings( new ArrayList<StaffBooking>(ic.getAll(StaffBooking.class)) );
        this.setStudentBookings( new ArrayList<StudentBooking>(ic.getAll(StudentBooking.class)) );

    }
    //TODO: Please someone sort the persistent classes out.
    /**
     * Method to reserve a room for a student.
     *
     * <p>Method returns booking ID for use in I/O.</p>
     *
     * @param s Student object for booking.
     * @param r Room to be booked.
     * @param st Reservation starting time.
     * @param et Reservation ending time.
     * @return Booking ID if booking successful, blank if not.
     */
    public String reserveRoomStudent ( Students s, Room r, Timestamp st, Timestamp et ) {
        if ( checkRoomTimeAvailable( r, st, et) ) {

            Booking b = new Booking(st, et);
            b.setRoom( r );

            StudentBooking sb = new StudentBooking(b, s);
            updateClasses( b, sb );

            refreshRoomHandler();
            return b.getId();
        }
        return "";
    }

    /**
     * Method to reserve a room for a staff.
     *
     * <p>Method returns booking ID for use in I/O.</p>
     *
     * @param s Staff object for booking.
     * @param r Room to be booked.
     * @param st Reservation starting time.
     * @param et Reservation ending time.
     * @return Booking ID if booking successful, blank if not.
     */
    public String reserveRoomStaff ( Staff s, Room r, Timestamp st, Timestamp et ) {
        if ( checkRoomTimeAvailable( r, st, et) ) {
            Booking b = new Booking(st, et);
            b.setRoom( r );

            StaffBooking sb = new StaffBooking( b, s );
            updateClasses( b, sb );

            refreshRoomHandler();
            return b.getId();
        }
        return "";
    }

    /**
     * Method to reserve a room for a module.
     *
     * <p>Method returns booking ID for use in I/O.</p>
     *
     * @param m Module object for booking.
     * @param r Room to be booked.
     * @param st Reservation starting time.
     * @param et Reservation ending time.
     * @return Booking ID if booking successful, blank if not.
     */
    public String reserveRoomModule ( Modules m, Room r, Timestamp st, Timestamp et ) {
        if ( checkRoomTimeAvailable( r, st, et) ) {
            Booking b = new Booking(st, et);
            b.setRoom(r);

            ModuleBooking mb = new ModuleBooking(b, m);
            updateClasses( b, mb );

            refreshRoomHandler();
            return b.getId();
        }
        return "";
    }
    //Method specifically to persist bookings in correct order.
    private <T> void updateClasses( Booking b, Object entityBooking ) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction();
        ses.persist( b );
        ses.persist( entityBooking );
        ses.getTransaction().commit();
        ses.close();
    }

    /**
     * Method to display booking details.
     *
     * <p>This method is to be used in the user interface to display the information before the user
     * confirms the booking and updates the database.</p>
     *
     * @param bt String for displaying which booking type it is.
     * @param b Booking info to be displayed.
     */
    public void bookingConfirmation ( String bt, Booking b ) {
        System.out.println( "Booking Confirmation: \n\n" +
                            "Booking Type: " + bt + "\n" +
                            "Room: " + b.getRoom().getNum() + "\n" +
                            "Start Time: " + b.getStart() + "\n" +
                            "Finish Time: " + b.getEnd() + "\n" +
                            "Booking ID: " + b.getId() + "\n");
    }

    // TODO: Make sure to test when deleting a reservation that the module/student/staff booking also deletes.
    /**
     * Method to cancel reservation by id.
     *
     * @param id Booking id to cancel.
     */
    public void cancelReservation ( String id ) {
        IController ic = new Controller();
        try {
            Booking b = (Booking) ic.getById(Booking.class, id);
            if (b.getStaffBooking() != null) {
                ic.delete(StaffBooking.class, id);
            } else if (b.getStudentBooking() != null) {
                ic.delete(StudentBooking.class, id);
            } else if (b.getModuleBooking() != null) {
                ic.delete(ModuleBooking.class, id);
            }
            ic.delete(Booking.class, id);
            refreshRoomHandler();
        }
        catch (NoResultException e) {

        }
    }

    /**
     * Method to return all rooms with a reservation.
     *
     * @return List of all reserved rooms.
     */
    public List<Room> getReservedRooms () {
        List<Room> tmp = new ArrayList<>();

        for ( Booking b : this.getBookings() ) {
            if ( !tmp.contains( b.getRoom() ) ) {
                tmp.add( b.getRoom() );
            }
        }

        return tmp;
    }

    /**
     * Method to return all rooms available at a specified time.
     *
     * <p>Method checks if a room is within a booking and if the timestamp given is within the allocated time of
     * the booking. If so then it removes it from the temporary list and returns the remaining rooms.</p>
     *
     * @param t Timestamp to check against.
     * @return List of all available rooms at a specified time.
     */
    public List<Room> getAvailableRooms ( Timestamp t ) {
        List<Room> tmp = new ArrayList<>( this.getRooms() );
        for ( Room r : tmp ) {
            for ( Booking b : this.getBookings() ) {
                if ( r.getNum() == b.getRoom().getNum() && checkTimeAvailable( t, b.getStart(), b.getEnd() ) ) {
                    tmp.remove( r );
                }
            }
        }
        return tmp;
    }

    /**
     * Method to update a room's details.
     *
     * @param r Room to be updated.
     * @param num Room number to update to.
     * @param type Room type to update to.
     * @param cap Person cap to update to.
     * @param sd_cap Social distanced person cap to update to.
     */
    public void updateRoomDetails ( Room r, String num, String type, int cap, int sd_cap ) {
        String oldNum = r.getNum();
        r.setNum( num );
        r.setType( type );
        r.setCap( cap );
        r.setSd_cap( sd_cap );
        IController ic = new Controller();
        ic.update( r );
        if(!oldNum.equals(num)){
            ic.delete(Room.class, oldNum);
        }
        refreshRoomHandler();
    }

    /**
     * Method to check if time is between two other times.
     *
     * @param t Timestamp to be checked.
     * @param st Lower-end time.
     * @param et Upper-end time.
     * @return If <code>t</code> is between <code>st</code> and <code>et</code>.
     */
    public boolean checkTimeAvailable ( Timestamp t, Timestamp st, Timestamp et ) {
        return !(t.after(st) && t.before(et));
    }

    /**
     * Method to check if booked time overlaps with previous bookings of the same room.
     *
     * @param r Room.
     * @param st Booking start time.
     * @param et Booking finish time.
     * @return True if times given are available.
     */
    public boolean checkRoomTimeAvailable ( Room r, Timestamp st, Timestamp et ) {
        for ( Booking b : this.getBookings() ) {
            if ( b.getRoom().getNum() == r.getNum() ) {
                if ( !checkTimeAvailable(st, b.getStart(), b.getEnd()) ) {
                    System.out.println( "Starting time " + st + " conflicts with booking with time " +
                                        b.getStart() + "-" + b.getEnd() );
                    return false;
                }
                else if ( !checkTimeAvailable(et, b.getStart(), b.getEnd()) ) {
                    System.out.println( "Finish time " + et + " conflicts with booking with time " +
                                        b.getStart() + "-" + b.getEnd() );
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if string is an integer.
     *
     * @param s String to check.
     * @return If string is purely an integer.
     */
    public boolean isInteger( String s ) {
        try {
            int i =  Integer.parseInt( s );
            return true;
        }
        catch ( NumberFormatException nfe ) {
            return false;
        }
    }

    /**
     * <code>rooms</code> getter method.
     *
     * @return All rooms in database.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * <code>bookings</code> getter method.
     *
     * @return All bookings in database.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * <code>moduleBookings</code> getter method.
     *
     * @return All module bookings in database.
     */
    public List<ModuleBooking> getModuleBookings() {
        return moduleBookings;
    }

    /**
     * <code>staffBookings</code> getter method.
     *
     * @return All staff bookings in database.
     */
    public List<StaffBooking> getStaffBookings() {
        return staffBookings;
    }

    /**
     * <code>studentBookings</code> getter method.
     *
     * @return All student bookings in database.
     */
    public List<StudentBooking> getStudentBookings() {
        return studentBookings;
    }

    /**
     * <code>rooms</code> setter method.
     *
     * @param rooms New room list.
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * <code>bookings</code> setter method.
     *
     * @param bookings New bookings list.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * <code>moduleBookings</code> setter method.
     *
     * @param moduleBookings New module bookings list.
     */
    public void setModuleBookings(List<ModuleBooking> moduleBookings) {
        this.moduleBookings = moduleBookings;
    }

    /**
     * <code>staffBookings</code> setter method.
     *
     * @param staffBookings New staff bookings list.
     */
    public void setStaffBookings(List<StaffBooking> staffBookings) {
        this.staffBookings = staffBookings;
    }

    /**
     * <code>studentBookings</code> setter method.
     *
     * @param studentBookings New student bookings list.
     */
    public void setStudentBookings(List<StudentBooking> studentBookings) {
        this.studentBookings = studentBookings;
    }
}
