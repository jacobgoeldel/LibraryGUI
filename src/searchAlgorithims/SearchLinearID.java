package searchAlgorithims;

import java.util.List;

import library.Book;

public class SearchLinearID implements BookSearch<Integer>{

	public SearchLinearID() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int search(List<Book> books, Integer key) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getID() == key) {
				return i;
			}		
		}
		
		return -1;
	}

}
