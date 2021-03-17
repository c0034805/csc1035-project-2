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
                case 5 -> {
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
                           "5: Quit."
        );
    }

    private void reserveOptions() {
        boolean quit = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("1: Staff reservation.\n" +
                           "2: Student reservation.\n" +
                           "3: Return to main menu.");

        System.out.println("Please select an option:");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> reserveStaff();
            case 2 -> reserveStudent();
            case 3 -> {
                System.out.println("Returning to main menu.");
            }
            default -> System.out.println("Not a valid option.");
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

                String conf = handler.reserveRoomStaff(staff, room, Timestamp.valueOf(begin), Timestamp.valueOf(end));
                handler.bookingConfirmation("Staff", (Booking) ic.getById(Booking.class, conf));
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

                String conf = handler.reserveRoomStudent(student, room, Timestamp.valueOf(begin), Timestamp.valueOf(end));
                handler.bookingConfirmation("Student", (Booking) ic.getById(Booking.class, conf));
            }
            catch (NoResultException e) {
                System.out.println("There is no room with the given number.");
            }
        }
        catch (NoResultException e) {
            System.out.println("There is no student with the given ID.");
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

    // TODO: Check if updating handler is faster than creating new instances each option select.
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

            System.out.print("Which of the following modules would you like to quit?\nPlease enter module ID:");
            for (Take t : student.getTake()) {
                Modules tmpModule = (Modules) ic.getById(Modules.class, t.getMid());
                System.out.println(tmpModule.getId() + " - " + tmpModule.getName());
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

            System.out.print("Which of the following modules would you like to stop teaching?\n" +
                    "Please enter module ID:");
            for (Teach t : staff.getTeach()){
                Modules tmpModule = (Modules) ic.getById(Modules.class, t.getMid());
                System.out.println(tmpModule.getId() + " - " + tmpModule.getName());
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
                    timetableGeneration(modules);
                    quit = true;
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
}
