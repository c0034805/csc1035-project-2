package test_suite;

import csc1035.project2.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class RoomHandlerTest extends MasterTest{
    RoomHandler handler;

    @Override
    @BeforeEach
    void setUp(){
        super.setUp();
        handler = new RoomHandler();
    }
    /**
     * Adds an hour value to a timestamp
     * @param stmp the timestamp to edit
     * @param hr the number of hours to add (long)
     * @return returns the timestamp + the specific number of hours
     */
    public Timestamp timestampAddHr(Timestamp stmp, float hr){

        hr *= 3600000;
        return new Timestamp((long) (stmp.getTime()+hr));
    }

    /**
     * Method that checks whether getRooms() gets all rooms
     */
    @Test
    public void getRoomsReturnsAll() {
        Object[] rooms = handler.getRooms().toArray();
        Assertions.assertArrayEquals(rooms,this.rooms);
    }

    /**
     * Method that makes a student reservation on the 22 June 2021
     * @param startHr The hour to start the reservation
     * @param endHr The hour to end the reservation
     */
    public void studentReservation(long startHr, long endHr){

        handler.reserveRoomStudent(students[0],rooms[0],timestampAddHr(day,startHr),timestampAddHr(day,endHr));
    }

    /**
     * Method that makes a staff reservation on the 22 June 2021
     * @param startHr The hour to start the reservation
     * @param endHr The hour to end the reservation
     */
    public void staffReservation(long startHr, long endHr){

        handler.reserveRoomStaff(staff[0],rooms[0],timestampAddHr(day,startHr),timestampAddHr(day,endHr));
    }

    /**
     * Method that makes a module reservation on the 22 June 2021
     * @param startHr The hour to start the reservation
     * @param endHr The hour to end the reservation
     */
    public void moduleReservation(long startHr, long endHr){

        handler.reserveRoomModule(modules[0],rooms[0],timestampAddHr(day,startHr),timestampAddHr(day,endHr));
    }

    /**
     * Method that checks if reserveRoomStudent() reserves a room
     */
    @Test
    public void reserveRoomStudentReservesRoom(){

        studentReservation(11,12);
        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(StudentBooking.class).size());

        studentReservation(13,23);
        Assertions.assertEquals(2,controller.getAll(Booking.class).size());
        Assertions.assertEquals(2,controller.getAll(StudentBooking.class).size());

    }

    /**
     * Method that checks if all db entries from ReserveRoomStudent are as expected
     */
    @Test
    public void reserveRoomStudentDetailsCorrect(){

        studentReservation(11,12);

        Booking b = (Booking)controller.getAll(Booking.class).get(0);
        StudentBooking sb = (StudentBooking) controller.getAll(StudentBooking.class).get(0);

        Assertions.assertEquals(b.getNum(),rooms[0].getNum());
        Assertions.assertEquals(sb.getSid(),students[0].getId());
        Assertions.assertEquals(b.getId(),sb.getBid());
        Assertions.assertEquals(b.getStart(),timestampAddHr(day,11));
        Assertions.assertEquals(b.getEnd(),timestampAddHr(day,12));


    }

    /**
     * Method that checks if reserveRoomStaff() reserves a room
     */
    @Test
    public void reserveRoomStaffReservesRoom(){

        staffReservation(11,12);
        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(StaffBooking.class).size());

        staffReservation(13,23);
        Assertions.assertEquals(2,controller.getAll(Booking.class).size());
        Assertions.assertEquals(2,controller.getAll(StaffBooking.class).size());

    }

    /**
     * Method that checks if all db entries from ReserveRoomStaff are as expected
     */
    @Test
    public void reserveRoomStaffDetailsCorrect(){

        staffReservation(11,12);

        Booking b = (Booking)controller.getAll(Booking.class).get(0);
        StaffBooking sb = (StaffBooking) controller.getAll(StaffBooking.class).get(0);

        Assertions.assertEquals(b.getNum(),rooms[0].getNum());
        Assertions.assertEquals(sb.getSid(),staff[0].getId());
        Assertions.assertEquals(b.getId(),sb.getBid());
        Assertions.assertEquals(b.getStart(),timestampAddHr(day,11));
        Assertions.assertEquals(b.getEnd(),timestampAddHr(day,12));


    }

    /**
     * Method that checks if reserveRoomModule() reserves a room
     */
    @Test
    public void reserveRoomModuleReservesRoom(){

        moduleReservation(11,12);
        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(ModuleBooking.class).size());

        moduleReservation(13,23);
        Assertions.assertEquals(2,controller.getAll(Booking.class).size());
        Assertions.assertEquals(2,controller.getAll(ModuleBooking.class).size());

    }

    /**
     * Method that checks if all db entries from ReserveRoomModule are as expected
     */
    @Test
    public void reserveRoomModuleDetailsCorrect(){

        studentReservation(11,12);

        Booking b = (Booking)controller.getAll(Booking.class).get(0);
        ModuleBooking mb = (ModuleBooking) controller.getAll(ModuleBooking.class).get(0);

        Assertions.assertEquals(b.getNum(),rooms[0].getNum());
        Assertions.assertEquals(mb.getId(),modules[0].getId());
        Assertions.assertEquals(b.getId(),mb.getBid());
        Assertions.assertEquals(b.getStart(),timestampAddHr(day,11));
        Assertions.assertEquals(b.getEnd(),timestampAddHr(day,12));


    }


    /**
     * Method that checks if cancel reservation deletes that reservation from all relevant booking tables
     */
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

    /**
     * Method that checks that there is no change when a non-existent reservation gets deleted
     */
    @Test
    public void cancelReservationIDCheck(){
        studentReservation(11,12);
        handler.cancelReservation("fakeID");

        Assertions.assertEquals(1,controller.getAll(Booking.class).size());
        Assertions.assertEquals(1,controller.getAll(StudentBooking.class).size());
    }

    /**
     * Method that checks if getReservedRooms return the correct number of Reservations
     */
    @Test
    public void getReservedRoomsReturnsCorrectSize(){
        studentReservation(11,12);

        Assertions.assertEquals(1,handler.getReservedRooms().size());

        //both reservations use the same room, so the size of the output should not change
        staffReservation(12,13);
        Assertions.assertEquals(1,handler.getReservedRooms().size());
    }

    /**
     * Method that checks if getReservedRooms returns the correct rooms
     */
    @Test
    public void getReservedRoomsReturnsCorrectValues(){
        studentReservation(11,12);
        handler.reserveRoomStaff(staff[0],rooms[1],timestampAddHr(day,11),timestampAddHr(day,12));
        // first makes 2 reservations, using room 0 and 1

        List<Room> results = handler.getReservedRooms();
        Assertions.assertEquals(2,results.size());

        //checks if 0 =0 and 1=1 or if 1=0 and 0=1 (Room)
        boolean checker = ((results.get(0) == rooms[0] && results.get(1) == rooms[1])||(results.get(0) == rooms[1] && results.get(1) == rooms[0]));
        Assertions.assertTrue(checker);

    }

    /**
     * Method that checks if getAvailableRooms returns the correct rooms
     */
    @Test
    public void getAvailableRoomsReturnsCorrectValues(){
        studentReservation(11,12);
        List<Room> results = handler.getAvailableRooms(timestampAddHr(day, 11.5F));

        Assertions.assertEquals(2,results.size());
        //checks if the results are the same as the two remaining rooms
        boolean checker = ((results.get(0) == rooms[1] && results.get(1) == rooms[2])||(results.get(0) == rooms[2] && results.get(1) == rooms[1]));
        Assertions.assertTrue(checker);

        List<Room> futureResults = handler.getAvailableRooms(timestampAddHr(day, 800F));
        Assertions.assertEquals(3,futureResults.size());

        //checks if all results contain all rooms (already checked if expected size)
        checker = true;
        for (Room room:rooms) {
            if(!futureResults.contains(room)){
                checker = false;
            }
        }
        Assertions.assertTrue(checker);
    }

    /**
     * Method that checks if update room details updates the expected values
     */
    @Test
    public void updateRoomDetailsCorrect(){
        String type = "newer";
        int cap = Integer.MAX_VALUE;
        int sd_cap = Integer.MIN_VALUE;

        handler.updateRoomDetails(rooms[0],rooms[0].getNum(),type,cap,sd_cap);

        Room updated = (Room)controller.getById(Room.class,rooms[0].getNum());
        Assertions.assertEquals(new Room(rooms[0].getNum(),type,cap,sd_cap,new ArrayList<Booking>()),updated);
        Assertions.assertEquals(handler.getRooms().size(),3);

    }

    /**
     * Method that checks if update room details updates the expected values, when ID is also updated
     */
    @Test
    public void updateRoomDetailsCorrectID(){
        String num = "new";
        String type = "newer";
        int cap = Integer.MAX_VALUE;
        int sd_cap = Integer.MIN_VALUE;

        handler.updateRoomDetails(rooms[0],num,type,cap,sd_cap);

        Room updated = (Room)controller.getById(Room.class,num);
        Assertions.assertEquals(new Room(num,type,cap,sd_cap,new ArrayList<Booking>()),updated);
        Assertions.assertEquals(handler.getRooms().size(),3);

    }

    /**
     * Method that checks if checkTimeAvailable functions correctly with typical values
     */
    @Test
    public void checkTimeAvailableTypicalValues(){
        Assertions.assertTrue(handler.checkTimeAvailable(timestampAddHr(day,5.5F),timestampAddHr(day,5),timestampAddHr(day,6)));
        Assertions.assertTrue(handler.checkTimeAvailable(timestampAddHr(day,5.5F),timestampAddHr(day,0),timestampAddHr(day,20)));
    }

    /**
     * Method that checks if checkTimeAvailable functions correctly with atypical values
     */
    @Test
    public void checkTimeAvailableAtypicalValues(){
        Assertions.assertFalse(handler.checkTimeAvailable(timestampAddHr(day,2F),timestampAddHr(day,5),timestampAddHr(day,6)));
        Assertions.assertFalse(handler.checkTimeAvailable(timestampAddHr(day,99F),timestampAddHr(day,5),timestampAddHr(day,6)));
        Assertions.assertFalse(handler.checkTimeAvailable(timestampAddHr(day,5),timestampAddHr(day,5),timestampAddHr(day,5)));

    }

    /**
     * Method that checks if CheckRoomTimeAvailable functions correctly
     */
    @Test
    public void checkRoomTimeAvailableCorrect(){

        Assertions.assertTrue(handler.checkRoomTimeAvailable(rooms[0],timestampAddHr(day,11.5F),timestampAddHr(day,12.5F) ));
        studentReservation(11,12);
        Assertions.assertFalse(handler.checkRoomTimeAvailable(rooms[0],timestampAddHr(day,11.5F),timestampAddHr(day,12.5F) ));
        Assertions.assertFalse(handler.checkRoomTimeAvailable(rooms[0],timestampAddHr(day,11),timestampAddHr(day,12) ));
        Assertions.assertFalse(handler.checkRoomTimeAvailable(rooms[0],timestampAddHr(day,10),timestampAddHr(day,13) ));
    }





}
