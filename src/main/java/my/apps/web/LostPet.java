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
        Integer Phone = Integer.valueOf(request.getParameter("Phone"));
        String Message = request.getParameter("Message");
        String[] Microchiped = request.getParameterValues("Yes" + "No");
        String[] Neutered = request.getParameterValues("Yes" + "No");

        Losts newLosts = new Losts(OwnerName, Email, Phone, Message, Microchiped, Neutered);
        try {
            lostRepository.insert(newLosts);
            out.println("Inserted - <b>" + newLosts.toString() + "</b><br/>");

            out.println(OwnerName + Email + Phone + Message + Microchiped + Neutered);

            out.println("<h2>Are you sure? </h2>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            out.println("OwnerName - <b>" + OwnerName + "</b><br/>");
            out.println("Email - <b>" + Email + "</b><br/>");
            out.println("Phone" + Phone + "</b><br/>");
            out.println("Text" + Message + "</b><br/>");
            out.println("Chiped" + Microchiped + "</b><br/>");
            out.println("Fixed" + Neutered + "</b><br/>");


        } catch (ClassNotFoundException e) {
            out.println("Class not found issues!");
            e.printStackTrace();

        } catch (SQLException e) {
        out.println("SQL exception issues!");
        e.printStackTrace();
        }


        // finished writing, send to browser
        out.close();
        out.println("<a href='/'>Go Back</a>");

        @Override
        protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            counter++;
            List<Losts> lostss;
            try {
            lostss = lostRepository.read();}
            catch (Exception e) {
            lostss = new ArrayList<>();}

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<head>");
            out.println("<title> Get count </title>");
            out.println("</head>");

            out.println("<h2>Get count</h2>");
            for (Losts lost: lost) {
                out.println("<b>" + lost.toString() + "</b><br />");
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