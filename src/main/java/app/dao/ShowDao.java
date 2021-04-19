package app.dao;

import java.util.List;
import java.util.Random;

import app.model.Show;
import com.google.common.collect.ImmutableList;

public class ShowDao {

    private final List<Show> shows = ImmutableList.of(
            new Show("Moby Dick", "Herman Melville", "9789583001215"),
            new Show("A Christmas Carol", "Charles Dickens", "9780141324524"),
            new Show("Pride and Prejudice", "Jane Austen", "9781936594290"),
            new Show("The Fellowship of The Ring", "J. R. R. Tolkien", "0007171978"),
            new Show("Harry Potter", "J. K. Rowling", "0747532699"),
            new Show("War and Peace", "Leo Tolstoy", "9780060798871"),
            new Show("Don Quixote", "Miguel Cervantes", "9789626345221"),
            new Show("Ulysses", "James Joyce", "9780394703800"),
            new Show("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"),
            new Show("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "9780060531041"),
            new Show("The adventures of Huckleberry Finn", "Mark Twain", "9781591940296"),
            new Show("Alice In Wonderland", "Lewis Carrol", "9780439291491")
    );

    public Iterable<Show> getAllShows() {
        return shows;
    }

    public Show getShowByIsbn(String isbn) {
        return shows.stream().filter(b -> b.isbn.equals(isbn)).findFirst().orElse(null);
    }

    public Show getRandomShow() {
        return shows.get(new Random().nextInt(shows.size()));
    }
}
