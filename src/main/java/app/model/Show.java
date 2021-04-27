package app.model;


import app.model.Users.PCOUser;

import java.util.List;
import java.util.Map;

public class Show {
    private final int showId;
    private final String showTitle;
    private final String genre;
    private final String length;
    private final String movie;
    private final String series;
    private final String PCO;
    private final String year;
    private final String imageAddress;
    private final Map<String, String> credits_roll;


    public Show(int showId, String showTitle, String genre, String length, String movie, String series, String PCO, String year, String imageAddress, Map<String, String> casts) {
        this.showId = showId;
        this.showTitle = showTitle;
        this.genre = genre;
        this.length = length;
        this.movie = movie;
        this.series = series;
        this.PCO = PCO;
        this.year = year;
        this.imageAddress = imageAddress;
        this.credits_roll = casts;
    }

    public int getShowID() { return showId; }

    public String getShowTitle() { return showTitle; }

    public String getGenre() { return genre; }

    public String getLength() { return length; }

    public String getLengthHour() { return String.valueOf((int)(Double.parseDouble(length))); }

    public int getLengthMinute() { return (int)((Double.parseDouble(length) - ((int)(Double.parseDouble(length)))) * 100); }

    public String getMovie() { return movie; }

    public String getSeries() { return series; }

    public String getPCO() { return PCO; }

    public String getYear() { return String.valueOf(year); }

    public int getShowId() { return showId; }

    public String getCover() { return this.imageAddress; }

    public Map<String, String> getCredits_roll() { return this.credits_roll; }
}
