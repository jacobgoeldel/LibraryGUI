import java.util.ArrayList;

import library.Book;
import library.BookManager;
import searchAlgorithims.BookSearch;
import searchAlgorithims.SearchBinaryID;
import searchAlgorithims.SearchLinearID;

import java.util.LinkedList;
import java.util.List;

import library.BookManager;

public class BookTester {
	
	//just a simple program to test book loading
	public static void main(String[] args) {
		System.out.println("Searching for the last book out of 1500 elements...");
		binarySearchTime();
		linearSearchTime();
	}
	
	public static void binarySearchTime() {
		BookManager bookManager = new BookManager("test.csv", new ArrayList<Book>());
		BookSearch<Integer> searchAlgorithim = new SearchBinaryID();
		
		long startTime = System.nanoTime();
		
		searchAlgorithim.search(bookManager.getBooks(), 1500);
		
		long endTime = System.nanoTime();
		System.out.println("Time taken for Binary Search: " + ((endTime - startTime) / 1000000.0) + " milliseconds.");
	}
	
	public static void linearSearchTime() {
		BookManager bookManager = new BookManager("test.csv", new LinkedList<Book>());
		BookSearch<Integer> searchAlgorithim = new SearchLinearID();
		long startTime = System.nanoTime();
		
		searchAlgorithim.search(bookManager.getBooks(), 1500);
		
		long endTime = System.nanoTime();
		System.out.println("Time taken for Linear Search: " + ((endTime - startTime) / 1000000.0) + " milliseconds.");
	}

}
