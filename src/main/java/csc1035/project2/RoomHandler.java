package csc1035.project2;

import java.awt.print.Book;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// This might end up mapping to the Reservations table. See how we use it.
// TODO: Please someone switch the room number to integers and remove all instances of `Integer.parseInt`
public class RoomHandler {


    private List<Room> rooms;
    private List<Booking> bookings;

    /**
     * RoomHandler constructor method.
     */
    public RoomHandler() {
        IController ic = new Controller();
        rooms = new ArrayList<Room>(ic.getAll(Room.class));
        bookings = new ArrayList<Booking>(ic.getAll(Booking.class));
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
     * Method to reserve a room for a student.
     *
     * <p>Method does not check if student is able to book a room at the specified times nor if they have
     * already booked this. This is to be checked through the interface for booking system using
     * other methods.</p>
     *
     * @param s Student object for booking
     * @param r Room to be booked
     * @param st Reservation starting time
     * @param et Reservation ending time
     */
    public void reserveRoomStudent ( Students s, Room r, Timestamp st, Timestamp et ) {
        Booking b = new Booking( Integer.parseInt(r.getNum()), st, et );
        IController ic = new Controller();
        ic.update( b );
        StudentBooking sb = new StudentBooking( b.getId(), s.getId() );
        ic.update( sb );
    }

    /**
     * Method to return all rooms with a reservation.
     *
     * @return List of all reserved rooms.
     */
    public List<Room> getReservedRooms () {
        List<Room> tmp = new ArrayList<>();
        for ( Room r : this.getRooms() ) {
            for ( Booking b : this.getBookings() ) {
                if ( Integer.parseInt(r.getNum()) == b.getNum() ) {
                    tmp.add( r );
                }
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
     * @param t Timestamp to check against
     * @return List of all available rooms at a specified time.
     */
    public List<Room> getAvailableRooms ( Timestamp t ) {
        List<Room> tmp = new ArrayList<>( this.getRooms() );
        for ( Room r : tmp ) {
            for ( Booking b : this.getBookings() ) {
                if ( Integer.parseInt(r.getNum()) == b.getNum() && checkTimeAvailable( t, b.getStart(), b.getEnd() ) ) {
                    tmp.remove( r );
                }
            }
        }
        return tmp;
    }

    /**
     * Method to check if time is between two other times.
     *
     * @param t Timestamp to be checked
     * @param st Lower-end time
     * @param et Upper-end time
     * @return If <code>t</code> is between <code>st</code> and <code>et</code>.
     */
    public boolean checkTimeAvailable ( Timestamp t, Timestamp st, Timestamp et ) {
        return (t.after(st) && t.before(et));
    }
}
