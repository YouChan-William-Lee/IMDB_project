package app.model;


public class Show {
    public final int showId;
    public final String showTitle;
    public final String genre;
    public final double length;
    public final int movie;
    public final int series;
    public final int procoId;
    public final int year;
    public final String imageAddress;

    public String getMediumCover() {
        return this.imageAddress;
    }

    public String getLargeCover() {
        return this.imageAddress;
    }

    public Show(int showId, String showTitle, String genre, double length, int movie, int series, int procoId, int year, String imageAddress) {
        this.showId = showId;
        this.showTitle = showTitle;
        this.genre = genre;
        this.length = length;
        this.movie = movie;
        this.series = series;
        this.procoId = procoId;
        this.year = year;
        this.imageAddress = imageAddress;
    }

    public String getShowTitle() { return showTitle; }

    public String getGenre() { return genre; }

    public String getLengthHour() { return String.valueOf((int)length); }

    public String getLengthMinute() { return String.valueOf((int)((length - (int)length)*100)); }

    public int getSeries() { return series; }

    public String getYear() { return String.valueOf(year); }

    public int getShowId() { return showId; }
}
