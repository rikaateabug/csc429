package application;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.EntityBase;

public class PatronCollection extends EntityBase {

	
	private static final String tableName = "Patron";
	private Vector<Patron> patrons;
	
	//----------------------------------------------------------	
	//Constructor sets up a blank Vector
	//----------------------------------------------------------
	public PatronCollection() {
		super(tableName);
		patrons = new Vector<Patron>();
	}
	
	//----------------------------------------------------------	
	//Adds patrons to the Vector
	//----------------------------------------------------------
	private void addPatron(Patron p) {
		patrons.add(p);
	}
	
	
	//----------------------------------------------------------	
	//Finding a Patron older than supplied date.
	//----------------------------------------------------------
	public void findPatronsOlderThan(String date) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (dateOfBirth < " + date + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextPatronData = (Properties)allDataRetrieved.elementAt(i);
				Patron patron = new Patron(nextPatronData);
				
				if (patron != null)
					addPatron(patron);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Patrons found older than: " + date + "\n");
		}
	}
	
	//----------------------------------------------------------	
	//Finding a Patron younger than supplied date.
	//----------------------------------------------------------
	public void findPatronsYoungerThan(String date) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (dateOfBirth > " + date + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextPatronData = (Properties)allDataRetrieved.elementAt(i);
				Patron patron = new Patron(nextPatronData);
				
				if (patron != null)
					addPatron(patron);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Patrons found younger than: " + date + "\n");
		}
		
	}
	
	//----------------------------------------------------------	
	//Finding a Patron with the provided zip code
	//----------------------------------------------------------
	public void findPatronsAtZipCode(String zip) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (zip=" + zip + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextPatronData = (Properties)allDataRetrieved.elementAt(i);
				Patron patron = new Patron(nextPatronData);
				
				if (patron != null)
					addPatron(patron);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Patrons found with the zip: " + zip + "\n");
		}
				
	}
	
	//----------------------------------------------------------	
	//Finding a Patron with a name similar to the supplied one.
	//----------------------------------------------------------
	public void findPatronsWithNameLike(String name) throws InvalidPrimaryKeyException {
		String query = "SELECT * FROM " + tableName + " WHERE (name LIKE '%" + name + "%')";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextPatronData = (Properties)allDataRetrieved.elementAt(i);
				Patron patron = new Patron(nextPatronData);
				
				if (patron != null)
					addPatron(patron);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Patrons found with a name like: " + name + "\n");
		}
						
	}
	
	
	//----------------------------------------------------------
	// Method to print the Patrons in the Vector - used just for the tester
	//----------------------------------------------------------
	public void printAllPatrons() {
		int size = patrons.size();
		
		if (size == 0) {
			System.out.println("Patrons is empty");
		}	
		else {
			System.out.println("Fields: "
					+ "Patron Id, Name, Address, City, State, Zip, Email, DoB, Status");
			for (int i = 0; i < size; i++) {
				Patron p = patrons.elementAt(i);
				System.out.println(p.toString());
			}
		}
		
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
	// Abstract Methods
	//----------------------------------------------------------
	public Object getState(String criteria) {
		if (criteria.equals("PatronList"))
			return this;
		
		return null;
	}
	public void stateChangeRequest(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
