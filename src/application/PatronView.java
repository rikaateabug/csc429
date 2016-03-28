package application;

import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import userinterface.MessageView;
import userinterface.View;

/**
 * PatronView - GUI, created in the case of the user inserting a new Patron
 * 
 * Amanda Stevens & Ryan Tampone
 * CSC 429 - Assignment 2
 * Spring 2016
 * 
 * 
 * 	Constructors:
 *	Methods:
 */


public class PatronView extends View {

	//GUI stuff declared here
	
	private MessageView statusLog;
	
	protected TextField patronName;
	protected TextField address;
	protected TextField city;
	protected TextField stateCode;
	protected TextField zip;
	protected TextField email;
	protected TextField dateOfBirth;
	protected Button cancelButton;
	protected Button submitButton;
	protected Patron myPatron;
	
	protected Label nameLabel;
	protected Label addressLabel;
	protected Label cityLabel;
	protected Label stateCodeLabel;
	protected Label zipLabel;
	protected Label emailLabel;
	protected Label dobLabel;
	
	
	
	//----------------------------------------------------------
	//Constructor for PatronView -- takes a model object
	//----------------------------------------------------------
	public PatronView(Patron patron) {
		super(patron, "PatronView");
		
		myPatron = patron;
		// Should be similar to BookView
		// Create the container for showing our contents
		VBox container = new VBox(10);					//VBox with a spacing of 10
		container.setPadding(new Insets(15, 5, 5, 5));	//Padding
					
		//We write methods below to create our things, when finished we must add them as seen below
		container.getChildren().add(createTitle());			
		container.getChildren().add(createFormContent());
						
		//Error message area
		container.getChildren().add(createStatusLog(""));
		
		getChildren().add(container);

	}

	//----------------------------------------------------------
	//Creates the title of our GUI
	//----------------------------------------------------------	
	private Node createTitle() {
	
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);
		
		//CHANGE ALL THIS STUFF - ITS COPIED FROM MITRA
		Text titleText = new Text("Enter Patron Information:");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKOLIVEGREEN);
		container.getChildren().add(titleText);
			
		return container;
	}
	
	
	private VBox createFormContent() {
		//All GUI stuff goes inside here
		
		VBox formContainer = new VBox(15);
		VBox vbox = new VBox(15);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(30, 10, 50, 10));
			
		nameLabel = new Label("Name:");
		addressLabel = new Label("Address:");
		cityLabel = new Label("City:");
		stateCodeLabel = new Label("State:");;
		zipLabel = new Label("Zip Code:");
		emailLabel = new Label("Email:");
		dobLabel = new Label("Date of Birth:");
		
		patronName = new TextField();
		address = new TextField();
		city = new TextField();
		stateCode = new TextField();
		zip = new TextField();
		email = new TextField();
		dateOfBirth = new TextField("YYYY-MM-DD Format");
		cancelButton = new Button("Cancel");
		submitButton = new Button("Submit");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
	    grid.setHgap(30);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
		
		grid.add(nameLabel, 0, 0);
		grid.add(patronName, 1, 0);
		grid.add(addressLabel, 0, 1);
		grid.add(address, 1, 1);
		grid.add(cityLabel, 0, 2);
		grid.add(city, 1, 2);
		grid.add(stateCodeLabel, 0, 3);
		grid.add(stateCode, 1, 3);
		grid.add(zipLabel, 0, 4);
		grid.add(zip, 1, 4);
		grid.add(emailLabel, 0, 5);
		grid.add(email, 1, 5);
		grid.add(dobLabel, 0, 6);
		grid.add(dateOfBirth, 1, 6);
		
		HBox spacing = new HBox(15);
		spacing.setAlignment(Pos.CENTER);
		spacing.setPadding(new Insets(0, 10, 20, 10));
						
		//Lambda expressions to add ActionEvents to Buttons:
		cancelButton.setOnAction(e -> {
			myPatron.done();
		});
	
		submitButton.setOnAction(e2 -> {
			
			clearErrorMessage();
			
			Properties p = new Properties();
						
			if (patronName.getText().isEmpty()) {
				displayErrorMessage("Please enter the Patron Name");
				patronName.requestFocus();
			}
			else if (address.getText().isEmpty()){
				displayErrorMessage("Please enter the Address");
				address.requestFocus();
			}
			else if (city.getText().isEmpty()){
				displayErrorMessage("Please enter the City");
				city.requestFocus();
			}
			else if (stateCode.getText().isEmpty()){
				displayErrorMessage("Please enter the State code");
				stateCode.requestFocus();
			}
			else if (zip.getText().isEmpty()){
				displayErrorMessage("Please enter the Zip code");
				zip.requestFocus();
			}
			else if (email.getText().isEmpty()){
				displayErrorMessage("Please enter the Email");
				email.requestFocus();
			}
			else if (dateOfBirth.getText().isEmpty()){
				displayErrorMessage("Please enter the date of birth");
				dateOfBirth.requestFocus();
			}
			else if (!(dateOfBirth.getText().matches("\\d{4}-\\d{2}-\\d{2}"))){
				displayErrorMessage("Date Invalid, use: YYYY-MM-DD");
				dateOfBirth.requestFocus();
			}
			else {
				p.setProperty("name", patronName.getText());
				p.setProperty("address", address.getText());
				p.setProperty("city", city.getText());
				p.setProperty("stateCode", stateCode.getText());
				p.setProperty("zip", zip.getText());
				p.setProperty("email", email.getText());
				p.setProperty("dateOfBirth", dateOfBirth.getText());
				myPatron.setPatronProperties(p);
				myPatron.save();
				statusLog.displayMessage("Patron successfully inserted!");
			}
			
		});
			
		spacing.getChildren().addAll(submitButton, cancelButton);
		
		formContainer.getChildren().add(grid);
		formContainer.getChildren().add(spacing);
		return formContainer;		
	}	
	
	private MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);
		return statusLog;
	}
	
	private void clearErrorMessage() {
		statusLog.clearErrorMessage();
	}
	
	private void displayErrorMessage(String message) {
		statusLog.displayErrorMessage(message);
	}
	//----------------------------------------------------------
	//Abstract Methods inherited from View - Leave Blank
	//----------------------------------------------------------
	public void updateState(String key, Object value) {
	}

}