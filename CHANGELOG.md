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
* (ERICK) `studentRemoveModule()` makes use of removal check.
* (ERICK) `staffRemoveModule()` makes use of removal check.
* (ERICK)  `menu()` no longer makes use of source 15.
* (ERICK) Added `moduleOptions()` as selectable option.

##### v0.6 [16.03.2021]: Test Feedback 
* (ERICK) Module handler methods use`getModuleStudent/Staff()`.

##### v0.61 [16.03.2021]: Room Booking Fixing
* (ERICK) Booking class stops creating new row.
* (ERICK) Staff, students and modules cascade bookings when deleted.
* (ERICK) Rooms cascade bookings when deleted.

##### v0.62 [17.03.2021]: 
* (ERICK) Created `entityBooking()` method.
* (ERICK) Updated reservation methods according to master branch. 
* (ERICK) Fixed ManyToMany relationships with module association.
* (ERICK) Fixed `addStudent/StaffToModule` according to class change.
* (ERICK) Fixed `removeStudent/StaffFromModule` according to class change.
* (ERICK) Take/Teach make use of embedded ID.
* (ERICK) Added constructor methods to Take and Teach. 
* (ERICK) Added getter/setter methods for Teach/Take in module class.
* (ERICK) Fixed `addStudent/StaffToModule` according to embedded Teach/Take.
* (ERICK) Changed List to Set to avoid `MultipleBagFetchException`.
* (ERICK) Teach/Take fetching set to EAGER.
* (ERICK) Take/Teach creation finally fixed.
* (ERICK) `student/staffRemoveModule` display repositioned.
* (ERICK) Take/Teach removal finally fixed.
* (ERICK) Module handler test updated to match embedded classes.

##### v0.63 [20.03.2021]:
* (ERICK) Reinstated booking table change to get staff reservations to work.
* (ERICK) Updated student class structure to match staff structure.
* (ERICK) Updated module class structure to match staff structure.
* (ERICK) `reserveOptions()` now loops until choosing to exit.
* (ERICK) Module booking is correctly mapped to module.
* (ERICK) Added additional getter/setter methods to booking class.
* (ERICK) `cancelReservation()` now deletes staff/(ect) booking.
* (ERICK) Created `cancelBookingReservation()` method.

##### v0.7 [21.03.2021]: Test Suite Matching
* (ERICK) `getRoomsReturnsAll()` test doesn't test with arrays.
* (ERICK) `timestampAddHr()` disregards nanoseconds.
* (ERICK) Removed hash override in modules class.
* (ERICK) `reserveRoomModuleDetailsCorrect` booking fixed.
* (ERICK) `cancelReservationCancelsAllTypes()` checks corrected.
* (ERICK) `cancelReservation()` catches NoResultException exceptions.
* (ERICK) `getReservedRooms()` method fixed.
* (ERICK) `checkTimeAvailable()` slight javadoc change.
* (ERICK) `checkTimeAvailableTypicalValues()` assertions fixed.
* (ERICK) `checkRoomTimeAvailable()` checking updated.
* (ERICK) `checkTimeAvailableAtypicalValues()` assertions fixed.
* (ERICK) `checkRoomTimeAvailable()` checking further updated.