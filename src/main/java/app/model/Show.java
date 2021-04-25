package app.model;


import app.model.Users.PCOUser;

public class Show {
    private final int showId;
    private final String showTitle;
    private final String genre;
    private final double length;
    private final int movie;
    private final int series;
    private final PCOUser PCOUser;
    private final int year;
    private final String imageAddress;
//    private List<Cast> cast;


    public Show(int showId, String showTitle, String genre, double length, int movie, int series, PCOUser PCOUser, int year, String imageAddress) {
        this.showId = showId;
        this.showTitle = showTitle;
        this.genre = genre;
        this.length = length;
        this.movie = movie;
        this.series = series;
        this.PCOUser = PCOUser;
        this.year = year;
        this.imageAddress = imageAddress;
    }

    public int getshowID() {
        return showId;
    }

    public String getShowTitle() { return showTitle; }

    public String getGenre() { return genre; }

    public String getLengthHour() { return String.valueOf((int)length); }

    public String getLengthMinute() { return String.valueOf((int)((length - (int)length)*100)); }

    public int getSeries() { return series; }

    public String getYear() { return String.valueOf(year); }

    public int getShowId() { return showId; }

    public String getCover() {
        return this.imageAddress;
    }
}
