package application;

import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;

public class Tester {

	
	//Note that in order to connect to the Database the dbConfig file 
	//should be in the root of the project directory (workspace - csc429, NOT BIN OR SRC)
	public static void main(String [] args) throws InvalidPrimaryKeyException {
		
/////////////////////////////////////////////////////////////////////////////////////////////////////
		//OLD TEST STUFF
		//Book b = new Book("2");
		//System.out.println(b.toString());
		
		/**	FOR INSERTING A NEW BOOK
		 * Ensure that the bookId field is omitted OR
		 * the bookId is given an id.
		Properties newBook = new Properties();
		newBook.setProperty("status", "out");
		//newBook.setProperty("bookId", "3");
		newBook.setProperty("author", "Danny W");
		newBook.setProperty("title", "Some");
		newBook.setProperty("pubYear", "2016");
		
		Book b = new Book(newBook);
		b.insertNewBook();
		*/
		
		//Testing Patron Collection
		//PatronCollection pC = new PatronCollection();
		//pC.findPatronsWithNameLike("J");
		//pC.findPatronsAtZipCode("14420");
		//pC.findPatronsYoungerThan("1970-00-00");
		//pC.printAllPatrons();
		
		/**
		//For Updating a Book
		Properties newBookVals = new Properties();
		newBookVals.setProperty("status", "out");
		newBookVals.setProperty("author", "Joe Something");
		newBookVals.setProperty("title", "Some");
		newBookVals.setProperty("pubYear", "2017");
		
		Book oldBook = new Book("2");
		System.out.println(oldBook.toString());
		
		oldBook.updateBookInDatabase(newBookVals);
		System.out.println(oldBook.toString());
		*/
/////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		Scanner sc = new Scanner(System.in);
		/**
		//----------------------------------------------------------	
		//	Test Case 1: Books matching part of a given title
		//----------------------------------------------------------
		System.out.println("Test 1: Print Book records matching a given title\n");
		System.out.print("Enter the part of the book title: ");
		String title = sc.next();
		BookCollection bc = new BookCollection();
		bc.findBooksWithTitleLike(title);
		bc.printAllBooks();
		*/
		//----------------------------------------------------------	
		//	Test Case 2: Books Published before a given year
		//----------------------------------------------------------
		//System.out.println("Test 2: Print Book records published before a given year\n");
		//System.out.print("Enter the year: ");
		//String year = sc.nextLine();
		//BookCollection bc2 = new BookCollection();
		//bc2.findBooksOlderThanDate(year);
		//bc2.printAllBooks();
		
		//----------------------------------------------------------	
		//	Test Case 3: Patrons younger than a given date
		//----------------------------------------------------------
		//System.out.println("Test 3: Print Patron records younger than a given date\n");
		//System.out.print("Enter the date (YYYY-MM-DD): ");
		//String date = sc.next();
		//PatronCollection pc = new PatronCollection();
		//pc.findPatronsYoungerThan(date);
		//pc.printAllPatrons();
				
		//----------------------------------------------------------	
		//	Test Case 4: Patrons with a living in a given zip code
		//----------------------------------------------------------
		//System.out.println("Test 4: Print Patron records with a given zip code\n");
		//System.out.print("Enter the zip: ");
		//String zip = sc.next();
		//PatronCollection pc2 = new PatronCollection();
		//pc2.findPatronsAtZipCode(zip);
		//pc2.printAllPatrons();
		/**		
		//----------------------------------------------------------	
		//	Test Case 5: Transaction records with bookId and dateOfTrans 
		// Given a bookId and dateOfTrans, show me all transaction data related to that bookId and date.
		//----------------------------------------------------------
		System.out.println("Test 5: Print Transaction records related to a given bookId and dateOfTrans\n");
		System.out.print("Enter the bookId: ");
		String bookId = sc.nextLine();
		System.out.print("Enter the dateOfTrans: ");
		String dateOfTrans = sc.next();
		TransactionCollection tc = new TransactionCollection();
		String patronId = "null";
		tc.findMatchingTransactions(bookId, patronId, dateOfTrans);
		tc.printAllTransactions();
		*/
		
		//----------------------------------------------------------	
		//	Insert a new Book - Done
		//----------------------------------------------------------
		/**
		System.out.println("Insert a new Book\n");
		System.out.println("Enter the author: ");
		String author = sc.nextLine();
		System.out.println("Enter the title: ");
		String title = sc.nextLine();
		System.out.println("Enter the publish year: ");
		String year = sc.nextLine();
		
		Properties newBookVals = new Properties();
		newBookVals.setProperty("status", "out");
		newBookVals.setProperty("author", author);
		newBookVals.setProperty("title", title);
		newBookVals.setProperty("pubYear", year);
		Book b = new Book(newBookVals);
		b.save();
		*/
		
		//----------------------------------------------------------	
		//	Insert a new Patron - Not Done
		//----------------------------------------------------------
		/**
		System.out.println("Insert a new Book\n");
		System.out.println("Enter the author: ");
		String author = sc.nextLine();
		System.out.println("Enter the title: ");
		String title = sc.nextLine();
		System.out.println("Enter the publish year: ");
		String year = sc.nextLine();
		
		Properties newBookVals = new Properties();
		newBookVals.setProperty("status", "out");
		newBookVals.setProperty("author", author);
		newBookVals.setProperty("title", title);
		newBookVals.setProperty("pubYear", year);
		Book b = new Book(newBookVals);
		b.save();
		*/
		
		
		
		//----------------------------------------------------------	
		//	Insert a new Transaction - Not Done
		//----------------------------------------------------------
		/**
		System.out.println("Insert a new Book\n");
		System.out.println("Enter the author: ");
		String author = sc.nextLine();
		System.out.println("Enter the title: ");
		String title = sc.nextLine();
		System.out.println("Enter the publish year: ");
		String year = sc.nextLine();
		
		Properties newBookVals = new Properties();
		newBookVals.setProperty("status", "out");
		newBookVals.setProperty("author", author);
		newBookVals.setProperty("title", title);
		newBookVals.setProperty("pubYear", year);
		Book b = new Book(newBookVals);
		b.save();
		*/
		
		
		System.out.println("Test 2: Print Book records published before a given year\n");
		System.out.print("Enter the year: ");
		String year = sc.nextLine();
			if (year.isEmpty())
				System.out.println("Yay");
			else
				System.out.println("Boo");
		
	}
	
}
