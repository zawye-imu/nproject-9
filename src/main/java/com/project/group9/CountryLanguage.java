package com.project.group9;

public class CountryLanguage {

    private String Language;
    private String IsOfficial;
    private Float Percentage;

    public CountryLanguage() {
    }

    public CountryLanguage(String language, String isOfficial, Float percentage) {
        Language = language;
        IsOfficial = isOfficial;
        Percentage = percentage;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        IsOfficial = isOfficial;
    }

    public Float getPercentage() {
        return Percentage;
    }

    public void setPercentage(Float percentage) {
        Percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "Language='" + Language + '\'' +
                ", IsOfficial='" + IsOfficial + '\'' +
                ", Percentage=" + Percentage +
                '}';
    }
}
