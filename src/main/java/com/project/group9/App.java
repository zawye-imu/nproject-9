/**
 **********************************************************************
 * Copyright (c) 2019 Group9
 *
 * All rights reserved. Do not copy or adapt from this
 * code without getting permission from us...
 *
 * App: World Population System
 *
 * Author: Group9 - DevOPs
 * Date: 23/8/2019
 **********************************************************************
 */
package com.project.group9;
//These following section will include libraries for the app

import org.junit.jupiter.api.BeforeAll;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    static App a;

    @BeforeAll


    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< World cities, countries and population report >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");



        // Connect to database

        if (args.length < 1)
        {
            a.connect("localhost:33060");
        }
        else
        {
            a.connect(args[0]);
        }




        //Menu to look at
        a.menu();
        //Menu System for navigating
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the options to generate report....");
        int s = in.nextInt();
        while (s!=0) {
            //Case for options
            switch (s) {
                case 1:
                    a.countryP("all","n");
                    break;
                case 2:
                    a.countriesInCont("all","n");
                    break;
                case 3:
                    a.countriesInRegion("all","n");
                    break;
                case 4:
                    a.cityP("all","n");
                    break;
                case 5:
                    a.citiesInCont("all","n");
                    break;
                case 6:
                    a.citiesInRegion("all","n");
                    break;
                case 7:
                    a.citiesInCountry("all","n");
                    break;
                case 8:
                    a.citiesInDistrict("all","n");
                    break;
                case 9:
                    a.capitalP("all","n");
                    break;
                case 10:
                    a.capitalContinent("all","n");
                    break;
                case 11:
                    a.capitalRegion("all","n");
                    break;
                case 12:
                    a.calPopulation("Continent");
                    break;
                case 13:
                    a.calPopulation("Region");
                    break;
                case 14:
                    a.calPopulation("Name");
                    break;
                case 15:
                    a.calPopulation2("District");
                    break;
                case 16:
                    a.calPopulation2("Name");
                    break;
                case 17:
                    a.languageReport();
                    break;
                case 18:
                    a.countryP("input","n");
                    break;
                case 19:
                    a.countriesInCont("input","n");
                    break;
                case 20:
                    a.countriesInRegion("input","n");
                    break;
                case 21:
                    a.cityP("input","n");
                    break;
                case 22:
                    a.citiesInCont("input","n");
                    break;
                case 23:
                    a.citiesInRegion("input","n");
                    break;
                case 24:
                    a.citiesInCountry("input","n");
                    break;
                case 25:
                    a.citiesInDistrict("input","n");
                    break;
                case 26:
                    a.capitalP("input","n");
                    break;
                case 27:
                    a.capitalContinent("input","n");
                    break;
                case 28:
                    a.capitalRegion("input","n");
                    break;
                default:
                    System.out.println("Please enter the correct input");
                    break;
            }
            a.menu();
            System.out.print("Enter the options to generate report.....Press 0 for exit");
            s = in.nextInt();
        }



//        //all the countries sorted by population
//        a.countryP("all");
//        //countries sorted by continent
//        a.countriesInCont("all");
//        //countries sorted by region
//        a.countriesInRegion("all");


