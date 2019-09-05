package com.project.group9;


public class City {
    /**
     * City ID
     */
    private int ID;

    /**
     *City Name
     */
    private String Name;

    /**
     * Country Code
     */
    private String CountryCode;


    /**
     * District
     */
    private String District;

    /**
     * Population
     */
    private int Population;

    public City() {

    }

    public City(final int ID, final String name,final String countryCode,final String district,final int population) {
        this.ID = ID;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public int getPopulation() {
        return Population;
    }

    public void setID(final int ID) {
        this.ID = ID;
    }

    public void setName(final String name) {
        Name = name;
    }

    public void setCountryCode(final String countryCode) {
        CountryCode = countryCode;
    }

    public void setDistrict(final String district) {
        District = district;
    }

    public void setPopulation(final int population) {
        Population = population;
    }


    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", Population=" + Population +
                '}';
    }
}
