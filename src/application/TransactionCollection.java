package application;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import model.EntityBase;

public class TransactionCollection extends EntityBase {

	
	private static final String tableName = "Transaction";
	private Vector<Transaction> transactions;
	
	//----------------------------------------------------------	
	//Constructor sets up a blank Vector
	//----------------------------------------------------------
	public TransactionCollection() {
		super(tableName);
		transactions = new Vector<Transaction>();
	}
	
	//----------------------------------------------------------	
	//Adds Transactions to the Vector
	//----------------------------------------------------------
	private void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	
	//----------------------------------------------------------	
	//Finding a Transaction with the given criteria
	// Warning - Any 3 of the criteria can be null
	//----------------------------------------------------------
	public void findMatchingTransactions(String bookId, String patronId, String dateOfTrans) throws InvalidPrimaryKeyException {
		
		String query ="";
		
		if ((bookId == null) && (patronId == null)) {
			query = "Select * from Transaction where dateOfTrans=" + dateOfTrans;
		}
		else if ((bookId == null) && (dateOfTrans == null)) {
			query = "Select * from Transaction where patronId=" + patronId;
		}
		else if ((patronId == null) && (dateOfTrans == null)) {
			query = "Select * from Transaction where bookId=" + bookId;
		}
		else if (bookId == null) {
			query = "Select * from Transaction where patronId=" + patronId + " AND dateOfTrans=" + dateOfTrans;
		}
		else if (patronId == null) {
			query = "Select * from Transaction where bookId=" + bookId + " AND dateOfTrans=" + dateOfTrans;
		}
		else if (dateOfTrans == null) {
			query = "Select * from Transaction where patronId=" + patronId + " AND bookId=" + bookId;
		}
		else {
			//Throw an exception
			throw new InvalidPrimaryKeyException("Invalid null values for parameters\n");
		}
		
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);	
		
		if (allDataRetrieved != null) {
			
			for (int i = 0; i < allDataRetrieved.size(); i++) {
				
				Properties nextTransactionData = (Properties)allDataRetrieved.elementAt(i);
				Transaction transaction = new Transaction(nextTransactionData);
				
				if (transaction != null)
					addTransaction(transaction);
				
			}
		}
		else {
			//We only have no results, throw an exception & inform user
			throw new InvalidPrimaryKeyException("No Transactions found matching the criteria\n");
		}
	}
		
	//----------------------------------------------------------
	// Method to print the Transactions in the Vector - used just for the tester
	//----------------------------------------------------------
	public void printAllTransactions() {
		int size = transactions.size();
		
		if (size == 0) {
			System.out.println("Transactions is empty");
		}	
		else {
			//System.out.println("Fields: "
			//		+ "Patron Id, Name, Address, City, State, Zip, Email, DoB, Status");
			for (int i = 0; i < size; i++) {
				Transaction t = transactions.elementAt(i);
				System.out.println(t.toString());
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
