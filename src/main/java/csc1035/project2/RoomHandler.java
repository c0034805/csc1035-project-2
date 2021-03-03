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

    /*
    public void reserveRoomStudent ( Students s, Room r, Timestamp st, Timestamp et ) {
        Booking b = new Booking( Integer.parseInt(r.getNum()), st, et );
    }
    */

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
                if ( Integer.parseInt(r.getNum()) == b.getNum() && (t.after(b.getStart()) && t.before(b.getEnd())) ) {
                    tmp.remove( r );
                }
            }
        }
        return tmp;
    }
}
