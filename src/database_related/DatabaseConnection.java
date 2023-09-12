package database_related;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	static Connection connection;
	public static Connection connectToDb()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String username="root";
			String password = "root";
			String db_info = "jdbc:mysql://localhost:3306/student_ms";
			connection = DriverManager.getConnection(db_info,username,password);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return connection;
	}

}
