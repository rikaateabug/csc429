package application;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import userinterface.MainStageContainer;
import userinterface.View;
import javafx.scene.Scene;
import javafx.stage.Stage;
import exception.InvalidPrimaryKeyException;
import model.EntityBase;

public class BookCollection extends EntityBase {

	
	private static final String tableName = "Book";
	private Vector<Book> books;
	protected Librarian myLibrarian;
	protected Stage myStage;
	protected String bookTitle;
	
	//----------------------------------------------------------	
	//Constructor for use with Librarian
	//----------------------------------------------------------
	public BookCollection(Librarian lib) {	//String title
		super(tableName);
		myStage = MainStageContainer.getInstance();
		myLibrarian = lib;
		books = new Vector<Book>();
	}
	//----------------------------------------------------------	
	//Constructor sets up a blank Vector
	//----------------------------------------------------------
	public BookCollection() {
		super(tableName);
		books = new Vector<Book>();
	}
	
	//----------------------------------------------------------	
	//Adds Books to the Vector
	//----------------------------------------------------------
	private void addBook(Book b) {
		books.add(b);
	}
	
	
	//----------------------------------------------------------	
	//Finding a Book older than supplied date.
	//----------------------------------------------------------
	public void findBooksOlderThanDate(String year) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (pubYear < " + year + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextBookData = (Properties)allDataRetrieved.elementAt(i);
				Book book = new Book(nextBookData);
				
				if (book != null)
					addBook(book);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Books found older than: " + year + "\n");
		}
	}
	
	//----------------------------------------------------------	
	//Finding a Book newer than supplied date.
	//----------------------------------------------------------
	public void findBooksNewerThan(String year) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (pubYear > " + year + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextBookData = (Properties)allDataRetrieved.elementAt(i);
				Book book = new Book(nextBookData);
				
				if (book != null)
					addBook(book);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Books found newer than: " + year + "\n");
		}
		
	}
	
	//----------------------------------------------------------	
	//Finding a Book like a given title
	//----------------------------------------------------------
	public void findBooksWithTitleLike(String title) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (title LIKE '%" + title + "%')";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextBookData = (Properties)allDataRetrieved.elementAt(i);
				Book book = new Book(nextBookData);
				
				if (book != null)
					addBook(book);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Books found with a title like: " + title + "\n");
		}
		
	}
	//----------------------------------------------------------	
	//Finding a Book like a given Author
	//----------------------------------------------------------
	public void findBooksWithAuthorLike(String author) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (author LIKE '%" + author + "%')";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
					
				Properties nextBookData = (Properties)allDataRetrieved.elementAt(i);
				Book book = new Book(nextBookData);
					
				if (book != null)
					addBook(book);
					
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Books found with an author like: " + author + "\n");
		}
			
	}
	
	//----------------------------------------------------------
	// Method to create a new BookCollectionView
	//----------------------------------------------------------
	public void createAndShowBookCollectionView() {
		
		Scene currentScene = (Scene)myViews.get("BookCollectionView");
		
		if (currentScene == null) {
			
			View newView = new BookCollectionView(this);
			currentScene = new Scene(newView);
			myViews.put("BookCollectionView", currentScene);
		}
		swapToView(currentScene);
	}
	
	//----------------------------------------------------------
	// Method called by BookCollectionView to return to LibrarianView
	//----------------------------------------------------------
	public void done() {
			myLibrarian.transactionDone();
	}

	
	//----------------------------------------------------------
	// Method to print the Books in the Vector - used just for the tester
	//----------------------------------------------------------
	public void printAllBooks() {
		int size = books.size();
		
		if (size == 0) {
			System.out.println("Books is empty");
		}	
		else {
			//System.out.println("Fields: "
			//		+ "Patron Id, Name, Address, City, State, Zip, Email, DoB, Status");
			for (int i = 0; i < size; i++) {
				Book b = books.elementAt(i);
				System.out.println(b.toString());
			}
		}
		
	}
	
	//----------------------------------------------------------
	// Method to initialize schema
	//----------------------------------------------------------
	protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
		
	}

	//----------------------------------------------------------
	// Abstract Methods
	//----------------------------------------------------------
	public Object getState(String criteria) {
	
			if (criteria.equals("Books"))
				return books;
			else
			if (criteria.equals("BookList"))
				return this;
			
			return null;
	}
	
	public void stateChangeRequest(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