//        //Sorting city population from largest to smallest
//        a.cityP("all");
//        //cities sorted by continent
//        a.citiesInCont("all");
//        //cities sorted by region
//        a.citiesInRegion("all");
//        //cities in the country sorted by population
//        a.citiesInCountry("all");
//        //cities in each district
//        a.citiesInDistrict("all");
//
//        //capital cities in the world sorted by population
//        a.capitalP("all");
//        //capital cities in each continent
//        a.capitalContinent("all");
//        //capital cities in each region
//        a.capitalRegion("all");
//
//
//
////        Population report for each continent
//        a.calPopulation("Continent");
////        Population report for each region
//        a.calPopulation("Region");
////        Population report for each country
//        a.calPopulation("Name");
//
////        population report for each district
//        a.calPopulation2("District");
////        Population report for each city
//        a.calPopulation2("Name");
//
//
//
//        //Getting the language report
//       a.LanguageReport();
//
//
//
//        //Getting Input from the user section
//
//        //Getting countries with user input
//
//        a.countryP("input");
//        a.countriesInCont("input");
//        a.countriesInRegion("input");
//        a.cityP("input");
//        a.citiesInCont("input");
//        a.citiesInRegion("input");
//        a.citiesInCountry("input");
//        a.citiesInDistrict("input");
//        a.capitalP("input");
//        a.capitalContinent("input");
//        a.capitalRegion("input");










        a.disconnect();
        System.exit(0);
    }


     // Connection to MySQL database.

    private Connection con;


     //Connect to the MySQL database.

    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 5;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public void menu()
    {
        System.out.println("\nChoose options below to generate respective reports!!!!!\n1...All the countries in the world.\n2...All the countries in a continent.\n3...All the countries in a region."
        +"\n4...All the cities in the world.\n5...All the cities in continents.\n6...All the cities in regions.\n7...All the cities in countries.\n8...All the cities in districts.\n9...All the capital cities in the world."
        +"\n10...All the capital cities in continents.\n11...All the capital cities in regions.\n12...Population report(continent)\n13...Population report(Region)\n14...Population report(Country)"
        +"\n15...Population report(district)\n16...Population report(city)\n17...Language report\n18...Country report with input\n19...Country in continent report with input\n20...Country in region report with input"
        +"\n21...City report with input\n22...Cities in continent report with input\n23...Cities in region report with input\n24...Cities in country report with input\n25...Cities in district report with input"
        +"\n26...Capital city report with input\n27...Capital city in continent with input\n28...Capital city in region with input\n");

    }




    public void cityP(String stop,String mode)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM city ORDER BY Population DESC";
            // Execute SQL statement

            ResultSet rset = stmt.executeQuery(strSelect);

            //testing
            System.out.println(rset);


            // Return new employee if valid.
            // Check one is returned
            System.out.println("+++++++++++++++++++++++++++++++++++++++++Cities in the world from largest to smallest population+++++++++++++++++++++++++++++++++++++++++++");

            if(stop=="all")
            {
                while (rset.next())
                {


                    City c=new City();
                    c.setID(rset.getInt("ID"));
                    c.setName(rset.getString("Name"));

                    c.setPopulation(rset.getInt("Population"));
                    c.setDistrict(rset.getString("District"));


                    String code=rset.getString("CountryCode");
                    //Getting the country name by the country code
                    try {
                        Statement stmtc = con.createStatement();
                        // Create string for SQL statement
                        String strSelectc = "SELECT Name FROM country where Code='"+code+"'";
                        // Execute SQL statement

                        ResultSet rsetc = stmtc.executeQuery(strSelectc);
                        while(rsetc.next())
                        {
                            c.setCountryCode(rsetc.getString(1));
                        }
                    }
                    catch (Exception e1)
                    {
                        System.out.println("Error getting country name from code.\n Error: "+e1);

                    }




                    //Displaying output from the city object
                    System.out.println(" Name: "+c.getName()+"\n Population:"+c.getPopulation()+"\n District:"+c.getDistrict()+"\n Country: "+c.getCountryCode()+"\n");



                }
            }
            else
            {
                int s;

                if(mode=="test")
                {
                    s=2;
                }
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of cities....");
                    s = in.nextInt();
                }



                int i =0;
                while (rset.next() && i<s)
                {


                    City c=new City();
                    c.setID(rset.getInt("ID"));
                    c.setName(rset.getString("Name"));

                    c.setPopulation(rset.getInt("Population"));
                    c.setDistrict(rset.getString("District"));


                    String code=rset.getString("CountryCode");
                    //Getting the country name by the country code
                    try {
                        Statement stmtc = con.createStatement();
                        // Create string for SQL statement
                        String strSelectc = "SELECT Name FROM country where Code='"+code+"'";
                        // Execute SQL statement

                        ResultSet rsetc = stmtc.executeQuery(strSelectc);
                        while(rsetc.next())
                        {
                            c.setCountryCode(rsetc.getString(1));
                        }
                    }
                    catch (Exception e1)
                    {
                        System.out.println("Error getting country name from code.\n Error: "+e1);

                    }




                    //Displaying output from the city object
                    System.out.println(" Name: "+c.getName()+"\n Population:"+c.getPopulation()+"\n District:"+c.getDistrict()+"\n Country: "+c.getCountryCode()+"\n");
                    i++;


                }
            }





        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            System.out.println("Exception");
        }
    }





    public void countryP(String stop,String mode)
    {

        try
        {

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);





            System.out.println("+++++++++++++++++++++++++++++++++++Countries in the world from largest to smallest population++++++++++++++++++++++++++++++++++++++++++++");


            //Delete limitation after the code is finished

            if(stop.toLowerCase()=="all")
            {
                //If the user input is 0 show all countries

                while (rset.next())
                {

                    //This is the OOP output section
                    Country co=new Country();
                    co.setCode(rset.getString("Code"));
                    co.setName(rset.getString("Name"));
                    co.setPopulation(rset.getInt("Population"));
                    co.setContinent(rset.getString("Continent"));
                    co.setRegion(rset.getString("Region"));
                    co.setCapitali(rset.getString(3));

                    //Printing section
                    System.out.println("Code: "+co.getCode()+"\nName: "+co.getName()+"\nPopulation: "+co.getPopulation()+"\nContinent: "+co.getContinent()+"\nRegion: "+co.getRegion()+"\nCapital: "+co.getCapitali()+"\n\n\n");


                }
            }
            else
            {
                int i=0;
                int s;
                if (mode=="test")
                {
                    s=2;
                }
                else
                {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of cities....");
                    s = in.nextInt();
                }







                while (rset.next() && i<s)
                {

                    //This is the OOP output section
                    Country co=new Country();
                    co.setCode(rset.getString("Code"));
                    co.setName(rset.getString("Name"));
                    co.setPopulation(rset.getInt("Population"));
                    co.setContinent(rset.getString("Continent"));
                    co.setRegion(rset.getString("Region"));
                    co.setCapitali(rset.getString(3));

                    //Printing section
                    System.out.println("Code: "+co.getCode()+"\nName: "+co.getName()+"\nPopulation: "+co.getPopulation()+"\nContinent: "+co.getContinent()+"\nRegion: "+co.getRegion()+"\nCapital: "+co.getCapitali()+"\n\n\n");

                    i++;
                }
            }





        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            System.out.println("Exception");
        }
    }



    public void countriesInCont(String stop,String mode)
    {
        try
        {
            // Create an array list
            ArrayList<String> continent = new ArrayList<String>();

            //Add elements to ArrayList
            continent.add("North America");
            continent.add("Asia");
            continent.add("Europe");
            continent.add("Africa");
            continent.add("Oceania");
            continent.add("Antarctica");
            continent.add("South America");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Countries by each continent ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            Enumeration<String> e = Collections.enumeration(continent);

            if(stop=="all") {
                while (e.hasMoreElements()) {
                    String ct = e.nextElement();
                    //testing out
                    System.out.println(ct);

                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Continent='" + ct + "' ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect);

                    System.out.println("+++Countries in " + ct + "+++");

                    while (rset1.next()) {

                        Country co = new Country();
                        co.setCode(rset1.getString("Code"));
                        co.setName(rset1.getString("Name"));
                        co.setPopulation(rset1.getInt("Population"));
                        co.setContinent(rset1.getString("Continent"));
                        co.setRegion(rset1.getString("Region"));
                        co.setCapitali(rset1.getString(3));

                        //Printing section
                        System.out.println("Code: " + co.getCode() + "\nName: " + co.getName() + "\nPopulation: " + co.getPopulation() + "\nContinent: " + co.getContinent() + "\nRegion: " + co.getRegion() + "\nCapital: " + co.getCapitali() + "\n\n\n");


                    }
                    System.out.println("\n\n\n");

                }
            }
            else
            {
                //Section for user input
                System.out.println("Choose the continent (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    System.out.println(no+" -> "+e.nextElement());
                    no++;
                }
                int s1;
                if(mode=="test") {
                            s1=2;
                }else {
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose continent ....");
                    s1 = in1.nextInt();

                }
                String ct=null;
                switch (s1)
                {
                    case 1:
                        ct="North America";
                        break;
                    case 2:
                        ct="Asia";
                        break;
                    case 3:
                        ct="Europe";
                        break;
                    case 4:
                        ct="Africa";
                        break;
                    case 5:
                        ct="Oceania";
                        break;
                    case 6:
                        ct="Antarctica";
                        break;
                    case 7:
                        ct="South America";
                        break;
                    default:
                        System.out.println("Please give the correct input!");
                        break;
                }
                int s;
                if(mode=="test")
                {
                    s=2;
                }else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of countries....");
                     s = in.nextInt();
                }

                int i=0;

                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Continent='"+ct+"' ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect);

                    System.out.println("+++++++++++++++++++++++++++Top Populated Countries in "+ct+"++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    while (rset1.next() && i<s) {

                        Country co=new Country();
                        co.setCode(rset1.getString("Code"));
                        co.setName(rset1.getString("Name"));
                        co.setPopulation(rset1.getInt("Population"));
                        co.setContinent(rset1.getString("Continent"));
                        co.setRegion(rset1.getString("Region"));
                        co.setCapitali(rset1.getString(3));

                        //Printing section
                        System.out.println("Code: "+co.getCode()+"\nName: "+co.getName()+"\nPopulation: "+co.getPopulation()+"\nContinent: "+co.getContinent()+"\nRegion: "+co.getRegion()+"\nCapital: "+co.getCapitali()+"\n\n\n");

                        i++;
                    }
                    System.out.println("\n\n\n");




            }





        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }



    public void countriesInRegion(String stop,String mode)
    {
        try
        {
            // Execute SQL statement
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT Region FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create an array list
            ArrayList<String> region = new ArrayList<String>();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Countries by each region ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            while (rset.next())
            {
                region.add(rset.getString(1));
            }
            List<String> newList = region.stream().distinct().collect(Collectors.toList());



            //Add elements to ArrayList

            Enumeration<String> e = Collections.enumeration(newList);
            Enumeration<String> e1 = Collections.enumeration(newList);

            if (stop=="all") {


                while (e.hasMoreElements()) {
                    String ct = e.nextElement();
                    //testing out
                    System.out.println(ct);

                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect1 = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Region='" + ct + "' ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect1);

                    System.out.println("+++Countries in " + ct + "+++");


                    while (rset1.next()) {

                        Country co = new Country();
                        co.setCode(rset1.getString("Code"));
                        co.setName(rset1.getString("Name"));
                        co.setPopulation(rset1.getInt("Population"));
                        co.setContinent(rset1.getString("Continent"));
                        co.setRegion(rset1.getString("Region"));
                        co.setCapitali(rset1.getString(3));

                        //Printing section
                        System.out.println("Code: " + co.getCode() + "\nName: " + co.getName() + "\nPopulation: " + co.getPopulation() + "\nContinent: " + co.getContinent() + "\nRegion: " + co.getRegion() + "\nCapital: " + co.getCapitali() + "\n\n\n");


                    }
                    System.out.println("\n\n\n");
                }
            }
            else
            {
                //Section for user input
                System.out.println("Choose the region (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    System.out.println(no+" -> "+e.nextElement());
                    no++;
                }
                int s1;
                if(mode=="test")
                {
                        s1=2;
                }
                else {
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose region ....");
                    s1=in1.nextInt();
                }



                String ct=null;
                int rno=1;
                while (e1.hasMoreElements())
                {


                    if(rno==s1)
                    {
                        ct=e1.nextElement();
                        System.out.println("Assigned:"+ct);
                        break;
                    }
                    e1.nextElement();
                    rno++;
                }

                int s;
                if(mode=="test")
                {
                    s=2;
                }
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of countries....");
                    s = in.nextInt();
                }

                int i=0;

                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect1 = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Region='"+ct+"' ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect1);

                    System.out.println("+++Top populated countries in "+ct+"+++");


                    while (rset1.next() && i<s)
                    {

                        Country co=new Country();
                        co.setCode(rset1.getString("Code"));
                        co.setName(rset1.getString("Name"));
                        co.setPopulation(rset1.getInt("Population"));
                        co.setContinent(rset1.getString("Continent"));
                        co.setRegion(rset1.getString("Region"));
                        co.setCapitali(rset1.getString(3));

                        //Printing section
                        System.out.println("Code: "+co.getCode()+"\nName: "+co.getName()+"\nPopulation: "+co.getPopulation()+"\nContinent: "+co.getContinent()+"\nRegion: "+co.getRegion()+"\nCapital: "+co.getCapitali()+"\n\n\n");


                        i++;
                    }
                    System.out.println("\n\n\n");


            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }



    public void citiesInCont(String stop,String mode) {
        try {
            // Create an array list
            ArrayList<String> continent = new ArrayList<String>();

            //Add elements to ArrayList
            continent.add("North America");
            continent.add("Asia");
            continent.add("Europe");
            continent.add("Africa");
            continent.add("Oceania");
            continent.add("Antarctica");
            continent.add("South America");

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Cities by each continent ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            Enumeration<String> e = Collections.enumeration(continent);


            if (stop == "all") {
                while (e.hasMoreElements()) {
                    String ct = e.nextElement();
                    //testing out
                    System.out.println(ct);

                    // Execute SQL statement

                    Statement stmt1 = con.createStatement();
                    String strSelect = "SELECT city.Name,city.Population,city.District,city.CountryCode FROM city INNER JOIN country on city.CountryCode=country.Code WHERE country.Continent='" + ct + "'" + "ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect);

                    System.out.println("+++Cities in " + ct + "+++");

                    while (rset1.next()) {

                        City c = new City();

                        c.setName(rset1.getString("Name"));

                        c.setPopulation(rset1.getInt("Population"));
                        c.setDistrict(rset1.getString("District"));


                        String code = rset1.getString("CountryCode");
                        //Getting the country name by the country code

                        Statement stmtc = con.createStatement();
                        // Create string for SQL statement
                        String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                        // Execute SQL statement

                        ResultSet rsetc = stmtc.executeQuery(strSelectc);
                        while (rsetc.next()) {
                            c.setCountryCode(rsetc.getString(1));
                        }
                        //
                        System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");

                        System.out.println("\n\n\n");

                    }
                }
            }
            else {
                //user input section
                System.out.println("Choose the continent (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    System.out.println(no+" -> "+e.nextElement());
                    no++;
                }
                int s1;
                if(mode=="test")
                {
                    s1=2;
                }
                else {
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose continent ....");
                    s1=in1.nextInt();
                }

                String ct=null;
                switch (s1)
                {
                    case 1:
                        ct="North America";
                        break;
                    case 2:
                        ct="Asia";
                        break;
                    case 3:
                        ct="Europe";
                        break;
                    case 4:
                        ct="Africa";
                        break;
                    case 5:
                        ct="Oceania";
                        break;
                    case 6:
                        ct="Antarctica";
                        break;
                    case 7:
                        ct="South America";
                        break;
                    default:
                        System.out.println("Please give the correct input");
                        break;
                }



                    // Execute SQL statement

                    Statement stmt1 = con.createStatement();
                    String strSelect = "SELECT city.Name,city.Population,city.District,city.CountryCode FROM city INNER JOIN country on city.CountryCode=country.Code WHERE country.Continent='" + ct + "'" + "ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect);

                    System.out.println("+++Cities in " + ct + "+++");
                int s;
                if(mode=="test")
                {
                    s=2;
                }
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of cities....\n");
                    s = in.nextInt();
                }

                int i=0;
                    while (rset1.next() && i<s) {

                        City c = new City();

                        c.setName(rset1.getString("Name"));

                        c.setPopulation(rset1.getInt("Population"));
                        c.setDistrict(rset1.getString("District"));


                        String code = rset1.getString("CountryCode");
                        //Getting the country name by the country code

                        Statement stmtc = con.createStatement();
                        // Create string for SQL statement
                        String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                        // Execute SQL statement

                        ResultSet rsetc = stmtc.executeQuery(strSelectc);
                        while (rsetc.next()) {
                            c.setCountryCode(rsetc.getString(1));
                        }
                        //
                        System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");

                        System.out.println("\n\n");
                        i++;

                    }


                //end of user input section


            }
        }
        catch (Exception e3)
        {
            System.out.println("Error is :"+e3);
        }

            }





    public void citiesInRegion(String stop,String mode) {
        try {


            // Execute SQL statement
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT Region FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create an array list
            ArrayList<String> region = new ArrayList<String>();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Cities by each region ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            while (rset.next()) {
                region.add(rset.getString(1));
            }

            List<String> newList = region.stream().distinct().collect(Collectors.toList());
            //Add elements to ArrayList
            Enumeration<String> e = Collections.enumeration(newList);
            Enumeration<String> e1 = Collections.enumeration(newList);

            if(stop=="all"){
            while (e.hasMoreElements()) {


                String ct = e.nextElement();
                //testing out
                System.out.println(ct);

                // Execute SQL statement
                Statement stmt1 = con.createStatement();
                String strSelect1 = "SELECT city.Name,city.Population,city.District,city.CountryCode FROM city INNER JOIN country on city.CountryCode=country.Code WHERE Region='" + ct + "'" + "ORDER BY Population DESC";
                ResultSet rset1 = stmt1.executeQuery(strSelect1);


                System.out.println("+++Cities in " + ct + "+++");

                while (rset1.next()) {

                    City c = new City();

                    c.setName(rset1.getString("Name"));

                    c.setPopulation(rset1.getInt("Population"));
                    c.setDistrict(rset1.getString("District"));


                    String code = rset1.getString("CountryCode");
                    //Getting the country name by the country code


                    Statement stmtc = con.createStatement();
                    // Create string for SQL statement
                    String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                    // Execute SQL statement

                    ResultSet rsetc = stmtc.executeQuery(strSelectc);
                    while (rsetc.next()) {
                        c.setCountryCode(rsetc.getString(1));
                    }
                    //
                    System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");


                    System.out.println("\n\n\n");
                }


            }
        }else
        {
            //User Input Section
            System.out.println("Choose the region (by typing in the respective number)...");
            int no=1;
            while (e.hasMoreElements())
            {
                System.out.println(no+" -> "+e.nextElement());
                no++;
            }

            int s1;
            if(mode=="test")
            {
                s1=2;
            }
            else {
                Scanner in1 = new Scanner(System.in);
                System.out.print("Choose region ....");
                s1=in1.nextInt();
            }



            String ct=null;
            int rno=1;


            while (e1.hasMoreElements())
            {


                if(rno==s1)
                {
                    ct=e1.nextElement();
                    System.out.println("Assigned:"+ct);
                    break;
                }
                e1.nextElement();
                rno++;
            }

            int s;
            if(mode=="test")
            {
                s=2;
            }
            else
            {
                Scanner in = new Scanner(System.in);
                System.out.print("Enter the number of cities....");
                s = in.nextInt();
            }

            int i=0;





                // Execute SQL statement
                Statement stmt1 = con.createStatement();
                String strSelect1 = "SELECT city.Name,city.Population,city.District,city.CountryCode FROM city INNER JOIN country on city.CountryCode=country.Code WHERE Region='" + ct + "'" + "ORDER BY Population DESC";
                ResultSet rset1 = stmt1.executeQuery(strSelect1);


                System.out.println("+++Cities in " + ct + "+++");

                while (rset1.next() && i<s) {

                    City c = new City();

                    c.setName(rset1.getString("Name"));

                    c.setPopulation(rset1.getInt("Population"));
                    c.setDistrict(rset1.getString("District"));


                    String code = rset1.getString("CountryCode");
                    //Getting the country name by the country code


                    Statement stmtc = con.createStatement();
                    // Create string for SQL statement
                    String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                    // Execute SQL statement

                    ResultSet rsetc = stmtc.executeQuery(strSelectc);
                    while (rsetc.next()) {
                        c.setCountryCode(rsetc.getString(1));
                    }
                    //
                    System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");


                    System.out.println("\n");
                    i++;
                }



            }


        } catch (Exception e1) {
            System.out.println(e1.getMessage());
            System.out.println(e1);
        }
    }



    public void capitalP(String stop,String mode)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            System.out.println("++++++++++++++++++++++++++++++ Capital cities in the world from largest to smallest population+++++++++++++++++++++++++++++");
            if(stop=="all") {
                while (rset.next()) {
                    City c = new City();
                    c.setName(rset.getString("Name"));
                    c.setPopulation(rset.getInt("Population"));
                    c.setCountryCode(rset.getString(5));
                    System.out.println("Name:" + c.getName() + "\n Country: " + c.getCountryCode() + "\n Population: " + c.getPopulation());


                }
            }else {
                int s;
                if(mode=="test")
                {
                    s=2;
                }
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of cities....");
                    s = in.nextInt();
                }

                int i=0;
                while (rset.next() && i<s)
                {
                    City c=new City();
                    c.setName(rset.getString("Name"));
                    c.setPopulation(rset.getInt("Population"));
                    c.setCountryCode(rset.getString(5));
                    System.out.println("Name:"+c.getName()+"\n Country: "+c.getCountryCode()+"\n Population: "+c.getPopulation());
                    i++;


                }

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }
    }


    public void citiesInCountry(String stop,String mode)
    {
        try
        {
            // Execute SQL statement
            Statement stmt = con.createStatement();
            //Select distinct country codes of countires
            String strSelect = "SELECT DISTINCT Code FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create an array list
            ArrayList<String> codes = new ArrayList<String>();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Cities in each Country ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            while (rset.next())
            {
                codes.add(rset.getString(1));
            }
            //Various country codes
            List<String> newList = codes.stream().distinct().collect(Collectors.toList());
            //Add elements to ArrayList

            Enumeration<String> e = Collections.enumeration(newList);
            Enumeration<String> e1 = Collections.enumeration(newList);


            if(stop=="all") {
                while (e.hasMoreElements()) {
                    String ct = e.nextElement();


                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect1 = "SELECT city.Name,city.Population,city.District FROM city INNER JOIN country on city.CountryCode=country.Code WHERE city.CountryCode='" + ct + "'" + "ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect1);

                    //getting country name from the country code
                    Statement stmt2 = con.createStatement();
                    String strSelect2 = "SELECT Name FROM country WHERE code='" + ct + "'";
                    stmt2.setMaxRows(1);
                    ResultSet rset2 = stmt2.executeQuery(strSelect2);
                    String co = null;
                    while (rset2.next()) {
                        System.out.println("+++Cities in " + rset2.getString(1) + "+++");
                        co = rset2.getString(1);
                    }

                    while (rset1.next()) {

                        City c = new City();

                        c.setName(rset1.getString("Name"));

                        c.setPopulation(rset1.getInt("Population"));
                        c.setDistrict(rset1.getString("District"));


                        c.setCountryCode(co);

                        System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");
                    }
                    System.out.println("\n\n\n");
                }
            }else
            {
                //User input section






                System.out.println("Choose the country (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    Statement stmt2 = con.createStatement();
                    String strSelect2="SELECT Name FROM country WHERE code='"+e.nextElement()+"'";
                    ResultSet rset2=stmt2.executeQuery(strSelect2);
                    while (rset2.next()) {
                        System.out.println(no + " -> " + rset2.getString("Name"));

                    }
                    no++;
                }
                int s1;
                if(mode=="test") {
                        s1=2;
                }else {
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose country ....");
                    s1 = in1.nextInt();

                }

                String ct=null;
                int rno=1;

                while (e1.hasMoreElements())
                {
                    if(rno==s1)
                    {
                        ct=e1.nextElement();
                        System.out.println("Assigned:"+ct);
                        break;
                    }
                    e1.nextElement();
                    rno++;
                }
                int s;
                if(mode=="test")
                {
                    s=2;
                }else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of cities....");
                    s = in.nextInt();
                }

                int i=0;



                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect1= "SELECT city.Name,city.Population,city.District FROM city INNER JOIN country on city.CountryCode=country.Code WHERE city.CountryCode='"+ct+"'"+"ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect1);

                    //getting country name from the country code
                    Statement stmt2 = con.createStatement();
                    String strSelect2="SELECT Name FROM country WHERE code='"+ct+"'";
                    stmt2.setMaxRows(1);
                    ResultSet rset2=stmt2.executeQuery(strSelect2);
                    String co=null;
                    while (rset2.next()) {
                        System.out.println("+++Cities in " + rset2.getString(1) + "+++");
                        co=rset2.getString(1);
                    }

                    while (rset1.next() && i<s) {

                        City c = new City();

                        c.setName(rset1.getString("Name"));

                        c.setPopulation(rset1.getInt("Population"));
                        c.setDistrict(rset1.getString("District"));


                        c.setCountryCode(co);

                        System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");
                        i++;
                    }
                    System.out.println("\n");
                }



            }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }


    public void citiesInDistrict(String stop,String mode) {
        try {
            // Execute SQL statement
            Statement stmt = con.createStatement();
            //Select distinct country codes of countires
            String strSelect = "SELECT DISTINCT District FROM city";
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create an array list
            ArrayList<String> district = new ArrayList<String>();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Cities in each District ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            while (rset.next()) {
                district.add(rset.getString(1));
            }
            //Various country codes


            List<String> newList = district.stream().distinct().collect(Collectors.toList());


            //Add elements to ArrayList

            Enumeration<String> e = Collections.enumeration(newList);
            Enumeration<String> e1 = Collections.enumeration(newList);

            if (stop == "all") {
                while (e.hasMoreElements()) {
                    String ct = e.nextElement();
                    //testing out
                    System.out.println(ct);

                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect1 = "SELECT city.Name,city.Population,city.District,city.CountryCode FROM city WHERE District='" + ct + "'" + "ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect1);


                    System.out.println("+++Cities in " + ct + "+++");


                    while (rset1.next()) {

                        City c = new City();

                        c.setName(rset1.getString("Name"));

                        c.setPopulation(rset1.getInt("Population"));
                        c.setDistrict(rset1.getString("District"));


                        String code = rset1.getString("CountryCode");
                        //Getting the country name by the country code


                        Statement stmtc = con.createStatement();
                        // Create string for SQL statement
                        String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                        // Execute SQL statement

                        ResultSet rsetc = stmtc.executeQuery(strSelectc);
                        while (rsetc.next()) {
                            c.setCountryCode(rsetc.getString(1));
                        }
                        //
                        System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");

                        System.out.println("\n\n\n");
                    }


                }

            }else
            {
            //User input section
                System.out.println("Choose the district (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    System.out.println(no + " -> " + e.nextElement());
                    no++;
                }
                int s1;
                if(mode=="test")
                {
                    s1=2;
                }
                else {
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose district ....");
                    s1=in1.nextInt();
                }


                String ct=null;
                int rno=1;

                while (e1.hasMoreElements())
                {
                    if(rno==s1)
                    {
                        ct=e1.nextElement();
                        System.out.println("Assigned:"+ct);
                        break;
                    }
                    e1.nextElement();
                    rno++;
                }

                int s;
                if(mode=="test")
                {
                    s=2;
                }
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of cities....");
                    s = in.nextInt();
                }

                int i=0;







                    // Execute SQL statement
                    Statement stmt1 = con.createStatement();
                    String strSelect1 = "SELECT city.Name,city.Population,city.District,city.CountryCode FROM city WHERE District='" + ct + "'" + "ORDER BY Population DESC";
                    ResultSet rset1 = stmt1.executeQuery(strSelect1);


                    System.out.println("+++Cities in " + ct + "+++");


                    while (rset1.next() && i<s) {

                        City c = new City();

                        c.setName(rset1.getString("Name"));

                        c.setPopulation(rset1.getInt("Population"));
                        c.setDistrict(rset1.getString("District"));


                        String code = rset1.getString("CountryCode");
                        //Getting the country name by the country code


                        Statement stmtc = con.createStatement();
                        // Create string for SQL statement
                        String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                        // Execute SQL statement

                        ResultSet rsetc = stmtc.executeQuery(strSelectc);
                        while (rsetc.next()) {
                            c.setCountryCode(rsetc.getString(1));
                        }
                        //
                        System.out.println(" Name: " + c.getName() + "\n Population:" + c.getPopulation() + "\n District:" + c.getDistrict() + "\n Country: " + c.getCountryCode() + "\n");

                        System.out.println("\n");
                        i++;


                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }



    //capital cities in continent
    public void capitalContinent(String stop,String mode)
    {
        try
        {

            // Create an array list of continents
            ArrayList<String> continent = new ArrayList<String>();

            //Add elements to ArrayList
            continent.add("North America");
            continent.add("Asia");
            continent.add("Europe");
            continent.add("Africa");
            continent.add("Oceania");
            continent.add("Antarctica");
            continent.add("South America");
            Enumeration<String> e = Collections.enumeration(continent);



            System.out.println("++++++++++++++++++++++++++++++ Capital cities in each continent +++++++++++++++++++++++++++++");



            if(stop=="all") {
                while (e.hasMoreElements()) {

                    String ct = e.nextElement();
                    // Create an SQL statement
                    Statement stmt = con.createStatement();
                    // Create string for SQL statement
                    String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID WHERE country.continent='" + ct + "'ORDER BY city.Population DESC";
                    // Execute SQL statement
                    ResultSet rset = stmt.executeQuery(strSelect);
                    System.out.println("Capital cities in  " + ct);
                    while (rset.next()) {

                        City c = new City();
                        c.setName(rset.getString("Name"));
                        c.setPopulation(rset.getInt("Population"));
                        c.setCountryCode(rset.getString(5));
                        System.out.println("Name:" + c.getName() + "\n Country: " + c.getCountryCode() + "\n Population: " + c.getPopulation());


                    }
                    System.out.println("\n");
                }
            }else {
                //User input section
                System.out.println("Choose the continent (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    System.out.println(no+" -> "+e.nextElement());
                    no++;
                }
                int s1;
                if(mode=="test")
                {
                    s1=2;

                }else
                    {
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose continent ....");
                    s1=in1.nextInt();
                }


                String ct=null;
                switch (s1)
                {
                    case 1:
                        ct="North America";
                        break;
                    case 2:
                        ct="Asia";
                        break;
                    case 3:
                        ct="Europe";
                        break;
                    case 4:
                        ct="Africa";
                        break;
                    case 5:
                        ct="Oceania";
                        break;
                    case 6:
                        ct="Antarctica";
                        break;
                    case 7:
                        ct="South America";
                        break;
                    default:
                        System.out.println("Please give the correct input.");
                        break;
                }



                    // Create an SQL statement
                    Statement stmt = con.createStatement();
                    // Create string for SQL statement
                    String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID WHERE country.continent='" + ct + "'ORDER BY city.Population DESC";
                    // Execute SQL statement
                    ResultSet rset = stmt.executeQuery(strSelect);
                    System.out.println("Capital cities in  " + ct);
                    int s;
                    if(mode=="test")
                    {
                        s=2;
                    }else {
                        Scanner in = new Scanner(System.in);
                        System.out.print("Enter the number of cities....");
                        s = in.nextInt();

                    }

                    int i=0;
                    while (rset.next() && i<s) {

                        City c = new City();
                        c.setName(rset.getString("Name"));
                        c.setPopulation(rset.getInt("Population"));
                        c.setCountryCode(rset.getString(5));
                        System.out.println("Name:" + c.getName() + "\n Country: " + c.getCountryCode() + "\n Population: " + c.getPopulation());
                        i++;

                    }
                    System.out.println("\n");
                }



        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }
    }

    //capital cities in Region
    public void capitalRegion(String stop,String mode)
    {
        try
        {

            Statement stmt1 = con.createStatement();
            String strSelect1 = "SELECT DISTINCT Region FROM country";
            ResultSet rset1 = stmt1.executeQuery(strSelect1);
            ArrayList<String> region = new ArrayList<String>();
            while (rset1.next())
            {
                region.add(rset1.getString(1));
            }
            System.out.println(region);
            List<String> newList = region.stream().distinct().collect(Collectors.toList());
            System.out.println(newList);
            Enumeration<String> e = Collections.enumeration(newList);
            Enumeration<String> e1 = Collections.enumeration(newList);

            System.out.println("++++++++++++++++++++++++++++++ Capital cities in each region +++++++++++++++++++++++++++++");


            if(stop=="all") {
                while (e.hasMoreElements()) {
                    String ct = e.nextElement();
                    // Create an SQL statement
                    Statement stmt = con.createStatement();
                    // Create string for SQL statement
                    String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Region='" + ct + "'ORDER BY city.Population DESC";
                    // Execute SQL statement
                    ResultSet rset = stmt.executeQuery(strSelect);
                    System.out.println("Capital cities in  " + ct);
                    while (rset.next()) {

                        City c = new City();
                        c.setName(rset.getString("Name"));
                        c.setPopulation(rset.getInt("Population"));
                        c.setCountryCode(rset.getString(5));
                        System.out.println("Name:" + c.getName() + "\n Country: " + c.getCountryCode() + "\n Population: " + c.getPopulation());


                    }
                    System.out.println("\n\n\n");
                }
            }else {

                //User Input Section
                System.out.println("Choose the region (by typing in the respective number)...");
                int no=1;
                while (e.hasMoreElements())
                {
                    System.out.println(no + " -> " + e.nextElement());
                    no++;
                }

                int s1;
                if(mode=="test") {
                        s1=2;
                }
                else {

                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Choose region ....");
                    s1 = in1.nextInt();
                }

                String ct=null;
                int rno=1;

                while (e1.hasMoreElements())
                {
                    if(rno==s1)
                    {
                        ct=e1.nextElement();
                        System.out.println("Assigned:"+ct);
                        break;
                    }
                    e1.nextElement();
                    rno++;
                }

                int s;
                if(mode=="test")
                {
                    s=2;
                }
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the number of capital cities....");
                    s = in.nextInt();
                }

                int i=0;









                    // Create an SQL statement
                    Statement stmt = con.createStatement();
                    // Create string for SQL statement
                    String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Region='"+ct+"'ORDER BY city.Population DESC";
                    // Execute SQL statement
                    ResultSet rset = stmt.executeQuery(strSelect);
                    System.out.println("Capital cities in  "+ct);
                    while (rset.next() && i<s) {

                        City c=new City();
                        c.setName(rset.getString("Name"));
                        c.setPopulation(rset.getInt("Population"));
                        c.setCountryCode(rset.getString(5));
                        System.out.println("Name:"+c.getName()+"\n Country: "+c.getCountryCode()+"\n Population: "+c.getPopulation());
                        i++;

                    }
                    System.out.println("\n\n\n");
                }






        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }
    }


    public void calPopulation(String s)
    {
        try
        {
            Statement stmt1 = con.createStatement();
            String strSelect1 = "SELECT DISTINCT "+s+" FROM country";
            ResultSet rset1 = stmt1.executeQuery(strSelect1);
            ArrayList<String> ls = new ArrayList<String>();
            while (rset1.next())
            {
                ls.add(rset1.getString(1));
            }

            List<String> newList = ls.stream().distinct().collect(Collectors.toList());

            Enumeration<String> e = Collections.enumeration(newList);

            System.out.println("\n\n++++++++++++++++++++++++++++++ Population in countries' "+s+"s +++++++++++++++++++++++++++++\n");

            while (e.hasMoreElements()) {
                String ct=e.nextElement();
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT Population from country where "+s+"='"+ct+"'";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("\n\nPopulation in "+ct+"\n");
                float pop=0;
                while (rset.next()) {

                    pop=pop+rset.getInt(1);


                }
                System.out.println("The population in "+ct+" is "+pop);





                //getting cities in this s

                Statement stmt2 = con.createStatement();
                // Create string for SQL statement
                String strSelect2 = "SELECT city.Name,city.Population FROM city INNER JOIN country on city.CountryCode=country.Code WHERE country."+s+"='"+ct+"'"+"ORDER BY Population DESC";
                // Execute SQL statement
                ResultSet rset2 = stmt2.executeQuery(strSelect2);

                //Getting the population of all the cities
                float cpop=0;
                while (rset2.next())
                {
                    cpop=cpop+rset2.getInt(2);
                }
                System.out.println("Population in cities of "+ct+" ="+cpop);
                float npop=pop-cpop;
                System.out.println("People not living in cities of "+ct+" ="+npop);


                //Setting the variables back to zero
                cpop=0;
                pop=0;





            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }

    }


    //calculating the population of city and district
    public void calPopulation2(String s)
    {
        try
        {
            Statement stmt1 = con.createStatement();
            String strSelect1 = "SELECT DISTINCT "+s+" FROM city";
            ResultSet rset1 = stmt1.executeQuery(strSelect1);
            ArrayList<String> ls = new ArrayList<String>();

            while (rset1.next())
            {
                ls.add(rset1.getString(1));
            }

            List<String> newList = ls.stream().distinct().collect(Collectors.toList());

            Enumeration<String> e = Collections.enumeration(newList);

            System.out.println("\n\n++++++++++++++++++++++++++++++ Population in city"+s+"s +++++++++++++++++++++++++++++\n");

            while (e.hasMoreElements())
            {
                String ct=e.nextElement();
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT Population from city where "+s+"='"+ct+"'";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("\n\nPopulation in "+ct);
                float pop=0;
                while (rset.next()) {

                    pop=pop+rset.getInt(1);

            }
            System.out.println("The population in "+ct+" is "+pop);
                pop=0;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }

    }
    public void languageReport()
    {
        // Creating an Array list of languages
        ArrayList<String> continent = new ArrayList<String>();

        //Add elements to ArrayList
        continent.add("Chinese");
        continent.add("English");
        continent.add("Hindi");
        continent.add("Spanish");
        continent.add("Arabic");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Language Report+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Enumeration<String> e = Collections.enumeration(continent);
        double popworld=0;
         try {
             //Getting the population of the world
             Statement stmt = con.createStatement();
             // Create string for SQL statement
             String strSelect = "select Population from country";

             // Execute SQL statement
             ResultSet rset = stmt.executeQuery(strSelect);
             while (rset.next())
             {
                 popworld+=rset.getInt(1);
             }

         }
         catch (Exception e1)
         {
             System.out.println("Error in getting the population of the world.");
         }

         //Printing out the population of the world
        System.out.println("The population of the world is "+popworld);




        String lan=null;
        double noofpeople = 0;


        //Generating the number of people that speak a particular language
        while (e.hasMoreElements())
        {
            try
            {

                lan=e.nextElement();
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT countrylanguage.Percentage,country.Population,countrylanguage.Language from country inner join countrylanguage on countrylanguage.CountryCode=country.Code where countrylanguage.Language='"+lan+"' ORDER BY Population DESC";

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                double pop;
                double per;




                while (rset.next())
                {
                    pop=rset.getInt(2);
                    per=rset.getFloat(1);

                    noofpeople =  noofpeople + ((per/100) * pop);
                }



            }
            catch (Exception E)
            {
                System.out.println("Failed to generate a country report.\n Error is:"+E);
            }
            //Print Section
            System.out.println("The number of people who speaks "+lan+" is "+noofpeople);
            double per=(100/popworld)*noofpeople;
            System.out.println("The percentage of people who speaks this language in the world is "+per);

            noofpeople=0;

        }


    }









}




