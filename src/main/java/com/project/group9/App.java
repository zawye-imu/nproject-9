/**
 **********************************************************************
 * Copyright (c) 2019 Group9
 *
 * This software is provided by the contributors ``as is''
 * and any express or implied warranties, including, but not limited to,
 * the implied warranties of merchantability and fitness for a particular
 * purpose are disclaimed. In no event shall the author or contributors
 * be liable for any direct, indirect, incidental, special, exemplary,
 * or consequential damages (including, but not limited to, procurement
 * of substitute goods or services; loss of use, data, or profits; or
 * business interruption) however caused and on any theory of liability,
 * whether in contract, strict liability, or tort (including negligence
 * or otherwise) arising in any way out of the use of this software,
 * even if advised of the possibility of such damage.
 *
 *   App: World Population System
 *
 * Author: Group9 - DevOPs
 *   Date: 23/8/2019
 **********************************************************************
 */
package com.project.group9;
//These following section will include libraries for the app
import java.math.BigInteger;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

public class App {

    static App a;

    @BeforeAll

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< World cities, countries and population report >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        //test
        //System.out.println("This is new");

        // Connect to database
        //a.connect();
        if (args.length < 1)
        {
            a.connect("localhost:3306");
        }
        else
        {
            a.connect(args[0]);
        }






        //Sorting city population from largest to smallest
        a.cityP();


        //all the countries sorted by population
        a.countryP();
        //countries sorted by continent
        a.countriesInCont();
        //Getting user inputs
        //a.countryP_input();
        //countries sorted by region

        a.countriesInRegion();

        //cities sorted by continent
        a.citiesInCont();
        //cities sorted by region
        a.citiesInRegion();
        //cities in the country sorted by population
        a.citiesInCountry();

        //cities in each district
        a.citiesInDistrict();

        //capital cities in the world sorted by population
        a.capitalP();

        //capital cities in each continent
        a.capitalContinent();

        //capital cities in each region
        a.capitalRegion();

        //Population report for each continent
        a.calPopulation("Continent");
        //Population report for each region
        a.calPopulation("Region");
        //Population report for each country
        a.calPopulation("Name");

        //population report for each district
        a.calPopulation2("District");
        //Population report for each city
        a.calPopulation2("Name");

        //getting world population
//        a.worldPop();

        //Getting the language report
        a.LanguageReport();

        //Country report
        //Country c=new Country();
        //c=a.coreport("Japan");
        //a.displayco(c);







        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */

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

        int retries = 10;
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

    public City getcity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City wo = new City();
                wo.setID(rset.getInt("ID")) ;
                wo.setName(rset.getString("Name")) ;
                wo.setCountryCode(rset.getString("CountryCode"));
                wo.setDistrict(rset.getString("District"));
                wo.setPopulation(rset.getInt("Population"));

                return wo;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public void cityP()
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
            int i =0;
            while (rset.next() && i<5)
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            System.out.println("Exception");
        }
    }


    public void displaycity(City wo)
    {
        if (wo != null)
        {

            System.out.println(wo.toString());
        }
    }



    public void countryP()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //testing
            System.out.println(rset);



            System.out.println("+++++++++++++++++++++++++++++++++++Countries in the world from largest to smallest population++++++++++++++++++++++++++++++++++++++++++++");


            //Delete limitation after the code is finished
            int i =0;

            while (rset.next() && i<5)
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            System.out.println("Exception");
        }
    }

