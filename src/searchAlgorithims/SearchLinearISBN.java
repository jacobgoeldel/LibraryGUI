package searchAlgorithims;

import java.util.List;

import library.Book;

public class SearchLinearISBN implements BookSearch<String> {

	public SearchLinearISBN() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int search(List<Book> books, String key) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getIsbn().equals(key)) {
				return i;
			}		
		}
		
		return -1;
	}
	
	

}
