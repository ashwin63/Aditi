/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.Scanner;
@WebServlet("/upload")
@MultipartConfig
/**
 *
 * @author ashwi
 */
public class savetofile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {		
		String tempad="C:";            
            /* TODO output your page here. You may use following sample code. */
            int pid=Integer.parseInt(request.getParameter("id"));
            String  pname=request.getParameter("pname");
            String pquantity=request.getParameter("pquantity");
            String pprice=request.getParameter("pprice");
            File file1=new File(tempad+"/tomcat/webapps/summer/WEB-INF/classes/files/pvalues/pdetails.txt");
			PrintWriter outtofile=new PrintWriter(new FileOutputStream(file1,true));
			outtofile.write("\r\n");
			outtofile.write(pid+","+pname+","+pquantity+","+pprice);
            outtofile.flush();
            
            //function to upload image to the server folder
             Part filePart = request.getPart("pimage"); // Retrieves <input type="file" name="file">
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            // ... (do your job here
            Path file = Paths.get(tempad+"/tomcat/webapps/summer/WEB-INF/classes/files/pimages/"+pid+".jpg");
            
            Files.copy(fileContent,file);
            //out.println("suceass");
             RequestDispatcher rd=request.getRequestDispatcher("adminregister.jsp");  
        rd.forward(request, response);  
       //    response.sendRedirect("adminregister.jsp");  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet savetofile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet savetofile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
