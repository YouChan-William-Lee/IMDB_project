package app.dao;

import java.util.*;

import app.model.Cast;
import app.model.Show;
import app.model.Users.PCOUser;
import com.google.common.collect.ImmutableList;

public class ShowDao extends Database {

    private final List<Show> shows;

    public ShowDao() {

        PCOUser waltDisney = new PCOUser("waltdisneypictures", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Walt Disney", "Pictures", "waltdisneypictures@gmail.com", "pco");
        Map<String, Cast> creditsRoll1 = new HashMap<String, Cast>();
        Cast person1 = CastDao.getPerson("Ewan McGregor", new Date(39211200000L), "Ewan Gordon McGregor was born on March 31, 1971 in Perth, Perthshire, Scotland, to Carol Diane (Lawson) and James Charles McGregor, both teachers. His uncle is actor Denis Lawson. He was raised in Crieff. At age 16, he left Morrison Academy to join the Perth Repertory Theatre");
        Cast person2 = CastDao.getPerson("Natalie Portman", new Date(360878400000L), "Natalie Portman is the first person born in the 1980s to have won the Academy Award for Best Actress (for Black Swan (2010)). Natalie was born Natalie Hershlag on June 9, 1981, in Jerusalem, Israel.");
        creditsRoll1.put("Obi-Wan Kenoboi", person1);
        creditsRoll1.put("Queen Amidala", person2);
        Show show1 = new Show(1,"Star Wars: Episode I - The Phantom Menace", "Action",2.16,1,0, waltDisney,1999,"https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg", creditsRoll1);
        person1.addShow(show1);
        person2.addShow(show1);

        Map<String, Cast> creditsRoll2 = new HashMap<String, Cast>();
        person1 = CastDao.getPerson("Ewan McGregor", new Date(39211200000L), "Ewan Gordon McGregor was born on March 31, 1971 in Perth, Perthshire, Scotland, to Carol Diane (Lawson) and James Charles McGregor, both teachers. His uncle is actor Denis Lawson. He was raised in Crieff. At age 16, he left Morrison Academy to join the Perth Repertory Theatre");
        person2 = CastDao.getPerson("Natalie Portman", new Date(360878400000L), "Natalie Portman is the first person born in the 1980s to have won the Academy Award for Best Actress (for Black Swan (2010)). Natalie was born Natalie Hershlag on June 9, 1981, in Jerusalem, Israel.");
        creditsRoll2.put("Obi-Wan Kenoboi", person1);
        creditsRoll2.put("Queen Amidala", person2);
        Show show2 = new Show(2,"Star Wars: Episode II - Attack of The Clones","Action",2.22,1,0, waltDisney,2002,"https://images-na.ssl-images-amazon.com/images/I/91WTnoEb7RL._RI_.jpg", creditsRoll2);
        person1.addShow(show2);
        person2.addShow(show2);

        Map<String, Cast> creditsRoll3 = new HashMap<String, Cast>();
        person1 = CastDao.getPerson("Ewan McGregor", new Date(39211200000L), "Ewan Gordon McGregor was born on March 31, 1971 in Perth, Perthshire, Scotland, to Carol Diane (Lawson) and James Charles McGregor, both teachers. His uncle is actor Denis Lawson. He was raised in Crieff. At age 16, he left Morrison Academy to join the Perth Repertory Theatre");
        person2 = CastDao.getPerson("Natalie Portman", new Date(360878400000L), "Natalie Portman is the first person born in the 1980s to have won the Academy Award for Best Actress (for Black Swan (2010)). Natalie was born Natalie Hershlag on June 9, 1981, in Jerusalem, Israel.");
        creditsRoll3.put("Obi-Wan Kenoboi", person1);
        creditsRoll3.put("Queen Amidala", person2);
        Show show3 = new Show(3,"Star Wars: Episode III - Revenge of The Sith","Action",2.20,1,0, waltDisney,2005,"https://i.pinimg.com/originals/9d/6a/23/9d6a232c628e4aaaecfaf3dbdb1d9653.jpg", creditsRoll3);
        person1.addShow(show3);
        person2.addShow(show3);

        Map<String, Cast> creditsRoll4 = new HashMap<String, Cast>();
        person1 = CastDao.getPerson("Mark Hamill", new Date(-576561600000L), "Mark Hamill is best known for his portrayal of Luke Skywalker in the original Star Wars trilogy - Star Wars: Episode IV - A New Hope (1977), Star Wars: Episode V - The Empire Strikes Back (1980), and Star Wars: Episode VI - Return of the Jedi (1983) - a role he reprised in Star Wars: Episode VII - The Force Awakens (2015), Star Wars: Episode VIII - The Last Jedi (2017) and Star Wars: Episode IX - The Rise of Skywalker (2019). He also starred and co-starred in the films Corvette Summer (1978), The Big Red One (1980), and Kingsman: The Secret Service (2014). Hamill's extensive voice acting work includes a long-standing role as the Joker, commencing with Batman: The Animated Series (1992).");
        person2 = CastDao.getPerson("Harrison Ford", new Date(-869544000000L), "Harrison Ford was born on July 13, 1942 in Chicago, Illinois, to Dorothy (Nidelman), a radio actress, and Christopher Ford (born John William Ford), an actor turned advertising executive. His father was of Irish and German ancestry, while his maternal grandparents were Jewish emigrants from Minsk, Belarus.");
        creditsRoll4.put("Luke Skywalker", person1);
        creditsRoll4.put("Han Solo", person2);
        Show show4 = new Show(4,"Star Wars: Episode V - The Empire Strikes Back","Action",2.07,1,0, waltDisney,1980,"https://fsb.zobj.net/crop.php?r=nwgfaOZ1S1Fxw-zWmyBoZFhdEjGRX4LDwAxC9uDqFc0IgeshXPexkHF083T4UsZ_SAN3e5cVTCMs-V97WTbI49ANLszc4DXedgc0NQfEcFVp7uofEGvgH1IObRxNLTqAr1i6fyIYwEHOiLOD", creditsRoll4);
        person1.addShow(show4);
        person2.addShow(show4);

        Map<String, Cast> creditsRoll5 = new HashMap<String, Cast>();
        person1 = CastDao.getPerson("Mark Hamill", new Date(-576561600000L), "Mark Hamill is best known for his portrayal of Luke Skywalker in the original Star Wars trilogy - Star Wars: Episode IV - A New Hope (1977), Star Wars: Episode V - The Empire Strikes Back (1980), and Star Wars: Episode VI - Return of the Jedi (1983) - a role he reprised in Star Wars: Episode VII - The Force Awakens (2015), Star Wars: Episode VIII - The Last Jedi (2017) and Star Wars: Episode IX - The Rise of Skywalker (2019). He also starred and co-starred in the films Corvette Summer (1978), The Big Red One (1980), and Kingsman: The Secret Service (2014). Hamill's extensive voice acting work includes a long-standing role as the Joker, commencing with Batman: The Animated Series (1992).");
        person2 = CastDao.getPerson("Daisy Ridley", new Date(702849600000L), "Daisy Jazz Isobel Ridley is an English actress. She is best known for her breakthrough role as \"Rey\" in the 2015 film, Star Wars: Episode VII - The Force Awakens (2015). Daisy was born in Westminster, London, on April 10, 1992. She is the daughter of Louise Fawkner-Corbett and Chris Ridley.");
        creditsRoll5.put("Luke Skywalker", person1);
        creditsRoll5.put("Rey", person2);
        Show show5 =  new Show(5,"Star Wars: Episode IX - The Rise of Skywalker","Action",2.22,1,0, waltDisney,2019,"https://lumiere-a.akamaihd.net/v1/images/star-wars-the-rise-of-skywalker-theatrical-poster-1000_ebc74357.jpeg?region=0%2C0%2C891%2C1372", creditsRoll5);
        person1.addShow(show5);
        person2.addShow(show5);

        shows = ImmutableList.of(show1, show2, show3, show4, show5);
    }

    public Iterable<Show> getAllShows() {
        return shows;
    }

    public Show getShowByShowId(String showId) {
        int showID = Integer.parseInt(showId);
        return shows.stream().filter(b -> b.getShowID() == showID).findFirst().orElse(null);
    }

    public Show getRandomShow() {
        return shows.get(new Random().nextInt(shows.size()));
    }

    public Iterable<Show> getSearchedShowsByShowTitles(String searching) {
        List<Show> searchedShows = new ArrayList<Show>();
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getShowTitle().toUpperCase().contains(searching.toUpperCase())) {
                searchedShows.add(shows.get(i));
            };
        }

        return searchedShows;
    }
}
