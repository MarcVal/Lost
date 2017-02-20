package my.apps.web;

import my.apps.Losts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LostPet")
public class LostPet extends HttpServlet {

    private int counter;

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
        String link = request.getParameter("link");
        String date = request.getParameter("date");
        String summary = request.getParameter("summary");
        String domain = request.getParameter("domain");
        Losts newLosts = new Losts(OwnerName,Email,Phone,Message,Microchiped,Neutered);


        System.out.println(OwnerName + Email + Phone + Message + Microchiped + Neutered);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Are you sure? </h2>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
        out.println ("OwnerName - <b>" + newLosts.toString() + "</b><br/>" );
        out.println("OwnerName - <b>" + OwnerName + "</b><br/>");
        out.println("Email - <b>" + Email + "</b><br/>");
        out.println("Phone" + Phone + "</b><br/>");
        out.println("Text" + Message + "</b><br/>");
        out.println("Chiped" + Microchiped + "</b><br/>");
        out.println("Fixed" + Neutered + "</b><br/>");
        out.println("<a href='/'>Go Back</a>");

        // finished writing, send to browser
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Get count </title>");
        out.println("</head>");

        out.println("<h2>Get count</h2>");
        out.println(counter);
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }
}