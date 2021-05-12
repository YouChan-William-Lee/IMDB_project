package app.model.ShowEntities;

import java.util.Map;

public class Show {
    private final int showId;
    private String showTitle;
    private String genre;
    private String length;
    private String movie;
    private String series;
    private String PCO;
    private String year;
    private String imageAddress;
    private boolean approved;
    private Map<String, String> credits_roll;

    //Show constructor
    public Show(int showId, String showTitle, String genre, String length, String movie, String series, String PCO, String year, boolean approved, String imageAddress, Map<String, String> casts) {
        this.showId = showId;
        this.showTitle = showTitle;
        this.genre = genre;
        this.length = length;
        this.movie = movie;
        this.series = series;
        this.PCO = PCO;
        this.year = year;
        this.imageAddress = imageAddress;
        this.approved = approved;
        this.credits_roll = casts;
    }

    public int getShowID() {
        return showId;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getLength() {
        return length;
    }

    public String getLengthHour() {
        return String.valueOf((int) (Double.parseDouble(length)));
    }

    public int getLengthMinute() {
        return (int) Math.round((Double.parseDouble(length) - ((int) (Double.parseDouble(length)))) * 100);
    }

    public String getMovie() {
        return movie;
    }

    public String getSeries() {
        return series;
    }

    public String getPCO() {
        return PCO;
    }

    public String getYear() {
        return String.valueOf(year);
    }

    public String getCover() {
        return this.imageAddress;
    }

    public boolean getApproved() {
        return this.approved;
    }

    public Map<String, String> getCredits_roll() {
        return this.credits_roll;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setPCO(String PCO) {
        this.PCO = PCO;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setCredits_roll(Map<String, String> credits_roll) {
        this.credits_roll = credits_roll;
    }
}
