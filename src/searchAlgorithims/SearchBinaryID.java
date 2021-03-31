package searchAlgorithims;
import java.util.List;

public class SearchBinaryID implements BookSearch<Integer> {
	
	public SearchBinaryID() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int search(List<library.Book> books, Integer key) {
		//Setting variable for upperBound to books size - 1
				int upperBound = books.size() - 1;
				int lowerBound = 0;
				int mid = 0;
				
				//Loop to search for key
				while (lowerBound <= upperBound) {
					if(books.get(mid).getID() < key) {
						lowerBound = mid + 1;
					}else if (books.get(mid).getID() == key) {
						return mid;
					} else {
						upperBound = mid - 1;
					}
					mid = (lowerBound + upperBound)/2;
				}
				
				return -1;
	}
}