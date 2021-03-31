import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {

	public enum SortingTypes { Id, Title, Author, Year }
	public enum SortingDirection { Ascending, Descending }
	BookManager bookManager;
	
	private JFrame frmBooks;
	private JTextField searchField;
	
	private JButton btnSearch;
	
	private JComboBox cbSortDirection;
	
	private JComboBox cbSortType;
	
	String[] columnNames = {"Id",
			"Title",
            "Authors",
            "ISBN",
            "Year Published",
            "Rating"};
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmBooks = new JFrame();
		frmBooks.setTitle("Library Books");
		frmBooks.setBounds(100, 100, 670, 489);
		frmBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBooks.setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/icon.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createUI();
		createEvents();
	}
	
	private void createUI() {
		bookManager = new BookManager("books.csv", new ArrayList<Book>());
		
		btnSearch = new JButton("Search");
		
		cbSortDirection = new JComboBox(SortingDirection.values());
		
		cbSortType = new JComboBox(SortingTypes.values());
		
		searchField = new JTextField();
		
		searchField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSearch = new JLabel("Search:");
		GroupLayout groupLayout = new GroupLayout(frmBooks.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSearch)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchField, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblSortBy, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbSortType, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbSortDirection, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(cbSortDirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbSortType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSortBy)
						.addComponent(lblSearch))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableModel = new DefaultTableModel(createTableData(bookManager.getBooks()), columnNames);
		table = new JTable(tableModel);
		
		//set column widths
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		scrollPane.setViewportView(table);
		frmBooks.getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAndSort();
			}
		});
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAndSort();
			}
		});
	}
		
	public void searchAndSort() {
		//get vars for searching information
		boolean ascending = cbSortDirection.getSelectedItem() == SortingDirection.Ascending ? true : false;
		String search = searchField.getText();
		
		List<Book> bookOutput;
		if(search.equals(""))
			bookOutput = bookManager.getBooks();
		else
			bookOutput = bookManager.SearchFuzzy(search);
			
		//match to sorting algorithm based on search type
		switch((SortingTypes)cbSortType.getSelectedItem()) {
		case Id:
			bookManager.sortById(bookOutput);
			break;
			
		case Title:
			bookManager.sortByTitle(bookOutput);
			break;
			
		case Year:
			bookManager.sortByYear(bookOutput);
			break;
			
		case Author:
			bookManager.sortByAuthor(bookOutput);
			break;
			
		}
		
		//reverse the list if needed and update the table
		if(!ascending)
			bookOutput = bookManager.reverseList(bookOutput);
		resetTableData(bookOutput);
	}
	
	//converts from a list of books to a 2d array of objects, so that it can be displayed in the table
	public Object[][] createTableData(List<Book> bookData) {
		Object[][] data = new Object[bookData.size()][6];
		
		for(int i = 0; i < bookData.size(); i++) {
			data[i][0] = bookData.get(i).getID();
			data[i][1] = bookData.get(i).getTitle();
			data[i][2] = bookData.get(i).getAuthors();
			data[i][3] = bookData.get(i).getIsbn();
			data[i][4] = bookData.get(i).getYearPublished();
			data[i][5] = bookData.get(i).getRating();
		}
		
		return data;
	}
	
	//reset the table rows to match the current list in bookManager
	public void resetTableData() {
		//remove all current rows
		for(int i = tableModel.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		
		//add new data row by row
		for(Book book : bookManager.getBooks()) {
			Object[] data = new Object[6];
			data[0] = book.getID();
			data[1] = book.getTitle();
			data[2] = book.getAuthors();
			data[3] = book.getIsbn();
			data[4] = book.getYearPublished();
			data[5] = book.getRating();
			tableModel.addRow(data);
		}
	}
	
	public void resetTableData(List<Book> newList) {
		//remove all current rows
		for(int i = tableModel.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		
		//add new data row by row
		for(Book book : newList) {
			Object[] data = new Object[6];
			data[0] = book.getID();
			data[1] = book.getTitle();
			data[2] = book.getAuthors();
			data[3] = book.getIsbn();
			data[4] = book.getYearPublished();
			data[5] = book.getRating();
			tableModel.addRow(data);
		}
	}
}
