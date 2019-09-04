package com.project.group9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

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
    void TestingClassCityWorks()
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

        c.toString();


    }
    @Test
    void TestingClassCountryWorks()
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
    @Test
    void TestingLanguage()
    {
        CountryLanguage cl=new CountryLanguage();
        cl.setIsOfficial("T");
        cl.setLanguage("Test");
        cl.setPercentage(5.0f);

        cl.getIsOfficial();
        cl.getLanguage();
        cl.getPercentage();

        //Testing to string
        cl.toString();
    }
    @Test
    void functionCalls(){

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
        app.LanguageReport();



        app.countryP("input","test");


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