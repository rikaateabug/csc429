package application;

import userinterface.MainStageContainer;
import userinterface.WindowPosition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Tester class for Assignment 2
 * 
 *
 */

public class LibrarySystem extends Application {

	private Librarian theLibrarian;

	private Stage mainStage;
	
	public void start(Stage primaryStage) {
		
		MainStageContainer.setStage(primaryStage, "Library System");
		mainStage = MainStageContainer.getInstance();
		
		mainStage.setOnCloseRequest(new EventHandler <javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                System.exit(0);
            }
           });
		
		try {
			theLibrarian = new Librarian();
		}
		catch (Exception e) {
			System.out.println("Could not create Librarian");
			e.printStackTrace();
		}
		
		WindowPosition.placeCenter(mainStage);
		mainStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
