package test_suite;

import csc1035.project2.ModuleHandler;
import csc1035.project2.RoomHandler;
import org.junit.jupiter.api.BeforeEach;

public class ModuleHandlerTest extends MasterTest{
    ModuleHandler handler;

    @Override
    @BeforeEach
    void setUp(){
        super.setUp();
        handler = new ModuleHandler();
    }


}
