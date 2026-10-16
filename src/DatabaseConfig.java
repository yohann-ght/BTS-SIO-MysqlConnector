
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

	public static Connection getConnection() throws SQLException {
		
		String appEnv = System.getenv("APP_ENV");
		boolean isLocal = (appEnv == null || appEnv.equalsIgnoreCase("development") || appEnv.equalsIgnoreCase("local"));
		String url;
		String user;
		String password;
		
		if(isLocal) {
			url = "jdbc:mysql://localhost:3306/javabdd?useSSL=false&serverTimezone=UTC";
			user = "root";
            password = "";
		} else {
		      url = System.getenv("DB_URL");
	          user = System.getenv("DB_USER");
	          password = System.getenv("DB_PASSWORD");
	           
	          if (url == null || user == null || password == null) {
	               throw new IllegalStateException("Erreur : Variables de production manquantes !");
	          }
		}
		
		return DriverManager.getConnection(url, user, password);
	}
    
    
}
