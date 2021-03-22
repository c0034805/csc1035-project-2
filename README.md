CSC1035 Project 2 (Team Project)
================================

This package is built as a part of CSC1035: Portfolio-2.

Run this program to interact with the Room Booking database and 
generate timetables.

Upon running the program, you will be brought to the main menu.

##The main menu lists the following options:
> ###1. Reservation options.
>> Selecting this option will direct you to a different menu with the
>> following options:
>>>####1. Staff reservation.
>>>> Select this option to reserve a room for a staff member. You will
>>>> need to input a valid Staff ID and room number, as well as a begin
>>>> and end date and time for your reservation that doesn't collide
>>>> with another reservation (this will be checked by the system) in
>>>> the format ***yyyy-mm-dd hh:mm:ss***. If your booking is
>>>> successful, you will receive a booking confirmation which includes
>>>> your Booking ID.
>>>####2. Student reservation.
>>>> Select this option to reserve a room for a student. You will
>>>> need to input a valid Staff ID and room number, as well as a begin
>>>> and end date and time for your reservation that doesn't collide
>>>> with another reservation (this will be checked by the system) in
>>>> the format ***yyyy-mm-dd hh:mm:ss***. If your booking is
>>>> successful, you will receive a booking confirmation which includes
>>>> your Booking ID.
>>>####3. Cancel reservation.
>>>> Select this option to cancel a reservation. All staff and student
>>>> bookings will appear on your screen and you will be asked which
>>>> you wish to cancel. Enter the Booking ID of the booking you would
>>>> like to cancel.
>>>####4. Find available rooms.
>>>> Select this option to get all available rooms for a day of your
>>>> choice.
>>>####5. Return to main menu.
>>>> Select this option to return to the main menu.

> ###2. Module options.
>> Selecting this option will direct you to a different menu with the
>> following options:
>>> ####1. Add student to module.
>>>> Select this option to register a student as taking a module. You
>>>> will need to enter a valid Student and Module ID.
>>> ####2. Remove student from module.
>>>> Select this option to delist a student from a module. You
>>>> will need to enter a valid Student and Module ID.
>>> ####3. Add teacher to module.
>>>> Select this option to register a staff member as teaching a 
>>>> module. You will need  to enter a valid Staff and Module ID.
>>> ####4. Remove teacher from module.
>>>> Select this option to delist a staff member from a module. You
>>>> will need to enter a valid Staff and Module ID.
>>> ####5. Return to main menu.
>>>> Select this option to return to the main menu.

> ###3. Update a room's details.
>> Select this option to update a room's details. You will need to
>> enter the current room number of the room whose details you would
>> like to change. Then you will be asked to enter a new room number,
>> a new room type, a new room capacity and a new room capacity with
>> social distancing rules in effect. If there are any of these you
>> would like to keep the same, just enter the same information as
>> before.

> ###4. Generate timetable for selected modules.
>> Selecting this option will direct you to a different menu with the
>> following options:
>>> ####1. Add module to timetable generation.
>>>> Select this option to add a module to the list of modules you
>>>> would like to generate a timetable for. You will need to enter
>>>> the Module ID.
>>> ####2. Completed. Proceed to timetable generation.
>>>> Selecting this option will direct you to a different menu with the
>>>> following options:
>>>>> #####1. Generate timetable with social distancing rules.
>>>>>> Generate a timetable with the selected modules keeping in mind
>>>>>> there are social distancing rules in effect. Will not work if
>>>>>> you have not added any modules to the list.
>>>>> #####2. Generate timetable without social distancing rules.
>>>>>> Generate a timetable with the selected modules without social
>>>>>> distancing. Will not work if you have not added any modules to
>>>>>> the list.
>>>>> #####3. Return to timetable options.
>>>>>> Return to the previous menu.
>>> ####3. Quit timetable generation and return to main menu.
>>>> Select this option to return to the main menu.

> ###5. Display options.
>> Selecting this option will direct you to a different menu with the
>> following options: 
>>> ####1. Display all students.
>>>> Select this option to display all students in the database.
>>> ####2. Display all staff.
>>>> Select this option to display all staff in the database.
>>> ####3. Display all rooms.
>>>> Select this option to display all rooms in the database.
>>> ####4. Display all modules.
>>>> Select this option to display all modules in the database.
>>> ####5. Display which modules are taken by which students.
>>>> Select this option to display which modules are taken by which
>>>> students (this will display Module ID, and Student ID, not names).
>>> ####6. Display which modules are taught by which tutors.
>>>> Select this option to display which modules are taught by which
>>>> staff members (this will display Module ID, and Staff ID, not 
>>>> names).
>>> ####7. Return to main menu.
>>>> Select this option to return to the main menu. 

> ###6. Quit.
>> Select this option to quit the program.