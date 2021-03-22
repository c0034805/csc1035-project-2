package csc1035.project2;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.*;

/**
 * The main program.
 */
public class MainIO {
    public static void main(String[] args) {
        new MainIO().run();
    }

    public void run() {

        boolean quit = false;
        Scanner sc = new Scanner(System.in);

        while (!quit) {
            menu();

            System.out.println("Please select an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> reserveOptions();
                case 2 -> moduleOptions();
                case 3 -> updateRoom();
                case 4 -> timetableOptions();
                case 5 -> displayOptions();
                case 6 -> {
                    System.out.println("Quitting...");
                    quit = true;
                }
                default -> System.out.println("Not a valid option.");
            }
        }
    }

    private void menu() {
        System.out.println("1: Reservation options.\n" +
                           "2: Module options.\n" +
                           "3: Update a room's details.\n" +
                           "4: Generate timetable for selected modules.\n" +
                           "5: Display options.\n" +
                           "6: Quit."
        );
    }

    private void reserveOptions() {
        boolean quit = false;
        Scanner sc = new Scanner(System.in);

        while(!quit) {
            System.out.println("1: Staff reservation.\n" +
                               "2: Student reservation.\n" +
                               "3: Cancel reservation.\n" +
                               "4: Find available rooms..\n" +
                               "5: Return to main menu.");

            System.out.println("Please select an option:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> reserveStaff();
                case 2 -> reserveStudent();
                case 3 -> cancelBookingReservation();
                case 4 -> displayAvailableRooms();
                case 5 -> {
                    System.out.println("Returning to main menu.");
                    quit = true;
                }
                default -> System.out.println("Not a valid option.");
            }
        }
    }

    private void reserveStaff() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        RoomHandler handler = new RoomHandler();

        System.out.print("Please enter Staff ID: ");
        String sid = sc.nextLine();

