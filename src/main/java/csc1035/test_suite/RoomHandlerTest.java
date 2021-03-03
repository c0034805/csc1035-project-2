package csc1035.test_suite;

import csc1035.project2.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RoomHandlerTest {
    //these tests rely on the database containing the sample data
    RoomHandler handler;
    IController controller;

    @BeforeEach
    void setUp() {
        handler = new RoomHandler();
        handler.clearBookings();
        controller = new Controller();
    }

    @Test
    public void getRoomsReturnsAll() {
        boolean checker = true;

        String[] idsArr = {"0.379","0.365","1.846","1.862","2.484","2.019","3.473","3.940","4.178","4.550"};
        List<String> ids = Arrays.asList(idsArr);

        List<Room> rooms = handler.getRooms();
        for (Room room: rooms){
            if (!ids.contains(room.getNum())) {
                checker = false;
                break;
            }
        }
        checker =  checker && ids.size() == rooms.size();
        Assertions.assertTrue(checker);
    }

    public void studentReservation(){
        Students s = (Students) controller.getById(Students.class,216906208);
        Room r = (Room) controller.getById(Room.class,"0.379");

        Timestamp start = new Timestamp(2021, 6, 22,
                12, 0,0,0);
        Timestamp end = new Timestamp(2021, 6, 22,
                13, 0,0,0);

        handler.reserveRoomStudent(s,r,start,end);
    }

    @Test
    public void reserveRoomStudentReservesRoom(){
        studentReservation();
        //need to figure out how the booking ID gets made

    }



}
