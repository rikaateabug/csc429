package application;

import impresario.IModel;
import impresario.IView;

import java.util.Hashtable;

import exception.InvalidPrimaryKeyException;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Librarian - Main Interface Agent, called by our main program and creates an instance of LibrarianView
 * 
 * Amanda Stevens & Ryan Tampone
 * CSC 429 - Assignment 2
 * Spring 2016
 * 
 * 
 * 	Constructors:
 *	Methods:
 */

public class Librarian implements IModel {

	//GUI Components
	private Stage myStage;
	private Hashtable<String, Scene> myViews;
	
	
	//----------------------------------------------------------
	//Constructor
	//----------------------------------------------------------
	public Librarian() {
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();
		
		
		createAndShowLibrarianView();
		//createAndShowBookView();	//Comment and uncomment to test the various views
	}
	
	//----------------------------------------------------------
	//Method called by constructor, creates/shows LibrarianView
	//----------------------------------------------------------
	private void createAndShowLibrarianView() {
		
		Scene currentScene = (Scene)myViews.get("LibrarianView");
		
		if (currentScene == null) {
			
			//Create our initial view
			
			View newView = new LibrarianView(this);
			
			currentScene = new Scene(newView);
			myViews.put("LibrarianView", currentScene);
		}
		swapToView(currentScene);
	}


	//----------------------------------------------------------
	//Creates/show BookSearchView
	//----------------------------------------------------------
	private void createAndShowBookSearchView() {
			
		Scene currentScene = (Scene)myViews.get("BookSearchView");
			
		if (currentScene == null) {
			View newView = new BookSearchView(this);
			currentScene = new Scene(newView);
			myViews.put("BookSearchView", currentScene);
		}
		swapToView(currentScene);
	}
	
	//----------------------------------------------------------
	//Create new book - Called when "Insert new book" is clicked in LibrarianView
	//----------------------------------------------------------	
	public void createNewBook() {
		Book b = new Book(this);
		b.createAndShowBookView();
	}

	//----------------------------------------------------------
	//Return to LibrarianView from the BookSearchView
	//----------------------------------------------------------	
	public void back() {
		Book b = new Book(this);
		createAndShowLibrarianView();
	}
	
	//----------------------------------------------------------
	//Create new patron - Called when "Insert new patron" is clicked in LibrarianView
	//----------------------------------------------------------	
	public void createNewPatron() {
		Patron p = new Patron(this);
		p.createAndShowPatronView();
	}	
	
	//----------------------------------------------------------
	//Search Books - Called when "Search Books" is clicked in LibrarianView
	//----------------------------------------------------------	
	public void titleSearch() {
		createAndShowBookSearchView();
	}
	
	//----------------------------------------------------------
	//Search Books - Called when "Submit" is clicked in BookSearchView
	//----------------------------------------------------------	
	public void searchBooks(String bookTitle) {
		BookCollection bc = new BookCollection(this);
		
		try {
			bc.findBooksWithTitleLike(bookTitle);
		} catch (InvalidPrimaryKeyException e) {
			e.printStackTrace();
		}
		
		bc.createAndShowBookCollectionView();
	}	
	
	//----------------------------------------------------------
	//Called when user is finished - closes the program
	//----------------------------------------------------------	
	public void closeProgram() {
		Platform.exit();
	}
	
	
	//----------------------------------------------------------
	//Changes our View - Called by previous method
	//----------------------------------------------------------
	public void swapToView(Scene newScene) {
	
		if (newScene == null) {
			System.out.println("Librarian.swapToView(): Missing view for display");
		}
		
		myStage.setScene(newScene);
		myStage.sizeToScene();
		
		//Center our window
		WindowPosition.placeCenter(myStage);
	}
	
	//----------------------------------------------------------
	//Method Called by Book/Patron to return to LibrarianView
	//----------------------------------------------------------
	public void transactionDone() {
		createAndShowLibrarianView();
	}
	
	
	//----------------------------------------------------------
	//Abstract Methods inherited from IView - Leave Blank
	//----------------------------------------------------------
	public Object getState(String arg0) {
		return null;
	}
	public void stateChangeRequest(String arg0, Object arg1) {
	}
	public void subscribe(String arg0, IView arg1) {
	}
	public void unSubscribe(String arg0, IView arg1) {	
	}
	
	
}
