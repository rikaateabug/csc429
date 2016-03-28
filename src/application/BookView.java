package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Properties;

import impresario.IModel;
import userinterface.View;



public class BookView extends View {

	
	// GUI components
	protected Button cancelButton;
		
	//////////////////////////////////////////
	protected TextField publicationYear;
	protected TextField bookTitle;
	protected TextField author;
	//protected ComboBox status;
	protected Button submit;
	protected Button cancel;
	protected RadioButton activeStatus;
	protected RadioButton inactiveStatus;
	protected Label statusLabel;
	//////////////////////////////////////////
	protected Label bookTitleLabel;
	protected Label pubYearLabel;
	protected Label authorNameLabel;
	
	
	protected Book myBook;
		
	//----------------------------------------------------------
	//Constructor for BookView -- takes a model object
	//----------------------------------------------------------
	public BookView(Book book) {
		super(book, "BookView");
	
		myBook = book;
		
		// Create the container for showing our contents
		VBox container = new VBox(10);					//VBox with a spacing of 10
		container.setPadding(new Insets(15, 5, 5, 5));	//Padding
		
		// Adds a title for this panel (top of the screen)
		container.getChildren().add(createTitle());		// See method below
		
		container.getChildren().add(createFormContent());
		
		getChildren().add(container);
		
	}

	//----------------------------------------------------------
	//Creates the title of our GUI
	//----------------------------------------------------------	
	private Node createTitle() {
	
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);
		
		Text titleText = new Text("Book Information");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKOLIVEGREEN);
		container.getChildren().add(titleText);
			
		return container;
	}
	
	private VBox createFormContent() {
		//All GUI stuff goes inside here
		//All non-title GUI stuff goes inside here
		
			VBox formContainer = new VBox(15);
			VBox vbox = new VBox(15);
			vbox.setAlignment(Pos.CENTER);
			vbox.setPadding(new Insets(30, 10, 20, 10));
			
			publicationYear = new TextField();
			bookTitle = new TextField();
			author = new TextField();
			cancel = new Button("Cancel");
			submit = new Button("Submit");
			
			// We could use a ComboBox, but RadioButtons sure do look fancy
			//status = new ComboBox();
			//status.getItems().addAll("Active" , "Inactive");
			
			//Radio Buttons must be in a group as to not enable multiple ones to be selected
			final ToggleGroup group = new ToggleGroup();
			statusLabel = new Label("Status:");
			activeStatus = new RadioButton("Active");
			activeStatus.setToggleGroup(group);
			inactiveStatus = new RadioButton("Inactive");
			inactiveStatus.setToggleGroup(group);
			
			/////////////////////////////////////////////////////////////
			//We may not use these
			bookTitleLabel = new Label("Title:");
			authorNameLabel = new Label("Author:");
			pubYearLabel = new Label("Publication Year:");
			//////////////////////////////////////////////////////////////
			
			HBox spacing = new HBox(15);
			spacing.setAlignment(Pos.CENTER);
			spacing.setPadding(new Insets(0, 10, 20, 10));
			HBox spacing2 = new HBox(15);
			spacing2.setAlignment(Pos.CENTER);
			spacing2.setPadding(new Insets(0, 10, 40, 10));
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
		    grid.setHgap(30);
		    grid.setVgap(10);
		    grid.setPadding(new Insets(25, 25, 25, 25));
			
			grid.add(bookTitleLabel, 0, 0);
			grid.add(bookTitle, 1, 0);
			grid.add(authorNameLabel, 0, 1);
			grid.add(author, 1, 1);
			grid.add(pubYearLabel, 0, 2);
			grid.add(publicationYear, 1, 2);
			
			
			//Lambda expressions to add ActionEvents to Buttons

				cancel.setOnAction(e -> {
					myBook.done();
				});
				submit.setOnAction(e -> {
					Properties p = new Properties();
					p.setProperty("title", bookTitle.getText());
					p.setProperty("author", author.getText());
					p.setProperty("pubYear", publicationYear.getText());
					
					//Change Status to work with radio buttons
					//p.setProperty("status", bookTitle.getText());
					if (activeStatus.isSelected() == true)
						p.setProperty("", "Active");
					else if (inactiveStatus.isSelected() == true)
						p.setProperty("", "Inactive");
					else
						System.out.println("Select an active status");
					//Make sure to change this to show a MessageView instead
					
					myBook.setBookProperties(p);
					myBook.save();
				});
				
				//Adding the text fields to respective layouts
				spacing2.getChildren().addAll(statusLabel, activeStatus, inactiveStatus);
				spacing.getChildren().addAll(submit, cancel);
				

				formContainer.getChildren().add(grid);
				formContainer.getChildren().add(spacing2);
				formContainer.getChildren().add(spacing);
				return formContainer;
	}
	
	//----------------------------------------------------------
	//Abstract Methods inherited from View - Leave Blank
	//----------------------------------------------------------
	public void updateState(String arg0, Object arg1) {
		// TODO Auto-generated method stub
	}	
}