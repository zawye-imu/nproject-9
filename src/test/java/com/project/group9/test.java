package com.project.group9;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Test class for unit testing
public class test
{
    static App app;

    //init function to do previous work
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");


    }
    //Testing the population report functions
    @Test
    void testCalPopulationtoNull()
    {
        //Testing for null input
        app.calPopulation(null);

        //Testing for invalid input
        app.calPopulation("Invalid");

        //Testing with real input
        app.calPopulation("Continent");

    }
    //Testing various inputs for population report 2
    @Test
    void testCalPopulation2()
    {
        //Testing for null input
        app.calPopulation2(null);

        //Testing for invalid input
        app.calPopulation2("Invalid");

        //Testing with real input
        app.calPopulation2("Continent");

    }
    //testing the city class
    @Test
    void testingClassCityWorks()
    {
        City c=new City();

        //Testing set

        c.setPopulation(100);
        c.setName("Test");
        c.setDistrict("Test");
        c.setCountryCode("Test");
        c.setID(1);

        //Testing get
        c.getCountryCode();
        c.getDistrict();
        c.getID();
        c.getName();
        c.getPopulation();
        City c1=new City(200,"Test1","Test1","Test1",2);
        c1.toString();


    }
    //Testing the country class
    @Test
    void testingClassCountryWorks()
    {
        Country co=new Country();

        //Testing set
        co.setName("Test");
        co.setPopulation(100);
        co.setName("Test");
        co.setCode("Test");
        co.setCapital(100);
        co.setCode2("Test2");
        co.setContinent("Test");
        co.setGNP(null);
        co.setGNPOld(null);
        co.setGovernmentForm("Test");
        co.setHeadOfState("Test");
        co.setIndepYear(2017);
        co.setLifeExpectancy(null);
        co.setLocalName("Test");

        //Testing get
        co.getName();
        co.getCapital();
        co.getCode();
        co.getCode2();
        co.getContinent();
        co.getGNP();
        co.getGNPOld();
        co.getGovernmentForm();
        co.getHeadOfState();
        co.getIndepYear();
        co.getHeadOfState();
        co.getLifeExpectancy();
        co.getLocalName();
        co.getPopulation();

        //Test string output
        co.toString();



    }
    //Testing the language class
    @Test
    void testingLanguage()
    {
        CountryLanguage cl=new CountryLanguage();
        cl.setIsOfficial("T");
        cl.setLanguage("Test");
        cl.setPercentage(5.0f);

        cl.getIsOfficial();
        cl.getLanguage();
        cl.getPercentage();
        CountryLanguage cl1=new CountryLanguage("T","Test2",8.0f);
        //Testing to string
        cl1.toString();
    }
    //Testing the functions in the program to check if they work correctly
    @Test
    void functionCalls(){


        app.menu();
        app.countryP("all","n");
        app.countriesInCont("all","n");
        app.countriesInRegion("all","n");
        app.cityP("all","n");
        app.citiesInCont("all","n");
        app.citiesInRegion("all","n");
        app.citiesInCountry("all","n");
        app.citiesInDistrict("all","n");
        app.capitalP("all","n");
        app.capitalContinent("all","n");
        app.capitalRegion("all","n");
        app.calPopulation("Continent");
        app.calPopulation("Region");
        app.calPopulation("Name");
        app.calPopulation2("District");
        app.calPopulation2("Name");
        app.languageReport();
        app.countryP("input","test");

        // Input test section
        app.countriesInCont("input","test");
        app.countriesInRegion("input","test");
        app.cityP("input","test");
        app.citiesInCont("input","test");
        app.citiesInRegion("input","test");
        app.citiesInCountry("input","test");
        app.citiesInDistrict("input","test");
        app.capitalP("input","test");
        app.capitalContinent("input","test");
        app.capitalRegion("input","test");
    }



}