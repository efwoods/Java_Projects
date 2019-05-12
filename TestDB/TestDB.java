import java.sql.*;

public class TestDB{
	public static final String DB_NAME = "testjava.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:/home/ubuntu/Desktop/Java_Projects/TestDB/" + DB_NAME;
	public static final String TABLE_CONTACTS = "contacts";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_EMAIL = "email";

	public static void main(String[] args){
//		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/ubuntu/Desktop/Java_Projects/TestDB/testjava.db");
//			Statement statement = conn.createStatement();){
//			statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
		try{
			Connection conn = DriverManager.getConnection(CONNECTION_STRING);
//			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();
			statement.execute(	"DROP TABLE IF EXISTS " + TABLE_CONTACTS);
			
			statement.execute(	"CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
							 	"(" + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + 
							 	" INTEGER, " + COLUMN_EMAIL + " TEXT)");
			
			insertContact(statement,"Tim", 6546546, "tim@email.com");
			
			insertContact(statement,"Joe", 951951951, "Joe@email.com");

			insertContact(statement,"Jane", 9879879, "jane@email.com");

			insertContact(statement,"Dog", 3213321, "fido@email.com");

			statement.execute(	"UPDATE " + TABLE_CONTACTS + " SET " +
								COLUMN_PHONE + "=741741741 " + 
								"WHERE " + COLUMN_NAME + " ='Jane'");

			statement.execute(	"DELETE FROM " + TABLE_CONTACTS + " WHERE " +
								COLUMN_NAME + " ='Joe'");

/*			statement.execute("INSERT INTO contacts (name, phone, email) " +
								"VALUES('Joe',987454,'joe@email.com')");
			statement.execute("INSERT INTO contacts (name, phone, email) " +
								"VALUES('Jane',321321,'jane@email.com')");
			statement.execute("INSERT INTO contacts (name, phone, email) " +
								"VALUES('Dog',969665,'fido@email.com')");											
*/
/*			statement.execute("UPDATE contacts SET phone=654654654 WHERE name = 'Jane'");
			statement.execute("DELETE FROM contacts WHERE name = 'Joe'");
*/			
/*			statement.execute("SELECT * FROM contacts");
			ResultSet results = statement.getResultSet();
*/
			ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
			while(results.next()){
				System.out.println(results.getString(COLUMN_NAME) + " " +
									results.getInt(COLUMN_PHONE) + " " +
									results.getString(COLUMN_EMAIL));
			}
			results.close();
			statement.close();
			conn.close();
		} catch(SQLException e){
			System.out.println("Something went wrong... " + e.getMessage());
			e.printStackTrace();
		}	
	}

	private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
			statement.execute(	"INSERT INTO " + TABLE_CONTACTS +
								" (" + COLUMN_NAME + ", " +
								COLUMN_PHONE + ", " +
								COLUMN_EMAIL +
								") " +
								"VALUES('" + name + "', " + phone + ", '" + email + "')");		
	}
}
