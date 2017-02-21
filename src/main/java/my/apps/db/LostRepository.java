package my.apps.db;

import my.apps.Losts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valeria Marc on 2/20/2017.
 */
public class LostRepository {

    public void insert(Losts losts) throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(DemoCRUDOperations.URL, DemoCRUDOperations.USERNAME, DemoCRUDOperations.PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO LostPets (ownerName, email, phone, message, neutered, chipped) VALUES (?,?,?,?,?,?)");
        pSt.setString(1, losts.getOwnerName());
        pSt.setString(2, losts.getEmail());
        pSt.setInt(3, losts.getPhone());
        pSt.setString(4, losts.getMessage());
        pSt.setString(5, String.valueOf(losts.getNeutered()));
        pSt.setString(6, String.valueOf(losts.getMicrochip()));

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public List<Losts> read() throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(DemoCRUDOperations.URL, DemoCRUDOperations.USERNAME, DemoCRUDOperations.PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT ownerName, email, phone, message, neutered, chipped FROM losts");

        // 5. iterate the result set and print the values
        List<Losts> lostss = new ArrayList<>();
        while (rs.next()) {
            Losts losts = new Losts(
                    rs.getString("ownerName"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("message"),
                    rs.getString("microchiped"),
                    rs.getString("neutered")
            );
            lostss.add(losts);
        }
        //6.close the objects
        rs.close();
        st.close();
        conn.close();
        return lostss;
    }
}


