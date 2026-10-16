
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) {
	    
		// TODO Auto-generated method stub
	    try (Connection connection = DatabaseConfig.getConnection()) {
	    	
	    	if (connection != null && !connection.isClosed()) {
                System.out.println("Connexion à la base de donnée!");
			}
	    	
	    	 String sqlInsert = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
             
             try (PreparedStatement pstmtInsert = connection.prepareStatement(sqlInsert)) {
                 pstmtInsert.setString(1, "Julien");
                 pstmtInsert.setString(2, "julien@email.com");
                 
                 int lignesAjoutees = pstmtInsert.executeUpdate();  
                 System.out.println(lignesAjoutees + " ligne insérée.");
                 
                 String sqlSelect = "SELECT id, nom, email FROM utilisateurs";
                  
                 try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect);
                      ResultSet rs = pstmtSelect.executeQuery()) { 
                     
                     while (rs.next()) {
                         
                         int id = rs.getInt("id");
                         String nom = rs.getString("nom");
                         String email = rs.getString("email");
                         
                         System.out.println("ID: " + id + " Nom: " + nom + " Email: " + email);
                     }
                 }catch(SQLException e) {
          			System.err.println("Erreur SQL, je ne vois pas l'utilisateur");
          		}
             }catch(SQLException e) {
     			System.err.println("Erreur SQL INSERT");
     		}
             
		} catch (SQLException e) {
		    System.err.println("Échec de l'opération SQL :");
		    e.printStackTrace(); 
		}
	}

}
