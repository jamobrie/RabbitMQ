import java.util.HashMap;

public class Jwt {

    public static HashMap<String, String> accountLookup(String accountId, String jwt) throws Exception{

        if (jwt == null) { ;
        } else {

            Claims claims = Jwts.parser()
                    .setSigningKey("luBEK(P$x[%ZeQ4HAD5Ji1Z*;0Gcz583yP!v|KCmNEDDmQF/9P)>GpJK>Cx}3;R".getBytes("UTF-8"))
                    .parseClaimsJws(jwt).getBody();

            if ((claims.get("logged_in")).toString().equals("true")) {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/BankApp?useSSL=false", "root", "letmein");

                Statement stmt = con.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT username FROM tbl_user WHERE id = '" + accountId + "';");

                if (!rs.next()) {
                    con.close();
                    throw new Exception("Account not found");
                }

                String user = new String();
                user = rs.getString("username");

                String user = new String();
                user = rs.getString("username");

                if(!user.equals((claims.get("logged_in")).toString()).toString()){
                    throw new Exception("You do not have access");
                }

                rs=stmt.executeQuery("SELECT balance, dob FROM tbl_account WHERE user_id = '" + accountId + "';");
                HashMap<String, String> results = new HashMap<String, String>();

                if(rs.next()){
                    results.put("balance", rs.getString("balance").toString());
                    results.put("dob", rs.getString("dob").toString());
                    results.put("username", user);

                }
                con.close();

                return results;
            } else {
                throw new LoggedOutException("User is not logged in");
            }
        }
    }

}
