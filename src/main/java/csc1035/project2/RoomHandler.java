package csc1035.project2;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

// This might end up mapping to the Reservations table. See how we use it.
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
     * Method to return all rooms with a reservation.
     *
     * @return List of all reserved rooms.
     */
    public List<Room> getReservedRooms () {
        List<Room> tmp = new ArrayList<>();
        for ( Room r : this.getRooms() ) {
            // TODO: Please someone switch the room number to integers!
            for ( Booking b : this.getBookings() ) {
                if ( Integer.parseInt(r.getNum()) == b.getNum() ) {
                    tmp.add( r );
                }
            }
        }
        return tmp;
    }

}
