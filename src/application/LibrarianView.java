package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import impresario.IModel;
import userinterface.View;

/**
 * LibrarianView - GUI created by our Main Interface Agent Librarian. "Main Menu" of our system
 * 
 * Amanda Stevens & Ryan Tampone
 * CSC 429 - Assignment 2
 * Spring 2016
 * 
 * 
 * 	Constructors:
 *	Methods:
 */


public class LibrarianView extends View {
	
	
	//Declare GUI Components Here
	protected Librarian librarian; 
	protected Button insertNewBook;
	protected Button insertNewPatron;
	protected Button searchBooks;
	protected Button done;
	
	//----------------------------------------------------------
	//Constructor for LibrarianView -- takes a model object
	//----------------------------------------------------------
		public LibrarianView(Librarian lib) {
			super(lib, "LibrarianView");
		
		librarian = lib;
		
		// Create the container for showing our contents
		VBox container = new VBox(10);					//VBox with a spacing of 10
		container.setPadding(new Insets(15, 5, 5, 5));	//Padding
				
		//We write methods below to create our things, when finished we must add them as seen below
		container.getChildren().add(createTitle());			
		container.getChildren().add(createFormContent());
				
		getChildren().add(container);
	}
	
	//----------------------------------------------------------
	//Creates the title of our GUI
	//----------------------------------------------------------	
	private Node createTitle() {
		
		HBox container = new HBox();
		container.setPadding(new Insets(10, 0, 0, 0));
		container.setAlignment(Pos.CENTER);
			
		//Change below if desired
		Text titleText = new Text("LIBRARY SYSTEM");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKOLIVEGREEN);
		container.getChildren().add(titleText);
				
		return container;
	}
		
		
	private VBox createFormContent() {
		//All non-title GUI stuff goes inside here
		
		VBox formContainer = new VBox(15);
		
		VBox vbox = new VBox(15);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(30, 10, 50, 10));
		insertNewBook = new Button("Insert New Book");
		insertNewPatron = new Button("Insert New Patron");
		searchBooks = new Button("Search Books");
		done = new Button("Done");
		
		VBox spacing = new VBox(15);
		spacing.setAlignment(Pos.CENTER);
		spacing.setPadding(new Insets(0, 10, 20, 10));
		
		//Lambda expressions to add ActionEvents to Buttons:
		/**
		 * Here is an example of ActionEvents in the case I don't use Lambda:
		done.setOnAction(
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					librarian.closeProgram();
				}
			});
		*/
		
		done.setOnAction(e -> {
			librarian.closeProgram();
		});

		insertNewBook.setOnAction(e -> {	//Not finished
			librarian.createNewBook();
		});
		
		insertNewPatron.setOnAction(e -> {	//Not finished
			librarian.createNewPatron();
		});
		
		searchBooks.setOnAction(e -> {		//Not Started
			librarian.titleSearch();
		});
		
		
		//Adding the Buttons to respective layouts
		spacing.getChildren().add(done);
		vbox.getChildren().addAll(insertNewBook, insertNewPatron, searchBooks);
		formContainer.getChildren().add(vbox);
		formContainer.getChildren().add(spacing);
		
		return formContainer;
	}
	
	//----------------------------------------------------------
	//Abstract Methods inherited from View - Leave Blank
	//----------------------------------------------------------	
	public void updateState(String arg0, Object arg1) {
	}

}
