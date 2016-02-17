package application;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.EntityBase;

/**
 * Patron
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


public class Patron extends EntityBase 
{

	private static final String tableName = "Patron";
	protected Properties dependencies;
	
	//----------------------------------------------------------
	//Constructor taking in new data to create a new Patron
	//----------------------------------------------------------
	public Patron(Properties patronProps) 
	{
		super(tableName);
		persistentState = new Properties();
		Enumeration allKeys = patronProps.propertyNames();
		
		while (allKeys.hasMoreElements() == true) 
		{
			
			//nextKey are the column names, nextValue are the corresponding row values
			String nextKey = (String)allKeys.nextElement();
			String nextValue = patronProps.getProperty(nextKey);
			
			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
		
	}
	
	//----------------------------------------------------------
	//Constructor to instantiate a patron from primary key
	//----------------------------------------------------------
	public Patron(String primaryKey) throws InvalidPrimaryKeyException 
	{
		super(tableName);
		
		String query = "SELECT * FROM " + tableName + " WHERE (patronId=" + primaryKey + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
		
		//Ensuring we have at least one result
		if (allDataRetrieved != null) 
		{
			
			int size = allDataRetrieved.size();
			
			//There should only be one record with the same primary key
			if (size != 1) 
			{
				throw new InvalidPrimaryKeyException("Multiple Patrons found for this id: " + primaryKey + "\n");
			}
			else
			{
				
				Properties retrievedPatronData = allDataRetrieved.elementAt(0);
				//PersistentState acts as a "deep copy"
				persistentState = new Properties();
				
				
				// propertyNames() - Returns an enumeration of all the keys in this property list, including distinct keys 
				//in the default property list if a key of the same name has not already been found from
				//the main properties list.
				Enumeration allKeys = retrievedPatronData.propertyNames();
				
				//Can this be rewritten as a for each loop?
				while (allKeys.hasMoreElements() == true) 
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedPatronData.getProperty(nextKey);
					
					//Testing Print Statements
					//System.out.println(nextKey);
					//System.out.println(nextValue);
					
					
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
			throw new InvalidPrimaryKeyException("No Patron found with the id: " + primaryKey + "\n");
		}
		
	}

	//----------------------------------------------------------
		public void save()
		{
			if (persistentState.getProperty("patronId") != null) {
				updatePatronInDatabase();	
			}
			else {
				insertNewPatron();
			}
		}

	//----------------------------------------------------------
	//Inserting a new Patron record into the database
	//----------------------------------------------------------
	public void insertNewPatron()
	{		
		try 
		{
			Integer i = insertAutoIncrementalPersistentState(mySchema, persistentState);	
			System.out.println(i + " is the primary key of the new Patron");
		}
		catch (SQLException e) {
			System.out.println("Error inserting new Patron into the database" + e.toString());
		}
	}

		
	//----------------------------------------------------------
	//Updating an existing Patron record in the database
	//----------------------------------------------------------
	public void updatePatronInDatabase() 
	{
		try 
		{
			Properties whereClause = new Properties();
			whereClause.setProperty("patronId", persistentState.getProperty("patronId"));
			Integer i = updatePersistentState(mySchema, persistentState, whereClause);	
			
		}
		catch (SQLException e) {
			System.out.println("Error updating Patron in the database" + e.toString());
		}
	}

	//----------------------------------------------------------
	// Accessor Method
	//----------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();
			
		v.addElement(persistentState.getProperty("patronId"));
		v.addElement(persistentState.getProperty("name"));
		v.addElement(persistentState.getProperty("address"));
		v.addElement(persistentState.getProperty("city"));
		v.addElement(persistentState.getProperty("stateCode"));
		v.addElement(persistentState.getProperty("zip"));
		v.addElement(persistentState.getProperty("email"));
		v.addElement(persistentState.getProperty("dateOfBirth"));
		v.addElement(persistentState.getProperty("status"));
		
		return v;
	}	
		
	//----------------------------------------------------------
	// Method used in the tester class
	//----------------------------------------------------------
	public String toString() {
			
			String s = persistentState.getProperty("patronId") + " \t" + 
					   persistentState.getProperty("name") + " \t" +
					   persistentState.getProperty("address") + " \t" +
					   persistentState.getProperty("city") + " \t" +
					   persistentState.getProperty("stateCode") + " \t" + 
					   persistentState.getProperty("zip") + " \t" +
					   persistentState.getProperty("email") + " \t" +
					   persistentState.getProperty("dateOfBirth") + " \t" +
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
