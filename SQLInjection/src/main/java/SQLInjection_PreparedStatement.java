import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLInjection_PreparedStatement {

    public static boolean login(String username, String password) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db:3306/SocialMediaApp?useSSL=false", "root", "letmein");

            //Parameterized variables are pre-compiled so the input data is interpreted as a simple string instead of the raw value
            //Instead of data embedded directly in query
            //https://www.hackedu.com/blog/how-to-prevent-sql-injection-vulnerabilities-how-prepared-statements-work#:~:text=A%20prepared%20statement%20is%20a,safely%2C%20preventing%20SQL%20Injection%20vulnerabilities.
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
            return false;
        } catch (Exception e) {
            throw e;
        }
    }
}
