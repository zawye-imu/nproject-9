package com.project.group9;
//These following section will include libraries for the app
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        //test
        System.out.println("This is new");

        // Connect to database
        a.connect();
        // Get Employee
        World wo = a.getcity(1);
        // Display results
        a.displaycity(wo);

        //Sorting city population from largest to smallest
        a.cityP();

        //all the countries sorted by population
        a.countryP();




        //Getting user inputs
        Scanner reader = new Scanner(System.in);
        a.countryP_input(reader);
        reader.close();







//        System.out.println("Enter how many populated countries:");
//        int k = reader.nextInt();
//        a.cityP_input(k);
//        //closing
//        reader.close();


        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
    public World getcity(int ID)
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
                World wo = new World();
                wo.ID= rset.getInt("ID");
                wo.Name = rset.getString("Name");
                wo.CountryCode = rset.getString("CountryCode");
                wo.District = rset.getString("District");
                wo.Population = rset.getInt("Population");

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
            System.out.println("+++Cities in the world from largest to smallest population+++");
            int i =0;
            while (rset.next() && i<5)
            {
                int ID=rset.getInt("ID");
                String Name=rset.getString("Name");
                int Population=rset.getInt("Population");
                System.out.println(" "+Name+"--------------------"+Population);
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


    public void displaycity(World wo)
    {
        if (wo != null)
        {

            System.out.println(
                    wo.ID + " "
                            + wo.Name + " "
                            + wo.CountryCode + "\n"
                            + wo.District + "\n"
                            + wo.Population + "\n");
        }
    }



    public void countryP()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM country ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //testing
            System.out.println(rset);



            System.out.println("+++Countries in the world from largest to smallest population+++");


            //Delete limitation after the code is finished
            int i =0;

            while (rset.next() && i<5)
            {
                String Code=rset.getString("Code");
                String Name=rset.getString("Name");
                int Population=rset.getInt("Population");
                System.out.println(" "+Name+"------------->"+Population);
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

    public void countryP_input(Scanner scanner)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM country ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //testing
            System.out.println(rset);



            System.out.println("+++Countries in the world from largest to smallest population+++");
            //getting user input
            System.out.println("How many countries?");
            int n=scanner.nextInt();

            //The i variable will be defined by the user
            int i=0;
            while (rset.next() && i<n)
            {
                String Code=rset.getString("Code");
                String Name=rset.getString("Name");
                int Population=rset.getInt("Population");
                System.out.println(" "+Name+"------------->"+Population);
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


    public void cityP_input(int n)
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
            System.out.println("+++Cities in the world from largest to smallest population+++");


            int i =0;
            while (rset.next() && i<n)
            {
                int ID=rset.getInt("ID");
                String Name=rset.getString("Name");
                int Population=rset.getInt("Population");
                System.out.println(" "+Name+"--------------------"+Population);
                i++;


            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            System.out.println("Exception:"+e);
        }
    }



}
