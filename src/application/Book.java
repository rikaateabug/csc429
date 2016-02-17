package application;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import exception.InvalidPrimaryKeyException;
import model.EntityBase;

/**
 * Book
 * 
 * Amanda Stevens & Ryan Tampone
 * CSC 429 - Assignment 1
 * Spring 2016
 * 
 * 
 * 	Constructors:
 * 		- Creation of a new object from user supplied data		(DONE)
 *		- Instantiation from the database given a primary key	(DONE/TESTED)
 *
 *	Methods:
 *		- Insert a new object into the database			(DONE)
 *		- Update an existing object in the database		(DONE)
 *		- toString used simply for the tester class		(DONE)
 *
 *	(Inherited Methods from EntityBase, left undefined)
 */



public class Book extends EntityBase 
{

	private static final String tableName = "Book";
	protected Properties dependencies;
	
	//----------------------------------------------------------
	//Constructor taking in new data to create a new Book
	//----------------------------------------------------------
	public Book(Properties bookProps) 
	{
		super(tableName);
		persistentState = new Properties();
		Enumeration allKeys = bookProps.propertyNames();
		
		
		while (allKeys.hasMoreElements() == true) 
		{
			
			//nextKey are the column names, nextValue are the corresponding row values
			String nextKey = (String)allKeys.nextElement();
			String nextValue = bookProps.getProperty(nextKey);
			
			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
		
	}
	
	//----------------------------------------------------------
	//Constructor to instantiate a book from primary key
	//----------------------------------------------------------
	public Book(String primaryKey) throws InvalidPrimaryKeyException 
	{
		super(tableName);
				
		String query = "SELECT * FROM " + tableName + " WHERE (bookId=" + primaryKey + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
		
		//Ensuring we have at least one result
		if (allDataRetrieved != null) 
		{
			
			int size = allDataRetrieved.size();
			
			//There should only be one record with the same primary key
			if (size != 1) 
			{
				throw new InvalidPrimaryKeyException("Multiple Books found for this id: " + primaryKey + "\n");
			}
			else
			{
				
				Properties retrievedBookData = allDataRetrieved.elementAt(0);
				//PersistentState acts as a "deep copy"
				persistentState = new Properties();
				
				
				// propertyNames() - Returns an enumeration of all the keys in this property list, including distinct keys 
				//in the default property list if a key of the same name has not already been found from
				//the main properties list.
				Enumeration allKeys = retrievedBookData.propertyNames();
				
				
				while (allKeys.hasMoreElements() == true) 
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedBookData.getProperty(nextKey);
					
					
					if (nextValue != null) 
					{
						persistentState.setProperty(nextKey, nextValue);
					}
					
				}
			}
					
		}
		else 
		{
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Book found with the id: " + primaryKey + "\n");
		}
		
	}

	
	
	//----------------------------------------------------------
	public void save()
	{
		if (persistentState.getProperty("bookId") != null) {
			updateBookInDatabase();	
		}
		else {
			insertNewBook();
		}
	}
	
	//----------------------------------------------------------
	//Inserting a new Book record into the database
	//----------------------------------------------------------
	public void insertNewBook()
	{		
		try 
		{
			Integer i = insertAutoIncrementalPersistentState(mySchema, persistentState);	
			System.out.println(i + " is the primary key of the new Book");
		}
		catch (SQLException e) {
			System.out.println("Error inserting new Book into the database" + e.toString());
		}
	}

	
	//----------------------------------------------------------
	//Updating an existing Book record in the database
	//----------------------------------------------------------
	
	
	public void updateBookInDatabase() 
	{
		try 
		{
			Properties whereClause = new Properties();
			whereClause.setProperty("bookId", persistentState.getProperty("bookId"));
			Integer i = updatePersistentState(mySchema, persistentState, whereClause);	
			
		}
		catch (SQLException e) {
			System.out.println("Error updating Book in the database" + e.toString());
		}
	}
	
	//----------------------------------------------------------
	// Accessor Method
	//----------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();
		
		v.addElement(persistentState.getProperty("bookId"));
		v.addElement(persistentState.getProperty("author"));
		v.addElement(persistentState.getProperty("title"));
		v.addElement(persistentState.getProperty("pubYear"));
		v.addElement(persistentState.getProperty("status"));
		
		return v;
	}
	
	//----------------------------------------------------------
	// Method used in the tester class
	//----------------------------------------------------------
	public String toString() {
		
		String s = persistentState.getProperty("bookId") + " \t" + 
				   persistentState.getProperty("author") + " \t" +
				   persistentState.getProperty("title") + " \t" +
				   persistentState.getProperty("pubYear") + " \t" +
				   persistentState.getProperty("status") + " \t";
		return s;
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
	//Abstract Methods inherited from EntityBase - Leave Blank
	//----------------------------------------------------------
	public Object getState(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	public void stateChangeRequest(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
