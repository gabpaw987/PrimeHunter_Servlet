package primes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a Web Servlet, handling a prime number seeker and displaying<br>
 * the current prime number that was discovered last whenever the <br>
 * Servlet gets refreshed.
 * 
 * @author Gabriel Pawlowsky
 * @version 2012-10-14
 */
@WebServlet(name = "Searcher", urlPatterns = {"/Primes/Searcher"})
public class Searcher extends HttpServlet {

    //He is seeking for prime numbers with a delay of 100 ms.
    PrimeNumberSeeker numberSeeker;
    //The date where the Servlet got started.
    Date startDate;

    /**
     * Constructor, which sets the start date and starts the prime number seeker<br>
     * when the Searcher Servlet gets initialised.
     */
    public Searcher() {
        this.startDate = new Date();
        this.numberSeeker = new PrimeNumberSeeker();
        numberSeeker.start();
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods. <br> It also shows the current prime number
     * and the date and time it has been discovered.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Searcher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><center>Prime Searcher</center></h1>");
            //The blue bars
            out.println("<hr size=15 color=\"#0000ff\" width=75%>");
            out.println("<center>Started at " + getFormattedDate(this.startDate) + "</center>");
            out.println("<br>");
            out.println("<center>Last Prime discovered was " + numberSeeker.getPrimes().get(numberSeeker.getPrimes().size() - 1) + " at " + getFormattedDate(numberSeeker.getDateOFLastDiscovery()) + "<center>");
            out.println("<hr size=15 color=\"#0000ff\" width=75%>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    /**
     * A method to format the output string of a simple date like it is<br>
     * formatted in the example from Prof. Borko
     * @param date the date that shall be formatted
     * @return the formatted date as string
     */
    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("EEE MMM d hh:mm:ss z yyyy");
        return dateFormat.format(date);
    }

    /**
     * This Method interrupts the Thread, when the servlet will be undeployed.<br>
     * 
     * @param request servlet request
     * @param response servlet response
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response){
        numberSeeker.interrupt();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
