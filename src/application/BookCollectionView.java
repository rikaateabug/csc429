package application;

import java.util.Enumeration;
import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * BookCollectionView - GUI, created when the user selects "search books", interacts closely with BookTableModel
 * 
 * Amanda Stevens & Ryan Tampone
 * CSC 429 - Assignment 2
 * Spring 2016
 * 
 * 
 */


public class BookCollectionView extends View {

	protected TableView<BookTableModel> tableOfBooks;
	protected BookCollection bookCollection;
	protected Button done;
	
	//----------------------------------------------------------
	//Constructor takes a BookCollection as a model
	//----------------------------------------------------------
	public BookCollectionView(BookCollection bc) {
		super(bc, "BookCollectionView");
		bookCollection = bc;
		
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());
		
		getChildren().add(container);
		populateFields();
	}
	
	//----------------------------------------------------------
	protected void populateFields() {
		getEntryTableModelValues();
	}

	
	//----------------------------------------------------------
	//Get the values for our table
	//----------------------------------------------------------
	protected void getEntryTableModelValues() {
		ObservableList<BookTableModel> tableData = FXCollections.observableArrayList();
		
		try {
			BookCollection bookCol = (BookCollection)myModel.getState("BookList");
			Vector entryList = (Vector)bookCol.getState("Books");
			Enumeration entries = entryList.elements();
			
			while (entries.hasMoreElements() == true) {
				Book nextBook = (Book)entries.nextElement();
				Vector<String> view = nextBook.getEntryListView();
				System.out.println(view.toString());
				
				//Add list entry to list
				BookTableModel nextTableRowData = new BookTableModel(view);
				tableData.add(nextTableRowData);
			}
			
			tableOfBooks.setItems(tableData);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text(" Books: ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKOLIVEGREEN);
		container.getChildren().add(titleText);
		
		return container;
	}
	
	private VBox createFormContent() {
		VBox vbox = new VBox();
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
       	grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text prompt = new Text("LIST OF BOOKS");
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);
		
        tableOfBooks = new TableView<BookTableModel>();
        tableOfBooks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        TableColumn bookIdColumn = new TableColumn("Book Id") ;
        bookIdColumn.setMinWidth(100);
        bookIdColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookId"));
        
        TableColumn bookTitleColumn = new TableColumn("Title") ;
        bookTitleColumn.setMinWidth(100);
        bookTitleColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookTitle"));
        
        TableColumn bookAuthorColumn = new TableColumn("Author") ;
        bookAuthorColumn.setMinWidth(100);
        bookAuthorColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookAuthor"));
        
        TableColumn bookPubYearColumn = new TableColumn("Publication Year") ;
        bookPubYearColumn.setMinWidth(100);
        bookPubYearColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookPubYear"));
        
        TableColumn bookStatusColumn = new TableColumn("Status") ;
        bookStatusColumn.setMinWidth(100);
        bookStatusColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookStatus"));
        
        tableOfBooks.getColumns().addAll(bookIdColumn, bookTitleColumn, bookAuthorColumn, bookPubYearColumn, bookStatusColumn);
		
        
        
        ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(115, 150);
		scrollPane.setContent(tableOfBooks);

        done = new Button("Done");
        done.setOnAction(e -> {
			bookCollection.done();
		});
        
        
        vbox.getChildren().add(grid);
		vbox.getChildren().add(scrollPane);
		vbox.getChildren().add(done);
        
		return vbox;
	}
	
	//----------------------------------------------------------
	//Abstract Methods inherited from View - Leave Blank
	//----------------------------------------------------------
	public void updateState(String arg0, Object arg1) {
	}

}
