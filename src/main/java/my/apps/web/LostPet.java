package my.apps.web;

import my.apps.Losts;
import my.apps.db.LostRepository;
import my.apps.domain.LostEntry;
import sun.util.calendar.BaseCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import static java.lang.System.out;

@WebServlet("/LostPet")
public class LostPet extends HttpServlet {

    private int counter;

    private LostRepository lostRepository = new LostRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String OwnerName = request.getParameter("OwnerName");
        String Email = request.getParameter("Email");
        String Phone = request.getParameter("Phone");
        String Message = request.getParameter("Message");
        String Microchipped = request.getParameter("chipped");
        String Neutered = request.getParameter("neutered");
        // Date Date =      ("Date");

        Losts newLosts = new Losts(OwnerName, Email, Phone, Message, Microchipped, Neutered);

        System.out.println(OwnerName + Email + Phone + Message + Microchipped + Neutered);

        // write results for response

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\">");
        try {

            Date validDate = java.sql.Date.valueOf(LocalDate.MAX);
            // LocalDate.MAX ... de ce?
            LostPet lostPet = new LostPet();

            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\">");
            out.println("</head>");
            out.println("Inserted - <b>" + newLosts.toString() + "</b><br/>");

            out.println(OwnerName + Email + Phone + Message + Neutered + Microchipped);

            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\">");
            out.println("<h2>Are you sure? </h2>");
            out.println("OwnerName - <b>" + OwnerName + "</b><br/>");
            out.println("Email - <b>" + Email + "</b><br/>");
            out.println("Phone<b>" + Phone + "</b><br/>");
            out.println("Text<b>" + Message + "</b><br/>");
            out.println("Chiped<b>" + Microchipped + "</b><br/>");
            out.println("Fixed<b>" + Neutered + "</b><br/>");
            lostRepository.insert(newLosts);

        } catch (ClassNotFoundException e) {
            out.println("Class not found issues!");
            e.printStackTrace();

        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " + e.getMessage() + "<b></div>");

            /* out.println("SQL exception issues!");
            e.printStackTrace(); */
        }
        addGoBack(out);

        // finished writing, send to browser

        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Lost pets </title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\">");
        out.println("</head>");

        try {

            out.println("<h3>Losts...</h3>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Owner</th>");
            out.println("<th>Email</th>");
            out.println("<th>Message</th>");
            out.println("<th>Phone</th>");
            out.println("<th>Chipped status</th>");
            out.println("<th>Neuter status</th>");

            out.println("</tr>");

            List<LostEntry> losts = LostRepository.read();
            for (LostEntry lost : losts) {
                out.println("<tr>");
                out.println("<td>" + lost.getOwner() + "</td>");
                out.println("<td>" + lost.getEmail() + "</td>");
                out.println("<td>" + lost.getMessage() + "</td>");
                out.println("<td>" + lost.getPhone() + "</td>");
                out.println("<td>" + lost.getChipped() + "</td>");
                out.println("<td>" + lost.getNeutered() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " + e.getMessage() + "<b></div>");
        }

        out.println(counter);
        addGoBack(out);
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }

    private void addGoBack(PrintWriter out) {
        out.println("<a href='/'>Go Back</a>");
    }
}

/* List<Losts> lostss;
            String email = req.getParameter("Email");
            String owner = req.getParameter("Owner");
            for (Losts losts : lostss) {
                System.out.println(losts);
                out.println("<b>" + losts.toString() + "</b><br />");
                 try {
                lostss = lostRepository.read("email","owner");
            } catch (Exception e) {
                lostss = new ArrayList<>();
            } */