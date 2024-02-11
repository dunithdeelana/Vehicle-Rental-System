import java.sql.*;

public class DB {
	
	public static Connection mycon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
			
			return con;
			
		}catch(Exception e) {
			System.out.println("");
		}
		return null;
	}

}