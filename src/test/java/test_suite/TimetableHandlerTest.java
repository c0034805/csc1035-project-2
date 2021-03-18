package test_suite;

import csc1035.project2.ModuleRequirements;
import csc1035.project2.Modules;
import csc1035.project2.Session;
import csc1035.project2.TimetableHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimetableHandlerTest extends MasterTest{
    TimetableHandler handler;

    @Override
    @BeforeEach
    void setUp(){
        super.setUp();
        handler = new TimetableHandler();
    }

    /**
     * method that creates a list of modules whose size is based on it's parameters,
     * uses the sample data
     * @param size The size of the module list to output
     * @return returns a list of sample modules of the size input
     */
    private List<String> modulesIDList(int size){
        List<Modules> lst = Arrays.asList(modules);
        if (size>lst.size()){
            System.err.println("Requested module list size was greater than the sample data");
        }
        for (int i = 0; i < size; i++) {
            lst.remove(lst.size()-1);
        }

        List<String> toReturn = new ArrayList<>();
        for (Modules mod:lst) {
            toReturn.add(mod.getId());
        }
        return toReturn;
    }

    /**
     * Checks wether 2 sessions overlap
     * @param x session1 to check
     * @param y session2 to check
     * @return returns true if they do overlap
     */
    private boolean sessionOverlap(Session x, Session y){
        //if in different rooms or on a different day, return false
        if (x.getRoom() != y.getRoom() || x.getDay_of_week() != y.getDay_of_week()){
            return false;
        }
        int xlength = x.getEnd()-x.getStart();
        int ylength = y.getEnd()-y.getStart();

        return (x.getStart() <= y.getEnd()) && (x.getEnd() >= y.getStart());

    }

    /**
     * Method that checks whether generateSchoolTimetable returns the correct size with 1 module
     */
    @Test
    public void generateSchoolTimetableSizeSingle(){
        List<Session> sessions = handler.generateSchoolTimetable(false,modulesIDList(1));
        ModuleRequirements req = (ModuleRequirements) controller.getById(ModuleRequirements.class,modules[0].getId());
        int requiredSessions = req.getLectures() +req.getPracticals();

        Assertions.assertEquals(requiredSessions,sessions.size());
    }

    /**
     * Method that checks whether generateSchoolTimetable returns the correct size with multiple modules
     */
    @Test
    public void generateSchoolTimetableSizeMultiple(){
        List<Session> sessions = handler.generateSchoolTimetable(false,modulesIDList(3));

        int requiredSessions = 0;
        for (Modules mod:modules) {
            ModuleRequirements req = mod.getModuleRequirements();
            requiredSessions += req.getLectures() + req.getPracticals();
        }

        Assertions.assertEquals(requiredSessions,sessions.size());
    }

    /**
     * Method that checks whether generateSchoolTimetable returns the correct length of time for sessions
     */
    @Test
    public void generateSchoolTimetableSessionLength(){
        List<Session> sessions = handler.generateSchoolTimetable(false,modulesIDList(1));
        ModuleRequirements req = (ModuleRequirements) controller.getById(ModuleRequirements.class,modules[0].getId());
        List<Integer> lengths = new ArrayList<>();

        //adds the required session lengths to the lengths list
        for (int i = 0; i < req.getPracticals(); i++) {
            lengths.add(req.getPlength());
        }
        for (int i = 0; i < req.getLectures(); i++) {
            lengths.add(req.getLlength());
        }

        //removes lengths from the lengths list if a session length matches it
        for (Session sesh:sessions) {
            lengths.remove(Integer.valueOf(sesh.getEnd()-sesh.getStart()));
        }

        Assertions.assertEquals(0,lengths.size());

    }

    /**
     * Method that checks whether generateSchoolTimetable does not produce any conflicting sessions
     */
    @Test
    public void generateSchoolTimetableNoConflicts(){
        //because I don't have enough sample data, I'm gonna put each Module into the list multiple times
        //which could lead to some problems, if it does, can just add more sample data in MasterTest
        List<String> idList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            idList.addAll(modulesIDList(Integer.MAX_VALUE));
        }
        List<Session> sessions = handler.generateSchoolTimetable(true,modulesIDList(1));
        boolean checker = true;

        for (Session sesh:sessions) {

            for (Session sesh1:sessions) {
                if (sessionOverlap(sesh,sesh1)){
                    checker = false;
                }
            }
        }
        Assertions.assertTrue(checker);
    }


    /**
     * Method that checks that all sessions created by generateSchoolTimetable are within capacity
     * Not socially distant
     */
    @Test
    public void generateSchoolTimetableCapacityCorrect(){
        List<Session> sessions = handler.generateSchoolTimetable(false,modulesIDList(1));
        ModuleRequirements req = (ModuleRequirements) controller.getById(ModuleRequirements.class,modules[0].getId());
        boolean checker = true;
        for (Session sesh:sessions) {
            if(sesh.getRoom().getCap() < sesh.getStudents().size()){
                checker = false;
            }
        }
        Assertions.assertTrue(checker);
    }

    /**
     * Method that checks that all sessions created by generateSchoolTimetable are within capacity
     * socially distant
     */
    @Test
    public void generateSchoolTimetableSDCapacityCorrect(){
        List<Session> sessions = handler.generateSchoolTimetable(true,modulesIDList(1));
        ModuleRequirements req = (ModuleRequirements) controller.getById(ModuleRequirements.class,modules[0].getId());
        boolean checker = true;
        for (Session sesh:sessions) {
            if(sesh.getRoom().getSd_cap() < sesh.getStudents().size()){
                checker = false;
            }
        }
        Assertions.assertTrue(checker);
    }

}
