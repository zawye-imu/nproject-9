package com.project.group9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Integration testing especially the integration of database and application
public class AppIntegrationTest
{
    static App app;

    //Init function to do all the work before any other work
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }
    //Testing if the program reacts correctly if it connects to a invalid location
    @Test
    void testingInvalidLocation()
    {
        //Testing the invalid input for database
        app.connect("localhost:20001");

    }
    //Testing the disconnecting function of the database
    @Test
    void testDisconnect()
    {
        app.disconnect();
    }


}