//    public void countryP_input()
//    {
//        try
//        {
//            // Create an SQL statement
//            Statement stmt = con.createStatement();
//            // Create string for SQL statement
//            String strSelect = "SELECT * FROM country ORDER BY Population DESC";
//            // Execute SQL statement
//            ResultSet rset = stmt.executeQuery(strSelect);
//
//            //testing
//            System.out.println(rset);
//
//
//
//            System.out.println("+++Countries in the world from largest to smallest population+++");
//            //getting user input
//            System.out.println("How many countries?");
//            String text=System.console().readLine();
//            int n=Integer.parseInt(text);
//
//            //The i variable will be defined by the user
//            int i=0;
//            while (rset.next() && i<n)
//            {
//                String Code=rset.getString("Code");
//                String Name=rset.getString("Name");
//                int Population=rset.getInt("Population");
//                System.out.println(" "+Name+"------------->"+Population);
//                i++;
//
//
//            }
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get countries details");
//            System.out.println(e);
//        }
//    }
//
//
//
//
//    public void cityP_input(int n)
//    {
//        try
//        {
//            // Create an SQL statement
//            Statement stmt = con.createStatement();
//            // Create string for SQL statement
//            String strSelect = "SELECT * FROM city ORDER BY Population DESC";
//            // Execute SQL statement
//            ResultSet rset = stmt.executeQuery(strSelect);
//
//            //testing
//            System.out.println(rset);
//
//
//            // Return new employee if valid.
//            // Check one is returned
//            System.out.println("+++Cities in the world from largest to smallest population+++");
//
//
//            int i =0;
//            while (rset.next() && i<n)
//            {
//                int ID=rset.getInt("ID");
//                String Name=rset.getString("Name");
//                int Population=rset.getInt("Population");
//                System.out.println(" "+Name+"--------------------"+Population);
//                i++;
//
//
//            }
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get city details");
//            System.out.println(e);
//        }
//    }


    public void countriesInCont()
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
            while(e.hasMoreElements())
            {
                String ct=e.nextElement();
                //testing out
                System.out.println(ct);

                // Execute SQL statement
                Statement stmt1 = con.createStatement();
                String strSelect = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Continent='"+ct+"' ORDER BY Population DESC";
                ResultSet rset1 = stmt1.executeQuery(strSelect);

                System.out.println("+++Countries in "+ct+"+++");

                while (rset1.next()) {

                    Country co=new Country();
                    co.setCode(rset1.getString("Code"));
                    co.setName(rset1.getString("Name"));
                    co.setPopulation(rset1.getInt("Population"));
                    co.setContinent(rset1.getString("Continent"));
                    co.setRegion(rset1.getString("Region"));
                    co.setCapitali(rset1.getString(3));

                    //Printing section
                    System.out.println("Code: "+co.getCode()+"\nName: "+co.getName()+"\nPopulation: "+co.getPopulation()+"\nContinent: "+co.getContinent()+"\nRegion: "+co.getRegion()+"\nCapital: "+co.getCapitali()+"\n\n\n");


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



    public void countriesInRegion()
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
            System.out.println(region);
            List<String> newList = region.stream().distinct().collect(Collectors.toList());
            System.out.println(newList);


            //Add elements to ArrayList

            Enumeration<String> e = Collections.enumeration(newList);
            while(e.hasMoreElements())
            {
                String ct=e.nextElement();
                //testing out
                System.out.println(ct);

                // Execute SQL statement
                Statement stmt1 = con.createStatement();
                String strSelect1 = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Region='"+ct+"' ORDER BY Population DESC";
                ResultSet rset1 = stmt1.executeQuery(strSelect1);

                System.out.println("+++Countries in "+ct+"+++");

                while (rset1.next())
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



    public void citiesInCont() {
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
            while (e.hasMoreElements()) {
                String ct = e.nextElement();
                //testing out
                System.out.println(ct);

                // Execute SQL statement
                try {
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
                        try {
                            Statement stmtc = con.createStatement();
                            // Create string for SQL statement
                            String strSelectc = "SELECT Name FROM country where Code='" + code + "'";
                            // Execute SQL statement

                            ResultSet rsetc = stmtc.executeQuery(strSelectc);
                            while (rsetc.next()) {
                                c.setCountryCode(rsetc.getString(1));
                            }
                            //
                            System.out.println(" Name: "+c.getName()+"\n Population:"+c.getPopulation()+"\n District:"+c.getDistrict()+"\n Country: "+c.getCountryCode()+"\n");
                        }
                        catch (Exception e2) {
                            System.out.println("Error is: " + e2);
                        }
                        System.out.println("\n\n\n");

                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                    System.out.println(e1);
                }
            }
        }catch (Exception e3)
        {
            System.out.println("Error is :"+e3);
        }
    }



    public void citiesInRegion() {
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
            System.out.println(region);
            List<String> newList = region.stream().distinct().collect(Collectors.toList());
            System.out.println(newList);


            //Add elements to ArrayList

            Enumeration<String> e = Collections.enumeration(newList);
            while (e.hasMoreElements()) {

                try {
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

                        try {
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
                        } catch (Exception e2) {
                            System.out.println("Error is: " + e2);
                        }

                        System.out.println("\n\n\n");
                    }


                } catch (Exception et) {
                    System.out.println("The error is: " + et);
                }
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
            System.out.println(e1);
        }
    }



    public void capitalP()
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
            while (rset.next())
            {
                City c=new City();
                c.setName(rset.getString("Name"));
                c.setPopulation(rset.getInt("Population"));
                c.setCountryCode(rset.getString(5));
                System.out.println("Name:"+c.getName()+"\n Country: "+c.getCountryCode()+"\n Population: "+c.getPopulation());


            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }
    }


    public void citiesInCountry()
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
            while(e.hasMoreElements())
            {
                String ct=e.nextElement();


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





        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }


    public void citiesInDistrict() {
        try {
            // Execute SQL statement
            Statement stmt = con.createStatement();
            //Select distinct country codes of countires
            String strSelect = "SELECT DISTINCT District FROM city";
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create an array list
            ArrayList<String> District = new ArrayList<String>();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++ Cities in each District ++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            while (rset.next()) {
                District.add(rset.getString(1));
            }
            //Various country codes

            System.out.println(District);

            List<String> newList = District.stream().distinct().collect(Collectors.toList());

            System.out.println(newList);


            //Add elements to ArrayList

            Enumeration<String> e = Collections.enumeration(newList);
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

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }



    //capital cities in continent
    public void capitalContinent()
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




            while (e.hasMoreElements()) {
                String ct=e.nextElement();
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID WHERE country.continent='"+ct+"'ORDER BY city.Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("Capital cities in  "+ct);
                while (rset.next()) {

                    City c=new City();
                    c.setName(rset.getString("Name"));
                    c.setPopulation(rset.getInt("Population"));
                    c.setCountryCode(rset.getString(5));
                    System.out.println("Name:"+c.getName()+"\n Country: "+c.getCountryCode()+"\n Population: "+c.getPopulation());


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

    //capital cities in Region
    public void capitalRegion()
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




            System.out.println("++++++++++++++++++++++++++++++ Capital cities in each region +++++++++++++++++++++++++++++");

            while (e.hasMoreElements()) {
                String ct=e.nextElement();
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT city.Name,city.Population,country.Capital,city.ID,country.Name FROM country INNER JOIN city on country.Capital=city.ID WHERE country.Region='"+ct+"'ORDER BY city.Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("Capital cities in  "+ct);
                while (rset.next()) {

                    City c=new City();
                    c.setName(rset.getString("Name"));
                    c.setPopulation(rset.getInt("Population"));
                    c.setCountryCode(rset.getString(5));
                    System.out.println("Name:"+c.getName()+"\n Country: "+c.getCountryCode()+"\n Population: "+c.getPopulation());


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
                ResultSet rset2 = stmt.executeQuery(strSelect2);

                //Getting the population of all the cities
                float cpop=0;
                while (rset2.next())
                {
                    cpop=cpop+rset2.getInt(2);
                }
                System.out.println("Population in cities of "+ct+" ="+cpop);
                float npop=pop-cpop;
                System.out.println("People not living in cities of "+ct+" ="+npop);





            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }

    }
//    public void worldPop()
//    {
//        try {
//            //getting population fof the world
//            Statement stmt4 = con.createStatement();
//            String strSelect4 = "SELECT Population from country";
//            ResultSet rset4 = stmt4.executeQuery(strSelect4);
//            Integer wpop = 0;
//            while (rset4.next()) {
//                wpop = wpop + rset4.getInt(1);
//            }
//            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++Population of the world+++++++++++++++++++++++++++++++++++++++++++++++++");
//            System.out.println("\nThe population of the world is " + wpop);
//        }
//        catch (Exception e)
//        {
//            System.out.println("Error in getting world population\n"+"Error:"+e);
//        }
//    }

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

            while (e.hasMoreElements()) {
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
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            System.out.println("Exception");
        }

    }
    public void LanguageReport()
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

                    noofpeople =  (noofpeople + (per * (pop/ 100)));
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







        }


    }


    //Country report on country
    public Country coreport(String s)
    {
        Country c=new Country();
        try {
            //Getting the population of the world
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Name,country.Population,city.Name,country.Code,country.Continent,country.Region FROM country INNER JOIN city on country.Capital=city.ID where country.Name='"+s+"'";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next())
            {
                c.setCode(rset.getString("Code"));
                c.setName(rset.getString("Name"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                c.setCapitali(rset.getString(3));


            }
        }
        catch (Exception E){
            System.out.println("Error generating country report. \n Error:"+E);

        }


        return c;
    }

    //Display country
    public void displayco(Country co)
    {
        System.out.println("Code:"+co.getCode());
        System.out.println("Name:"+co.getName());
        System.out.println("Continent:"+co.getContinent());
        System.out.println("Region:"+co.getRegion());
        System.out.println("Population:"+co.getPopulation());
        System.out.println("Capital:"+co.getCapitali());

    }







}




