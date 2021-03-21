package test_suite;

import csc1035.project2.*;
import csc1035.project2.Take;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModuleHandlerTest extends MasterTest{
    ModuleHandler handler;

    @Override
    @BeforeEach
    void setUp(){
        super.setUp();
        handler = new ModuleHandler();
    }

    /**
     * Method that checks whether a correct entry in the Take table is added
     */
    @Test
    public void addStudentToModuleCorrect(){
        handler.addStudentToModule(students[0],modules[0] );
        List<Take> take = controller.getAll(Take.class);

        //checks that there is only 1 entry in that table
        Assertions.assertEquals(1,take.size());
        Assertions.assertEquals(students[0],take.get(0).getId().getSid());
        Assertions.assertEquals(modules[0],take.get(0).getId().getMid());
    }

    /**
     * Method that checks that duplicate entries to the take table are ignored
     */
    @Test
    public void addStudentToModuleDuplicate(){
        handler.addStudentToModule(students[0],modules[0] );
        handler.addStudentToModule(students[0],modules[0] );
        List<Take> take = controller.getAll(Take.class);

        //checks that there is only 1 entry in that table
        Assertions.assertEquals(1,take.size());
        Assertions.assertEquals(students[0],take.get(0).getId().getSid());
        Assertions.assertEquals(modules[0],take.get(0).getId().getMid());
    }

    /**
     * Method that checks if the take table allows a multitude of values
     */
    @Test
    public void addStudentToModuleMultipleEntries(){
        handler.addStudentToModule(students[0],modules[0] );
        handler.addStudentToModule(students[1],modules[0] );

        handler.addStudentToModule(students[0],modules[1] );
        handler.addStudentToModule(students[1],modules[1] );
        List<Take> take = controller.getAll(Take.class);

        //checks that there is 4 entries
        Assertions.assertEquals(4, take.size());


        //checks that all entries present in the collection
        //collection.contains uses equals(), so this should work
        boolean checker = (take.contains(new Take(students[0],modules[0]))&&
                take.contains(new Take(students[0],modules[1]))&&
                take.contains(new Take(students[1],modules[0]))&&
                take.contains(new Take(students[1],modules[1])));

        Assertions.assertTrue(checker);

    }

    /**
     * Method that checks whether a correct entry in the Teach table is added
     */
    @Test
    public void addStaffToModuleCorrect(){
        handler.addStaffToModule(staff[0],modules[0] );
        List<Teach> teach = controller.getAll(Teach.class);

        //checks that there is only 1 entry in that table
        Assertions.assertEquals(1,teach.size());
        Assertions.assertEquals(staff[0],teach.get(0).getId().getSid());
        Assertions.assertEquals(modules[0],teach.get(0).getId().getMid());
    }

    /**
     * Method that checks that duplicate entries to the Teach table are ignored
     */
    @Test
    public void addStaffToModuleDuplicate(){
        handler.addStaffToModule(staff[0],modules[0] );
        handler.addStaffToModule(staff[0],modules[0] );
        List<Teach> teach = controller.getAll(Teach.class);

        //checks that there is only 1 entry in that table
        Assertions.assertEquals(1,teach.size());
        Assertions.assertEquals(staff[0],teach.get(0).getId().getSid());
        Assertions.assertEquals(modules[0],teach.get(0).getId().getMid());
    }

    /**
     * Method that checks if the Teach table allows a multitude of values
     */
    @Test
    public void addStaffToModuleMultipleEntries(){
        handler.addStaffToModule(staff[0],modules[0] );
        handler.addStaffToModule(staff[1],modules[0] );

        handler.addStaffToModule(staff[0],modules[1] );
        handler.addStaffToModule(staff[1],modules[1] );
        List<Teach> teach = controller.getAll(Teach.class);

        //checks that there is 4 entries
        Assertions.assertEquals(4,teach.size());


        //checks that all entries present in the collection
        //collection.contains uses equals(), so this should work
        boolean checker = (teach.contains(new Teach(staff[0],modules[0]))&&
                teach.contains(new Teach(staff[0],modules[1]))&&
                teach.contains(new Teach(staff[1],modules[0]))&&
                teach.contains(new Teach(staff[1],modules[1])));

        Assertions.assertTrue(checker);
    }

    /**
     * Method to check if Remove student from module removes a student
     */
    @Test
    public void removeStudentFromModuleCorrect(){
        handler.addStudentToModule(students[0],modules[0] );
        handler.removeStudentFromModule(students[0],modules[0]);
        List<Take> take = controller.getAll(Take.class);

        Assertions.assertEquals(0,take.size());
    }

    /**
     * Method to check that no change is made if removeStudentFromModule is called with invalid values
     */
    @Test
    public void removeStudentFromModuleInvalid(){
        handler.addStudentToModule(students[0],modules[0] );
        handler.removeStudentFromModule(students[1],modules[0]);
        handler.removeStudentFromModule(students[0],modules[1]);
        handler.removeStudentFromModule(students[1],modules[1]);

        List<Take> take = controller.getAll(Take.class);

        Assertions.assertEquals(1,take.size());
        Assertions.assertEquals(students[0],take.get(0).getId().getSid());
        Assertions.assertEquals(modules[0],take.get(0).getId().getMid());
    }

    /**
     * Method to check if removeStaffFromModule removes staff
     */
    @Test
    public void removeStaffFromModuleCorrect(){
        handler.addStaffToModule(staff[0],modules[0] );
        handler.removeStaffFromModule(staff[0],modules[0]);
        List<Teach> teach = controller.getAll(Teach.class);

        Assertions.assertEquals(0,teach.size());
    }

    /**
     * Method to check that no change is made if removeStaffFromModule is called with invalid values
     */
    @Test
    public void removeStaffFromModuleInvalid(){
        handler.addStaffToModule(staff[0],modules[0] );
        handler.removeStaffFromModule(staff[1],modules[0]);
        handler.removeStaffFromModule(staff[0],modules[1]);
        handler.removeStaffFromModule(staff[1],modules[1]);

        List<Teach> teach = controller.getAll(Teach.class);

        Assertions.assertEquals(1,teach.size());
        Assertions.assertEquals(students[0],teach.get(0).getId().getSid());
        Assertions.assertEquals(modules[0],teach.get(0).getId().getMid());
    }
}
