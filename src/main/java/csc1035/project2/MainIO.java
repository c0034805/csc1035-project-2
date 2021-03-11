package csc1035.project2;

import java.sql.Timestamp;
import java.util.Scanner;

public class MainIO {
    public static void main(String[] args) {
        new MainIO().run();
    }

    public void run(){

        boolean quit = false;
        Scanner sc = new Scanner(System.in);

        while(!quit){
            menu();

            System.out.println("Please select an option: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1 -> reserveStaff();
                case 2 -> reserveStudent();
                case 3 -> {
                    System.out.println("Quitting...");
                    quit = true;
                }
                default -> System.out.println("Not a valid option.");
            }
        }
    }

    private void menu(){
        System.out.println("""
              1: Create a staff booking.
              2: Create a student booking.
              3: Quit.
              """);
    }

    private void reserveStaff(){
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        RoomHandler handler = new RoomHandler();

        System.out.print("Please enter Staff ID: ");
        String sid = sc.nextLine();

        try{
            Staff staff =  (Staff) ic.getById(Staff.class, sid);

            System.out.print("Which room would you like to make a reservation in? ");
            String roomNum = sc.nextLine();

            try{
                Room room = (Room) ic.getById(Room.class, roomNum);

                System.out.println("Format: yyyy-mm-dd hh:mm:ss");

                System.out.print("I want my reservation to begin at: ");
                String begin = sc.nextLine();
                boolean matches =begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                while(!matches){
                    System.out.print("Invalid time format, please try again:");
                    begin = sc.nextLine();
                    matches =begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                }

                System.out.print("I want my reservation to end at: ");
                String end = sc.nextLine();
                matches =end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                while(!matches){
                    System.out.print("Invalid time format, please try again:");
                    end = sc.nextLine();
                    matches =end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                }

                handler.reserveRoomStaff(staff, room, Timestamp.valueOf(begin), Timestamp.valueOf(end));
            }
            catch(NullPointerException e){
                System.out.println("There is no room with the given number.");
            }
        }
        catch (NullPointerException e){
            System.out.println("There is no staff member with the given ID.");
        }
    }

    private void reserveStudent(){
        Scanner sc = new Scanner(System.in);
        IController ic = new Controller<>();
        RoomHandler handler = new RoomHandler();

        System.out.print("Please enter Student ID: ");
        String sid = sc.nextLine();

        try{
            Students student =  (Students) ic.getById(Students.class, sid);

            System.out.print("Which room would you like to make a reservation in? ");
            String roomNum = sc.nextLine();

            try{
                Room room = (Room) ic.getById(Room.class, roomNum);

                System.out.println("Format: yyyy-mm-dd hh:mm:ss");

                System.out.print("I want my reservation to begin at: ");
                String begin = sc.nextLine();
                boolean matches =begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                while(!matches){
                    System.out.print("Invalid time format, please try again:");
                    begin = sc.nextLine();
                    matches =begin.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                }

                System.out.print("I want my reservation to end at: ");
                String end = sc.nextLine();
                matches =end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                while(!matches){
                    System.out.print("Invalid time format, please try again:");
                    end = sc.nextLine();
                    matches =end.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d\\ \\d\\d\\:\\d\\d\\:\\d\\d");
                }

                handler.reserveRoomStudent(student, room, Timestamp.valueOf(begin), Timestamp.valueOf(end));
            }
            catch(NullPointerException e){
                System.out.println("There is no room with the given number.");
            }
        }
        catch (NullPointerException e){
            System.out.println("There is no student with the given ID.");
        }
    }
}
