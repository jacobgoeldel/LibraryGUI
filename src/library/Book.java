package library;

public class Book {
	
	private int id;
	private String isbn;
	private String authors;
	private int yearPublished;
	private String title;
	private float averageRating;
	
	public Book(int id, String isbn, String authors, int yearPublished, String title, float averageRating) {
		this.id = id;
		this.isbn = isbn;
		this.authors = authors;
		this.yearPublished = yearPublished;
		this.title = title;
		this.averageRating = averageRating;
	}
	
	public int getID() {
		return id;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public int getYearPublished() {
		return yearPublished;
	}
	
	public String getTitle() {
		return title;
	}
	
	public float getRating() {
		return averageRating;
	}
}
