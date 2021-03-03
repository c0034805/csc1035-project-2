package csc1035.test_suite;

import csc1035.project2.Room;
import csc1035.project2.RoomHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RoomHandlerTest {
    //these tests rely on the database containing the sample data
    RoomHandler handler;

    @BeforeEach
    void setUp() {
        RoomHandler handler = new RoomHandler();
        handler.clearBookings();
    }

    @Test
    public void getAllReturnsAll() {
        boolean checker = true;
        RoomHandler handler = new RoomHandler();

        String[] idsArr = {"0.379","0.365","1.846","1.862","2.484","2.019","3.473","3.940","4.178","4.550"};
        List<String> ids = Arrays.asList(idsArr);

        List<Room> rooms = handler.getAll();
        for (Room room: rooms){
            if (!ids.contains(room.getNum())) {
                checker = false;
                break;
            }
        }
        checker =  checker && ids.size() == rooms.size();
        Assertions.assertTrue(checker);
    }



}
