package application;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

/**
 * BookTableModel - Used with BookCollectionView
 * 
 * Amanda Stevens & Ryan Tampone
 * CSC 429 - Assignment 2
 * Spring 2016
 * 
 * 
 * 	Constructors:
 *	Methods:
 */

public class BookTableModel {

	private final SimpleStringProperty bookId;
	private final SimpleStringProperty bookTitle;
	private final SimpleStringProperty bookAuthor;
	private final SimpleStringProperty bookPubYear;
	private final SimpleStringProperty bookStatus;
	
	//Constructor - Vectors maintain order, do NOT change the order of these statements
	public BookTableModel(Vector<String> bookData) {
		bookId = new SimpleStringProperty(bookData.elementAt(0));
		bookTitle = new SimpleStringProperty(bookData.elementAt(1));
		bookAuthor = new SimpleStringProperty(bookData.elementAt(2));
		bookPubYear = new SimpleStringProperty(bookData.elementAt(3));
		bookStatus = new SimpleStringProperty(bookData.elementAt(4));
	}
	//----------------------------------------------------------------------------
	//Accessor and Mutator methods?
	public String getBookId() {
		return bookId.get();
	}
	public void setBookId(String number) {
		bookId.set(number);
	}
	//----------------------------------------------------------------------------
	public String getBookTitle() {
		return bookTitle.get();
	}
	public void setBookTitle(String title) {
		bookTitle.set(title);
	}
	//----------------------------------------------------------------------------
	public String getBookAuthor() {
		return bookAuthor.get();
	}
	
	public void setBookAuthor(String author) {
		bookAuthor.set(author);
	}
	//----------------------------------------------------------------------------
	public String getBookPubYear() {
		return bookPubYear.get();
	}
	
	public void setBookPubYear(String year) {
		bookPubYear.set(year);
	}
	//----------------------------------------------------------------------------
	public String getBookStatus() {
		return bookStatus.get();
	}
	
	public void setBookStatus(String status) {
		bookStatus.set(status);
	}
	
}
