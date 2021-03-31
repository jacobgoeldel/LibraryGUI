package library;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookManager {
	private List<Book> books;
	
	public BookManager(String filename, List<Book> list) {
		//change from arraylist to linkedlist here to switch between them
		books = list;
		//books = new LinkedList<Book>();
		
		//the regex pattern to use on the csv
		String pattern = "\".+?\"|[^\"]+?(?=,)|(?<=,)[^\"]+";
		Pattern r = Pattern.compile(pattern);
		
		try {  
			//open the file with a bufferedreader
			Path path = Paths.get(filename);
			BufferedReader br = Files.newBufferedReader(path);
			
			br.readLine(); // skip first line
			String line;
		    while ((line = br.readLine()) != null) {
				Matcher m = r.matcher(line);
				
				//used to store matches from the csv
				ArrayList<String> bookData = new ArrayList<String>();
				
				//loop through all matches
				while(m.find()) {
					String val = m.group(0);
					//sometimes the regex leaves a , at the start, so remove it
					if(val.startsWith(",")) {
						val = val.substring(1);
					}
					//also remove any " at the start or end of the string
					if(val.startsWith("\"")) {
						val = val.substring(1);
					}
					if(val.endsWith("\"")) {
						val = val.substring(0, val.length() - 1);
					}
					
					//adds trimmed value to book data
					bookData.add(val);
				}
				
				//get the required data from bookData and parse when needed
				int id = Integer.parseInt(bookData.get(0));
				String isbn = bookData.get(5);
				String authors = bookData.get(7);
				int year = (int)Float.parseFloat(bookData.get(8));
				String title = bookData.get(10);
				float rating = Float.parseFloat(bookData.get(12));
				
				//add to list
				books.add(new Book(id, isbn, authors, year, title, rating));
		    }
			
		}   
		catch (IOException e) {  
			e.printStackTrace();  
		}  
	}

	public List<Book> getBooks() {
		return books;
	}
	
	//returns a list of all books  that contain the given key in the title or isbn
	public List<Book> SearchFuzzy(String key) {
		List<Book> returnBooks = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getIsbn().equals(key) || books.get(i).getTitle().toLowerCase().contains(key.toLowerCase())) {
				returnBooks.add(books.get(i));
			}		
		}
		
		return returnBooks;
	}
	
	//sorts by the year
	public List<Book> sortByYear(List<Book> inputBooks) {

		for(int i = 0; i < inputBooks.size(); i++) {
			//find the book with the lowest year int
			int min = i;
			for(int j = i + 1; j < inputBooks.size(); j++) {
				if(inputBooks.get(j).getYearPublished() < inputBooks.get(min).getYearPublished()) {
					min = j;
				}
			}
			//preform swap
			Book temp = inputBooks.get(i);
			inputBooks.set(i, inputBooks.get(min));
			inputBooks.set(min, temp);
		}
		
		return inputBooks;
	}
	
	//sorts by the id
	public List<Book> sortById(List<Book> inputBooks) {
		for(int i = 0; i < inputBooks.size(); i++) {
			//find the book with the lowest year int
			int min = i;
			for(int j = i + 1; j < inputBooks.size(); j++) {
				if(inputBooks.get(j).getID() < inputBooks.get(min).getID()) {
					min = j;
				}
			}
			//preform swap
			Book temp = inputBooks.get(i);
			inputBooks.set(i, inputBooks.get(min));
			inputBooks.set(min, temp);
		}
		
		return inputBooks;
	}
	
	//sorts by the author
	public List<Book> sortByAuthor(List<Book> inputBooks) {
		for(int i = 0; i < inputBooks.size(); i++) {
			//find the book with the lowest year int
			int min = i;
			for(int j = i + 1; j < inputBooks.size(); j++) {
				if(inputBooks.get(j).getAuthors().compareTo(inputBooks.get(min).getAuthors()) < 0) {
					min = j;
				}
			}
			//preform swap
			Book temp = inputBooks.get(i);
			inputBooks.set(i, inputBooks.get(min));
			inputBooks.set(min, temp);
		}
		
		return inputBooks;
	}
	
	//sorts by the title
	public List<Book> sortByTitle(List<Book> inputBooks) {
		for(int i = 0; i < inputBooks.size(); i++) {
			//find the book with the lowest year int
			int min = i;
			for(int j = i + 1; j < inputBooks.size(); j++) {
				if(inputBooks.get(j).getTitle().compareTo(inputBooks.get(min).getTitle()) < 0) {
					min = j;
				}
			}
			//preform swap
			Book temp = inputBooks.get(i);
			inputBooks.set(i, inputBooks.get(min));
			inputBooks.set(min, temp);
		}
		
		return inputBooks;
	}
	
	public List<Book> reverseList(List<Book> inputBooks) {
		List<Book> reversedBooks = new ArrayList<Book>();
		
		//add all the books to a stack
		Stack<Book> stack = new Stack<Book>();
		for(Book book : inputBooks) {
			stack.push(book);
		}
		
		while(!stack.isEmpty()) {
			reversedBooks.add(stack.pop());
		}
		
		return reversedBooks;
	}
	
	
}
