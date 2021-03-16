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
        Assertions.assertEquals(take.size(),1);
        Assertions.assertEquals(take.get(0).getSid(),students[0].getId());
        Assertions.assertEquals(take.get(0).getMid(),modules[0].getId());
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
        Assertions.assertEquals(take.size(),1);
        Assertions.assertEquals(take.get(0).getSid(),students[0].getId());
        Assertions.assertEquals(take.get(0).getMid(),modules[0].getId());
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
        Assertions.assertEquals(take.size(),4);


        //checks that all entries present in the collection
        //collection.contains uses equals(), so this should work
        boolean checker = (take.contains(new Take(students[0].getId(),modules[0].getId()))&&
                take.contains(new Take(students[0].getId(),modules[1].getId()))&&
                take.contains(new Take(students[1].getId(),modules[0].getId()))&&
                take.contains(new Take(students[1].getId(),modules[1].getId())));

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
        Assertions.assertEquals(teach.size(),1);
        Assertions.assertEquals(teach.get(0).getSid(),staff[0].getId());
        Assertions.assertEquals(teach.get(0).getMid(),modules[0].getId());
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
        Assertions.assertEquals(teach.size(),1);
        Assertions.assertEquals(teach.get(0).getSid(),staff[0].getId());
        Assertions.assertEquals(teach.get(0).getMid(),modules[0].getId());
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
        Assertions.assertEquals(teach.size(),4);


        //checks that all entries present in the collection
        //collection.contains uses equals(), so this should work
        boolean checker = (teach.contains(new Teach(staff[0].getId(),modules[0].getId()))&&
                teach.contains(new Teach(staff[0].getId(),modules[1].getId()))&&
                teach.contains(new Teach(staff[1].getId(),modules[0].getId()))&&
                teach.contains(new Teach(staff[1].getId(),modules[1].getId())));

        Assertions.assertTrue(checker);

    }
}
