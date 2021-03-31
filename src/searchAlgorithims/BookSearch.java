package searchAlgorithims;
import java.util.List;
import library.Book;

public interface BookSearch<T> {
    public int search(List<Book> books, T key);
}