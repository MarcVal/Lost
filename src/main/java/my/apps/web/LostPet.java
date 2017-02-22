package my.apps.web;

import my.apps.Losts;
import my.apps.db.LostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        Losts newLosts = new Losts(OwnerName, Email, Phone, Message, Microchipped, Neutered);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {

            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
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
            out.println("SQL exception issues!");
            e.printStackTrace();
        }

        // finished writing, send to browser
        out.println("<a href='/'>Go Back</a>");
        out.close();

    }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            counter++;
            List<Losts> lostss;
            String email = req.getParameter("Email");
            String owner = req.getParameter("Owner");
            try {
                lostss = lostRepository.read("email","owner");
            } catch (Exception e) {
                lostss = new ArrayList<>();
            }

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<head>");
            out.println("<title> Get count </title>");
            out.println("</head>");

            out.println("<h2>Get count</h2>");
            for (Losts losts : lostss) {
                System.out.println(losts);
                out.println("<b>" + losts.toString() + "</b><br />");
            }
            out.println(counter);
            out.close();
        }

        @Override
        public void init () throws ServletException {
            super.init();
            out.println("init() called. Counter is: " + counter);
        }

        @Override
        public void destroy () {
            out.println("Destroying Servlet! Counter is:" + counter);
            super.destroy();
        }

}