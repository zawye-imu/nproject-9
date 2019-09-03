package com.project.group9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }
    @Test
    void functionCalls()
    {

        //Testing if the whole app works
        String test[]={};
        app.main(test);
    }
    @Test
    void testDisconnect()
    {
        app.disconnect();
    }
}