        try {
            Staff staff = (Staff) ic.getById(Staff.class, sid);

            System.out.print("Which room would you like to make a reservation in? ");
            String roomNum = sc.nextLine();

            try {
                Room room = (Room) ic.getById(Room.class, roomNum);

                boolean validTime = false;
                while (!validTime) {
                    System.out.println("Format: yyyy-mm-dd hh:mm:ss");

                    System.out.print("I want my reservation to begin at: ");
                    String begin = sc.nextLine();
                    boolean matches = begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    while (!matches) {
                        System.out.print("Invalid time format, please try again:");
                        begin = sc.nextLine();
                        matches = begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    }

                    System.out.print("I want my reservation to end at: ");
                    String end = sc.nextLine();
                    matches = end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    while (!matches) {
                        System.out.print("Invalid time format, please try again:");
                        end = sc.nextLine();
                        matches = end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    }
                    validTime = handler.checkValidTimePeriod( Timestamp.valueOf(begin), Timestamp.valueOf(end) );
                    if (validTime) {
                        String conf = handler.reserveRoomStaff(staff, room, Timestamp.valueOf(begin), Timestamp.valueOf(end));
                        handler.bookingConfirmation("Staff", (Booking) ic.getById(Booking.class, conf));
                    }
                    else {
                        System.out.print("Invalid time period, please try again.\n");
                    }
                }
            }
            catch (NoResultException e) {
                System.out.println("There is no room with the given number.\n");
            }
        }
        catch (NoResultException e) {
            System.out.println("There is no staff member with the given ID.\n");
        }
    }

    private void reserveStudent() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        RoomHandler handler = new RoomHandler();

        System.out.print("Please enter Student ID: ");
        String sid = sc.nextLine();

        try {
            Students student = (Students) ic.getById(Students.class, sid);

            System.out.print("Which room would you like to make a reservation in? ");
            String roomNum = sc.nextLine();

            try {
                Room room = (Room) ic.getById(Room.class, roomNum);

                boolean validTime = false;
                while (!validTime) {
                    System.out.println("Format: yyyy-mm-dd hh:mm:ss");

                    System.out.print("I want my reservation to begin at: ");
                    String begin = sc.nextLine();
                    boolean matches = begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    while (!matches) {
                        System.out.print("Invalid time format, please try again:");
                        begin = sc.nextLine();
                        matches = begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    }

                    System.out.print("I want my reservation to end at: ");
                    String end = sc.nextLine();
                    matches = end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    while (!matches) {
                        System.out.print("Invalid time format, please try again:");
                        end = sc.nextLine();
                        matches = end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                    }
                    validTime = handler.checkValidTimePeriod( Timestamp.valueOf(begin), Timestamp.valueOf(end) );
                    if (validTime) {
                        String conf = handler.reserveRoomStudent(student, room, Timestamp.valueOf(begin), Timestamp.valueOf(end));
                        handler.bookingConfirmation("Student", (Booking) ic.getById(Booking.class, conf));
                    } else {
                        System.out.print("Invalid time period, please try again.\n");
                    }
                }
            }
            catch (NoResultException e) {
                System.out.println("There is no room with the given number.");
            }
        }
        catch (NoResultException e) {
            System.out.println("There is no student with the given ID.");
        }
    }

    private void cancelBookingReservation() {
        IController ic = new Controller<>();
        RoomHandler handler = new RoomHandler();
        Scanner sc = new Scanner(System.in);

        List<StaffBooking> staffBookings = ic.getAll( StaffBooking.class );
        List<StudentBooking> studentBookings = ic.getAll( StudentBooking.class );

        for( StaffBooking s : staffBookings ) {
            System.out.println("Booking ID: " + s.getBooking().getId() + "\n" +
                               "Booker (Staff): " + s.getStaff().getFirstname() + " " +
                                s.getStaff().getLastname() + "\n" +
                               "Room: " + s.getBooking().getRoom().getNum() + "\n" +
                               "Start: " + s.getBooking().getStart() + "\n" +
                               "End: " + s.getBooking().getEnd() + "\n\n");
        }

        for( StudentBooking s : studentBookings ) {
            System.out.println("Booking ID: " + s.getBooking().getId() + "\n" +
                               "Booker (Student): " + s.getStudent().getFirstname() + " " +
                               s.getStudent().getLastname() + "\n" +
                               "Room: " + s.getBooking().getRoom().getNum() + "\n" +
                               "Start: " + s.getBooking().getStart() + "\n" +
                               "End: " + s.getBooking().getEnd() + "\n\n");
        }

        System.out.println("Which booking would you like to cancel?");
        String choice = sc.nextLine();
        try {
            handler.cancelReservation( choice );
            System.out.println("Cancellation successful.\n");
        }
        catch (NoResultException e) {
            System.out.println("There is no booking with the given ID.");
        }
    }

    private void displayAvailableRooms() {
        RoomHandler handler = new RoomHandler();
        Scanner sc = new Scanner(System.in);

        System.out.println("Format: yyyy-mm-dd hh:mm:ss");
        System.out.print("Find available times at: ");
        String t = sc.nextLine();
        boolean matches = t.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
        while (!matches) {
            System.out.print("Invalid time format, please try again:");
            t = sc.nextLine();
            matches = t.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
        }
        List<Room> roomList = handler.getAvailableRooms(Timestamp.valueOf(t));
        System.out.print("Available Rooms: \n\n");
        for ( Room r : roomList ) {
            System.out.print("Room: " + r.getNum() + "\n" +
                             "Room Type: " + r.getType() + "\n" +
                             "Room Capacity: " + r.getCap() + "\n" +
                             "Socially Distant Room Capacity: " + r.getSd_cap() + "\n\n");
        }
    }

    private void updateRoom() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        RoomHandler handler = new RoomHandler();

        System.out.print("Please enter the room's current number: ");
        String roomNum = sc.nextLine();

        try {
            Room room = (Room) ic.getById(Room.class, roomNum);

            System.out.print("Enter new room number: ");
            String newNum = sc.nextLine();

            System.out.print("Enter new room type: ");
            String newType = sc.nextLine();

            System.out.print("Enter new room capacity: ");
            int newCap = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new room capacity with social " +
                    "distancing rules in place: ");
            int newSdCap = Integer.parseInt(sc.nextLine());

            handler.updateRoomDetails(room, newNum, newType, newCap, newSdCap);
        }
        catch (NoResultException e) {
            System.out.println("There is no room with the given number.");
        }
    }

    private void moduleOptions() {
        boolean quit = false;
        Scanner sc = new Scanner(System.in);

        while (!quit) {
            System.out.println(
                    "1: Add student to module.\n" +
                            "2: Remove student from module.\n" +
                            "3: Add teacher to module.\n" +
                            "4: Remove teacher from module.\n" +
                            "5: Return to main menu.\n");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> studentAddModule();
                case 2 -> studentRemoveModule();
                case 3 -> staffAddModule();
                case 4 -> staffRemoveModule();
                case 5 -> {
                    System.out.println("Returning to main menu.");
                    quit = true;
                }
                default -> System.out.println("Not a valid option.");
            }

        }
    }

    private void studentAddModule() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        ModuleHandler handler = new ModuleHandler();

        System.out.print("Please enter Student ID: ");
        String sid = sc.nextLine();
        try {
            Students student = (Students) ic.getById(Students.class, sid);

            System.out.print("Which module would you like the student to take?\n" + "Please enter Module ID:");
            String mid = sc.nextLine();
            try {
                Modules modules = (Modules) ic.getById(Modules.class, mid);
                boolean added = handler.addStudentToModule(student, modules);
                if (added) {
                    System.out.println("Addition successful.\n" +
                            "Student " + student.getId() + " " +
                            "is now taking module " + modules.getId() + ".");
                }
                else {
                    System.out.println("Addition unsuccessful.\n" +
                            "Student " + student.getId() + " " +
                            "is already taking module " + modules.getId() + ".");
                }
            }
            catch (NoResultException e) {
                System.out.println("There is no module with the given ID.");
            }

        }
        catch (NoResultException e) {
            System.out.println("There is no student with the given ID.");
        }
    }

    private void staffAddModule() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        ModuleHandler handler = new ModuleHandler();

        System.out.print("Please enter Staff ID: ");
        String sid = sc.nextLine();
        try {
            Staff staff = (Staff) ic.getById(Staff.class, sid);

            System.out.print("Which module would you like the student to take?\n" + "Please enter Module ID:");
            String mid = sc.nextLine();
            try {
                Modules modules = (Modules) ic.getById(Modules.class, mid);
                boolean added = handler.addStaffToModule(staff, modules);
                if (added) {
                    System.out.println("Addition successful.\n" +
                            "Staff " + staff.getId() + " " +
                            "is now teaching module " + modules.getId() + ".");
                }
                else {
                    System.out.println("Addition unsuccessful.\n" +
                            "Staff " + staff.getId() + " " +
                            "is already teaching module " + modules.getId() + ".");
                }
            }
            catch (NoResultException e) {
                System.out.println("There is no module with the given ID.");
            }

        }
        catch (NoResultException e) {
            System.out.println("There is no staff with the given ID.");
        }
    }

    private void studentRemoveModule() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        ModuleHandler handler = new ModuleHandler();

        System.out.print("Please enter Student ID: ");
        String sid = sc.nextLine();
        try {
            Students student = (Students) ic.getById(Students.class, sid);

            Set<Take> tmp = student.getTakes();
            Set<Modules> modTmp = new HashSet<>();
            for ( Take t : tmp ) {
                modTmp.add ( (Modules) ic.getById( Modules.class,  t.getId().getMid() ) );
            }
            System.out.print("Which of the following modules would you like to quit?\nPlease enter module ID:\n");
            for ( Modules m : modTmp ) {
                System.out.println( m.getId() + " - " + m.getName() );
            }

            String mid = sc.nextLine();
            try {
                Modules modules = (Modules) ic.getById(Modules.class, mid);
                boolean removed = handler.removeStudentFromModule(student, modules);
                if (removed) {
                    System.out.println("Removal successful.");
                }
                else {
                    System.out.println("Student does not take that module.");
                }
            }
            catch (NoResultException e) {
                System.out.println("There is no module with the given ID.");
            }
        }
        catch (NoResultException e) {
            System.out.println("There is no student with the given ID.");
        }
    }

    private void staffRemoveModule() {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        ModuleHandler handler = new ModuleHandler();

        System.out.print("Please enter Staff ID: ");
        String sid = sc.nextLine();
        try {
            Staff staff = (Staff) ic.getById(Staff.class, sid);

            Set<Teach> tmp = staff.getTeaches();
            Set<Modules> modTmp = new HashSet<>();
            for ( Teach t : tmp ) {
                modTmp.add ( (Modules) ic.getById( Modules.class,  t.getId().getMid() ) );
            }
            System.out.print("Which of the following modules would you like to quit?\nPlease enter module ID:\n");
            for ( Modules m : modTmp ) {
                System.out.println( m.getId() + " - " + m.getName() );
            }
            String mid = sc.nextLine();
            try {
                Modules modules = (Modules) ic.getById(Modules.class, mid);
                boolean removed = handler.removeStaffFromModule(staff, modules);
                if (removed) {
                    System.out.println("Removal successful.");
                }
                else {
                    System.out.println("Staff does not teach that module.");
                }
            }
            catch (NoResultException e) {
                System.out.println("There is no module with the given ID.");
            }
        }
        catch (NoResultException e) {
            System.out.println("There is no staff with the given ID.");
        }
    }

    private void timetableOptions() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Timetable Options:");

        boolean quit = false;
        List<String> modules = new ArrayList<>();

        while (!quit) {
            System.out.println("1: Add module to timetable generation.\n" +
                               "2: Completed. Proceed to timetable generation.\n" +
                               "3: Quit timetable generation and return to main menu.\n");

            System.out.println("Please select an option:");
            int selection = Integer.parseInt(sc.nextLine());

            switch (selection) {
                case 1 -> moduleToTimetable(modules);
                case 2 -> {
                    if(modules.isEmpty()){
                        System.out.println("You haven't entered any modules yet.");
                    }
                    else {
                        timetableGeneration(modules);
                        quit = true;
                    }
                }
                case 3 -> {
                    System.out.println("Returning to main menu.");
                    quit = true;
                }
                default -> System.out.println("Not a valid option.");
            }
        }
    }

    private void moduleToTimetable(List<String> modules) {
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();

        System.out.print("Enter module ID: ");
        String moduleID = sc.nextLine();

        try {
            Modules module = (Modules) ic.getById(Modules.class, moduleID);
            modules.add(moduleID);
        } catch (NoResultException e) {
            System.out.println("There is no module with the given ID, " +
                    "returning to timetable options screen.");
        }
    }

    private void timetableGeneration(List<String> modules) {
        Scanner sc = new Scanner(System.in);
        TimetableHandler handler = new TimetableHandler();

        System.out.println("Timetable Generation:");

        boolean quit = false;
        while(!quit) {
            quit = true;

            System.out.println("1: Generate timetable with social distancing rules.\n" +
                               "2: Generate timetable without social distancing rules.\n" +
                               "3: Return to timetable options.\n");

            System.out.println("Please select an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("Generating timetable...");
                    handler.generateSchoolTimetable(true, modules);
                }
                case 2 -> {
                    System.out.println("Generating timetable...");
                    handler.generateSchoolTimetable(false, modules);
                }
                case 3 -> System.out.println("Returning to timetable options.");
                default -> {
                    System.out.println("Not a valid option.");
                    quit = false;
                }
            }
        }
    }

    private void displayOptions(){
        Scanner sc = new Scanner(System.in);

        boolean quit = true;
        while(quit) {
            System.out.println("1: Display all students.\n" +
                               "2: Display all staff.\n" +
                               "3: Display all rooms.\n" +
                               "4: Display all modules.\n" +
                               "5: Display which modules are taken by which students.\n" +
                               "6: Display which modules are taught by which tutors.\n" +
                               "7: Return to main menu.\n");

            System.out.println("Please select an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> displayStudents();
                case 2 -> displayStaff();
                case 3 -> displayRooms();
                case 4 -> displayModules();
                case 5 -> displayTake();
                case 6 -> displayTeach();
                case 7 -> System.out.println("Returning to main menu.");
                default -> {
                    System.out.println("Not a valid option.");
                    quit = false;
                }
            }
        }
    }

    private void displayStudents(){
        IController<Students> ic= new Controller<>();
        List<Students> students =  new ArrayList<>(ic.getAll(Students.class));

        for(Students student: students){
            System.out.println(student);
        }
    }

    private void displayStaff(){
        IController<Staff> ic= new Controller<>();
        List<Staff> staff =  new ArrayList<>(ic.getAll(Staff.class));

        for(Staff tutor: staff){
            System.out.println(tutor);
        }
    }

    private void displayRooms(){
        IController<Room> ic= new Controller<>();
        List<Room> rooms =  new ArrayList<>(ic.getAll(Room.class));

        for(Room room: rooms){
            System.out.println(room);
        }
    }

    private void displayModules(){
        IController<Modules> ic = new Controller<>();
        List<Modules> modules = new ArrayList<>(ic.getAll(Modules.class));

        for(Modules module: modules){
            System.out.println(module);
        }
    }

    private void displayTake(){
        IController<Take> ic = new Controller<>();
        List<Take> takes = new ArrayList<>(ic.getAll((Take.class)));

        for(Take take: takes){
            System.out.println(take);
        }
    }

    private void displayTeach(){
        IController<Teach> ic = new Controller<>();
        List<Teach> teaches = new ArrayList<>(ic.getAll((Teach.class)));

        for(Teach teach: teaches){
            System.out.println(teach);
        }
    }
}
