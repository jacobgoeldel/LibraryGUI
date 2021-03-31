package searchAlgorithims;
import java.util.List;

import library.Book;

public class SearchBinaryISBN implements BookSearch<String> {
	
	public SearchBinaryISBN() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int search(List<Book> books, String key) {
		//Setting variable for upperBound to books size - 1
		int upperBound = books.size() - 1;
		int lowerBound = 0;
		int mid = 0;
		
		//Loop to search for key
		while (lowerBound <= upperBound) {
			if(books.get(mid).getIsbn().compareTo(key) < 0) {
				lowerBound = mid + 1;
			}else if (books.get(mid).getIsbn().equals(key)) {
				return mid;
			} else {
				upperBound = mid - 1;
			}
			mid = (lowerBound + upperBound)/2;
		}
		if(lowerBound > upperBound) {
			System.out.println("Not Found");
		}
		
		return -1;
	}
}