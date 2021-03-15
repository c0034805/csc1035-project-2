# CHANGELOG

##### v0.2 [03.03.2021]: Room Booking Methods
* (ERICK) Created `getReservedRooms()` method.
* (ERICK) Created `getAvailableRooms()` method.
* (ERICK) Booking ID is now auto-generated.
* (ERICK) `getAvailableRooms()` now takes timestamp.
* (ERICK) Created `reserveRoomStudent()` method.
* (ERICK) Created `checkTimeAvailable()` method.
* (ERICK) Added lists for booking relationships.
* (ERICK) Created `refreshRoomHandler()` method.
* (ERICK) Created `cancelReservation()`method.

##### v0.3 [05.03.2021]: 
* (ERICK) Created `checkRoomTimeAvailable()` method.
* (ERICK) `reserveRoomStudent()` returns booking id.
* (ERICK) Created `bookingConfirmation()` method.
* (ERICK) Created `reserveRoomStaff()` method.
* (ERICK) Methods now accommodate string IDs.
* (ERICK) Created `reserveRoomModule()` method.
* (ERICK) Created `isInteger()` method.
* (ERICK) Created `updateRoomDetails()` method.
* `cancelReservation()` takes string instead of int.

##### v0.4 [14.03.2021]: Module Handling Methods
* (ERICK) Added javadoc documentation to RoomHandler.
* (ERICK) Created `ModuleHandler` class.
* (ERICK) Created `refreshModuleHandler()` method.
* (ERICK) Added lists for Take and Teach.
* (ERICK) Created `addStudentToModule()` method.
* (ERICK) Created `addStaffToModule()` method.
* (ERICK) Created `removeStudentFromModule()` method.
* (ERICK) Created `removeStaffFromModule()` method.
* (ERICK) Student-take not created if already exists.
* (ERICK) Staff-teach not created if already exists.

##### v0.5 [15.03.2021]: Module Handling UI
* (ERICK) Created `moduleOptions()` method.
* (ERICK) `addStudent/StaffToModule()` now return booleans.
* (ERICK) Created `studentAddModule()` method.
* (ERICK) Created `staffAddModule()` method.
* (ERICK) Created `studentRemoveModule()` method.
* (ERICK) Created `staffRemoveModule()` method.
* (ERICK) Take/teach removal checks if valid removal.
* (ERICK) Take/teach addition checks correctly.