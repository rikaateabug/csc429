package application;

import java.sql.SQLException;

/**
 * Transaction
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

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.EntityBase;

public class Transaction extends EntityBase 
{

	private static final String tableName = "Transaction";
	protected Properties dependencies;
	
	//----------------------------------------------------------
	//Constructor taking in new data to create a new Book
	//----------------------------------------------------------
	public Transaction(Properties transactionProps) 
	{
		super(tableName);
		persistentState = new Properties();
		Enumeration allKeys = transactionProps.propertyNames();
		
		while (allKeys.hasMoreElements() == true) 
		{
			
			//nextKey are the column names, nextValue are the corresponding row values
			String nextKey = (String)allKeys.nextElement();
			String nextValue = transactionProps.getProperty(nextKey);
			
			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
		
	}
	
	//----------------------------------------------------------
	//Constructor to instantiate a book from primary key
	//----------------------------------------------------------
	public Transaction(String primaryKey) throws InvalidPrimaryKeyException 
	{
		super(tableName);
		
		String query = "SELECT * FROM " + tableName + " WHERE (transactionId=" + primaryKey + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
		
		//Ensuring we have at least one result
		if (allDataRetrieved != null) 
		{
			
			int size = allDataRetrieved.size();
			
			//There should only be one record with the same primary key
			if (size != 1) 
			{
				throw new InvalidPrimaryKeyException("Multiple Transactions found under this ID: " + primaryKey + "\n");
			}
			else
			{
				
				Properties retrievedTransactionData = allDataRetrieved.elementAt(0);
				//PersistentState acts as a "deep copy"
				persistentState = new Properties();
				
				
				// propertyNames() - Returns an enumeration of all the keys in this property list, including distinct keys 
				//in the default property list if a key of the same name has not already been found from
				//the main properties list.
				Enumeration allKeys = retrievedTransactionData.propertyNames();
				
				while (allKeys.hasMoreElements() == true) 
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedTransactionData.getProperty(nextKey);
					
					//Testing Print Statements
					//System.out.println(nextKey);
					//System.out.println(nextValue);
					//
					
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
			throw new InvalidPrimaryKeyException("No Transaction found with the provided id: " + primaryKey + "\n");
		}
		
	}

	//----------------------------------------------------------
		public void save()
		{
			if (persistentState.getProperty("bookId") != null) {
				updateTransactionInDatabase();	
			}
			else {
				insertNewTransaction();
			}
		}
	
	
	//----------------------------------------------------------
	//Inserting a new Transaction record into the database
	//----------------------------------------------------------
	public void insertNewTransaction()
	{		
		try 
		{
			Integer i = insertAutoIncrementalPersistentState(mySchema, persistentState);	
			System.out.println(i + " is the primary key of the new Transaction");
		}
		catch (SQLException e) {
			System.out.println("Error inserting new Transaction into the database" + e.toString());
		}
	}

		
	//----------------------------------------------------------
	//Updating an existing Transaction record in the database
	//----------------------------------------------------------
	public void updateTransactionInDatabase() 
	{
		try 
		{
			Properties whereClause = new Properties();
			whereClause.setProperty("transId", persistentState.getProperty("transId"));
			Integer i = updatePersistentState(mySchema, persistentState, whereClause);	
				
		}
		catch (SQLException e) {
			System.out.println("Error updating Transaction in the database" + e.toString());
		}
	}
	
	//----------------------------------------------------------
	// Accessor Method
	//----------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();
				
		v.addElement(persistentState.getProperty("transId"));
		v.addElement(persistentState.getProperty("bookId"));
		v.addElement(persistentState.getProperty("patornId"));
		v.addElement(persistentState.getProperty("transType"));
		v.addElement(persistentState.getProperty("dateOfTrans"));
			
		return v;
	}
	
	//----------------------------------------------------------
	// Method used in the tester class
	//----------------------------------------------------------
	public String toString() {
			
		String s = persistentState.getProperty("transId") + " \t" + 
				   persistentState.getProperty("bookId") + " \t" +
				   persistentState.getProperty("patronId") + " \t" +
				   persistentState.getProperty("transType") + " \t" +
				   persistentState.getProperty("dateOfTrans") + " \t";
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
