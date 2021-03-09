package test_suite;

import csc1035.project2.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


public class RoomHandlerTest {
    RoomHandler handler;
    IController controller;
    Modules[] modules = {new Modules("RSI3393","Nunc nisl.",20,12),
    new Modules("BBU5808","Integer pede justo lacinia eget tincidunt eget tempus vel pede.",10,12),
    new Modules("SAM0176","Cras in purus eu magna vulputate luctus.",10,6)};

    Room[] rooms = {new Room("0.379","PC Cluster",157,36),
    new Room("0.365","PC Cluster",59,9),
    new Room("1.846","Lecture Lecture",274,50)};

    Staff[] staff = {new Staff("NUC3292317","Jacenta","Khomich"),
    new Staff("NUC9674902","Carmita","Cogzell"),
    new Staff("NUC7362101","Swen","Geard")};

    Students[] students = {new Students("216906208","Doralynn","Bordman"),
    new Students("218577635","Farra","Pietroni"),
    new Students("216365117","Clareta","Osmint")};

    ModuleRequirements[] requirements = {new ModuleRequirements("RSI3393",1,2,1,2),
    new ModuleRequirements("BBU5808",1,2,2,2),
    new ModuleRequirements("SAM0176",3,1,4,1)};

    Timestamp day = new Timestamp(2021, 6, 22,
            0, 0,0,0);

    @BeforeEach
    void setUp() {
        handler = new RoomHandler();
        controller = new Controller();
        fillDBSample();
    }

    void deleteAll(){
        controller.deleteAll(Modules.class);
        controller.deleteAll(Room.class);
        controller.deleteAll(Staff.class);
        controller.deleteAll(Students.class);
        controller.deleteAll(ModuleRequirements.class);
    }

    /**
     * Fills the DB with sample data
     */
    void fillDBSample(){
        deleteAll();
        controller.bulkListSave(Arrays.asList(modules));
        controller.bulkListSave(Arrays.asList(rooms));
        controller.bulkListSave(Arrays.asList(staff));
        controller.bulkListSave(Arrays.asList(students));
        controller.bulkListSave(Arrays.asList(requirements));
    }

    public Timestamp timestampAddHr(Timestamp stmp, long hr){

        hr *= 3600000;
        return new Timestamp(stmp.getTime()+hr);
    }

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
        Assertions.assertEquals(sb.getSid(),students[0].getId());
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
//    @Test
//   public void getReservedRoomsReturnsCorrectSize(){
//       studentReservation(11,12);
//       Timestamp middle = new Timestamp(2021, 6, 22,
//               11, 30,0,0);
//       Timestamp after = new Timestamp(2999, 6, 22,
//               11, 30,0,0);

//       Assertions.assertEquals(1,handler.getReservedRooms(middle).size());

//       //just a couple years after that reservation
//       Assertions.assertEquals(1,handler.getReservedRooms(after).size());

//   }

//   /**
//    * Method that checks if getAvailableRooms return the correct number of Reservations
//    */
//   @Test
//   public void getAvailableRoomsReturnsCorrectSize(){
//       studentReservation(11,12);
//       Timestamp middle = new Timestamp(2021, 6, 22,
//               11, 30,0,0);
//       Timestamp after = new Timestamp(2999, 6, 22,
//               11, 30,0,0);

//       int difference = handler.getReservedRooms(middle).size() - handler.getReservedRooms(after).size();

//       //checks that the available list size after the reservations is the same as the room list size
//       Assertions.assertEquals(controller.getAll(Room.class).size(),handler.getAvailableRooms(after).size());
//       Assertions.assertEquals(1,difference);

//   }





}
