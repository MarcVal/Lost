package my.apps.db;

import my.apps.Losts;
import my.apps.domain.LostEntry;
import my.apps.web.LostPet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
        pSt.setString(3, losts.getPhone());
        pSt.setString(4, losts.getMessage());
        pSt.setString(5, (losts.getNeutered()));
        pSt.setString(6, (losts.getMicrochip()));


        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public List<LostEntry> read(String email, String owner, String phone) throws ClassNotFoundException, SQLException {

        System.out.println("email:"+email + "|"+owner+"|");
        // 1. load the driver
        Class.forName("org.postgresql.Driver");
        int indexEmail = 0, indexOwner = 0, indexPhone = 0;

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(DemoCRUDOperations.URL, DemoCRUDOperations.USERNAME, DemoCRUDOperations.PASSWORD);

        // 3. create a query statement
        String query = "SELECT ownerName, email, phone, message, neutered, chipped FROM LostPets ";
        boolean emailValid = email != null && !"".equals(email);
        boolean ownerValid = owner != null && !"".equals(owner);
        boolean phoneValid = phone != null && !"".equals(phone);

        if(emailValid || ownerValid || phoneValid) {
            query = query + " WHERE ";
        }
        int index = 1;
        boolean needsOr = false;
        if (emailValid) {
            query += "email = ?";
            needsOr = true;
            indexEmail = index;
            index++;
        }
        if (owner != null && !"".equals(owner)) {
            if(needsOr) {
                query += " OR ";
            }
            query += "owner = ?";
            needsOr = true;
            indexOwner = index;
            index++;
        }
        if(phone != null && !"".equals(phone)) {
            if(needsOr) {
                query += " OR ";
            }
            query += "phone = ?";
            needsOr = true;
            indexPhone = index;
            index++;
        }
        System.out.println(query);
        PreparedStatement pSt = conn.prepareStatement(query);
        if (emailValid) {
            pSt.setString(indexEmail, email);
        }
        if(ownerValid) {
            pSt.setString(indexOwner, owner);
        }
        if(phoneValid) {
            pSt.setString(indexPhone, phone);
        }


        // 4. execute a query

        ResultSet rs = pSt.executeQuery(query);

        // 5. iterate the result set and print the values
        List<LostEntry> lostss = new ArrayList<>();
        while (rs.next()) {
            LostEntry losts = new LostEntry(
                    rs.getString("ownerName"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("message"),
                    rs.getString("chipped"),
                    rs.getString("neutered")
            );
            System.out.println(losts);
            lostss.add(losts);
        }
        //6.close the objects
        rs.close();
        pSt.close();
        conn.close();
        return lostss;
    }

    public static List<LostEntry> read() throws ClassNotFoundException, SQLException {
        //1. load the driver
        Class.forName("org.postgresql.Driver");

        //2. obtain a connection
        Connection conn = DriverManager.getConnection(DemoCRUDOperations.URL, DemoCRUDOperations.USERNAME, DemoCRUDOperations.PASSWORD);

        //3. create a query statement
        Statement st = conn.createStatement();

        //4. execute a query
        ResultSet rs = st.executeQuery("SELECT id, ownerName, email, phone, message, chipped, neutered FROM LostPet");

        //5. iterate the result set and print the values
        List<LostEntry> lostEntries = new ArrayList<>();
        while (rs.next()) {
            LostEntry lostEntry = new LostEntry(
                    rs.getString("ownerName"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("message"),
                    rs.getString("chipped"),
                    rs.getString("neutered")

            );
            lostEntries.add(lostEntry);

        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return lostEntries;
    }
}


