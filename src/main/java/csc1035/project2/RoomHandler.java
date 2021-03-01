package csc1035.project2;

import java.util.ArrayList;
import java.util.List;

// This might end up mapping to the Reservations table. See how we use it.
public class RoomHandler {
    private List<Room> rooms;

    public RoomHandler() {
        IController ic = new Controller();
        rooms = new ArrayList<Room>(ic.getAll(Room.class));
    }

}
