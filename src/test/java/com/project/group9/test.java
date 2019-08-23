package com.project.group9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class test
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }
    @Test
    void TestCalPopulationtoNull()
    {
        //Testing for null input
        app.calPopulation(null);

        //Testing for invalid input
        app.calPopulation("Invalid");

        //Testing with real input
        app.calPopulation("Continent");
    }
    @Test
    void TestCalPopulation2()
    {
        //Testing for null input
        app.calPopulation2(null);

        //Testing for invalid input
        app.calPopulation2("Invalid");

        //Testing with real input
        app.calPopulation2("Continent");
    }
    @Test
    void functionCalls()
    {

        app.cityP();
        app.citiesInDistrict();
        app.citiesInCountry();
        app.citiesInCont();
        app.countriesInRegion();

        //Countries function test
        app.countryP();

        app.countriesInRegion();
        app.countriesInCont();
        app.capitalContinent();
        app.capitalRegion();
        app.capitalP();

    }





    
}