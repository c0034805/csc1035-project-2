package csc1035.test_suite;

import csc1035.project2.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
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

    public void studentReservation(int startHr, int endHr){
        Students s = (Students) controller.getById(Students.class,216906208);
        Room r = (Room) controller.getById(Room.class,"0.379");

        Timestamp start = new Timestamp(2021, 6, 22,
                startHr, 0,0,0);
        Timestamp end = new Timestamp(2021, 6, 22,
                endHr, 0,0,0);

        handler.reserveRoomStudent(s,r,start,end);
    }
    public void staffReservation(int startHr, int endHr){
        Staff s = (Staff) controller.getById(Staff.class,"NUC3292317");
        Room r = (Room) controller.getById(Room.class,"0.379");

        Timestamp start = new Timestamp(2021, 6, 22,
                startHr, 0,0,0);
        Timestamp end = new Timestamp(2021, 6, 22,
                endHr, 0,0,0);

        handler.reserveRoomStaff(s,r,start,end);
    }
    public void moduleReservation(int startHr, int endHr){
        Module m = (Module) controller.getById(Module.class,"RSI3393");
        Room r = (Room) controller.getById(Room.class,"0.379");

        Timestamp start = new Timestamp(2021, 6, 22,
                startHr, 0,0,0);
        Timestamp end = new Timestamp(2021, 6, 22,
                endHr, 0,0,0);

        handler.reserveRoomModule(m,r,start,end);
    }

    @Test
    public void reserveRoomStudentReservesRoom(){

        studentReservation(11,12);
        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(StudentBooking.class).size());

        studentReservation(13,23);
        Assertions.assertEquals(2,controller.getAll(Booking.class).size());
        Assertions.assertEquals(2,controller.getAll(StudentBooking.class).size());

    }
    @Test
    public void reserveRoomStaffReservesRoom(){

        staffReservation(11,12);
        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(StaffBooking.class).size());

        staffReservation(13,23);
        Assertions.assertEquals(2,controller.getAll(Booking.class).size());
        Assertions.assertEquals(2,controller.getAll(StaffBooking.class).size());

    }
    @Test
    public void reserveRoomModuleReservesRoom(){

        moduleReservation(11,12);
        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(ModuleBooking.class).size());

        moduleReservation(13,23);
        Assertions.assertEquals(2,controller.getAll(Booking.class).size());
        Assertions.assertEquals(2,controller.getAll(ModuleBooking.class).size());

    }

    @Test
    public void cancelReservationCancelsAllTypes(){
        studentReservation(11,12);
        staffReservation(12,13);
        moduleReservation(13,14);
        List<Booking> bookings = controller.getAll(Booking.class);
        Assertions.assertEquals(1,bookings.size());
        Assertions.assertEquals(1,controller.getAll(StudentBooking.class).size());

        handler.cancelReservation(bookings.get(0).getId());
        handler.cancelReservation(bookings.get(1).getId());
        handler.cancelReservation(bookings.get(2).getId());

        Assertions.assertEquals(0,controller.getAll(Booking.class).size());
        Assertions.assertEquals(0,controller.getAll(StudentBooking.class).size());
        Assertions.assertEquals(0,controller.getAll(ModuleBooking.class).size());
        Assertions.assertEquals(0,controller.getAll(StaffBooking.class).size());
    }

    @Test
    public void cancelReservationIDCheck(){
        studentReservation(11,12);
        handler.cancelReservation(Integer.MAX_VALUE);

        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(StudentBooking.class).size());
    }

    @Test
    public void getReservedRoomsReturnsCorrectSize(){
        studentReservation(11,12);
        Timestamp middle = new Timestamp(2021, 6, 22,
                11, 30,0,0);
        Timestamp after = new Timestamp(2999, 6, 22,
                11, 30,0,0);

        Assertions.assertEquals(1,handler.getReservedRooms(middle).size());

        //just a couple years after that reservation
        Assertions.assertEquals(1,handler.getReservedRooms(after).size());

    }

    @Test
    public void getAvailableRoomsReturnsCorrectSize(){
        studentReservation(11,12);
        Timestamp middle = new Timestamp(2021, 6, 22,
                11, 30,0,0);
        Timestamp after = new Timestamp(2999, 6, 22,
                11, 30,0,0);

        int difference = handler.getReservedRooms(middle).size() - handler.getReservedRooms(after).size();

        //checks that the available list size after the reservations is the same as the room list size
        Assertions.assertEquals(controller.getAll(Room.class).size(),handler.getAvailableRooms(after).size());
        Assertions.assertEquals(1,difference);

    }




}
