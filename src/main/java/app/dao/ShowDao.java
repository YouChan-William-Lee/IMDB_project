package app.dao;

import java.util.List;
import java.util.Random;

import app.model.Show;
import com.google.common.collect.ImmutableList;

public class ShowDao {

    private final List<Show> shows = ImmutableList.of(
            new Show(1,"Star Wars: Episode I - The Phantom Menace", "Action",2.16,1,0,9,1999,"https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg"),
            new Show(2,"Star Wars: Episode II - Attack of The Clones","Action",2.22,1,0,9,2002,"https://images-na.ssl-images-amazon.com/images/I/91WTnoEb7RL._RI_.jpg"),
            new Show(3,"Star Wars: Episode III - Revenge of The Sith","Action",2.20,1,0,9,2005,"https://i.pinimg.com/originals/9d/6a/23/9d6a232c628e4aaaecfaf3dbdb1d9653.jpg"),
            new Show(4,"Star Wars: Episode V - The Empire Strikes Back","Action",2.07,1,0,9,1980,"https://fsb.zobj.net/crop.php?r=nwgfaOZ1S1Fxw-zWmyBoZFhdEjGRX4LDwAxC9uDqFc0IgeshXPexkHF083T4UsZ_SAN3e5cVTCMs-V97WTbI49ANLszc4DXedgc0NQfEcFVp7uofEGvgH1IObRxNLTqAr1i6fyIYwEHOiLOD"),
            new Show(5,"Star Wars: Episode IX - The Rise of Skywalker","Action",2.22,1,0,9,2019,"https://lumiere-a.akamaihd.net/v1/images/star-wars-the-rise-of-skywalker-theatrical-poster-1000_ebc74357.jpeg?region=0%2C0%2C891%2C1372")
    );

    public Iterable<Show> getAllShows() {
        return shows;
    }

    public Show getShowByShowId(String showId) {
        int showID = Integer.parseInt(showId);
        return shows.stream().filter(b -> b.showId == showID).findFirst().orElse(null);
    }

    public Show getRandomShow() {
        return shows.get(new Random().nextInt(shows.size()));
    }
}
