package com.project.group9;
//These following section will include libraries for the app
import java.sql.*;

public class App {

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
        World wo = a.getcity(1);
        // Display results
        a.displaycity(wo);

        //Sorting city population from largest to smallest
        a.cityP();

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
            while (rset.next())
            {
                int ID=rset.getInt("ID");
                String Name=rset.getString("Name");
                int Population=rset.getInt("Population");
                System.out.println(" "+Name+"--------------------"+Population);


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
}
