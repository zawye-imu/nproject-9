package com.project.group9;

public class CountryLanguage {

    private String Language;
    private String IsOfficial;
    private Float Percentage;

    public CountryLanguage() {
    }

    public CountryLanguage(final String language, final String isOfficial,final  Float percentage) {
        Language = language;
        IsOfficial = isOfficial;
        Percentage = percentage;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(final String language) {
        Language = language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(final String isOfficial) {
        IsOfficial = isOfficial;
    }

    public Float getPercentage() {
        return Percentage;
    }

    public void setPercentage(final Float percentage) {
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
