/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.NewDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import model.News;
import model.Staff;
import validation.CommonValidation;

/**
 *
 * @author PC
 */
@WebServlet(name="AddNewsServlet", urlPatterns={"/add-news"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddNewsServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewsServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewsServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        NewDAO ndao = new NewDAO();
        StaffDAO sdao = new StaffDAO();
        List<String> categories = ndao.getAllCategory();
        List<Staff> staffs = sdao.getAdminAndAdministrative();
        request.setAttribute("categories", categories);
        request.setAttribute("staffs", staffs);
        request.getRequestDispatcher("addnews.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");    
        Part filePart=request.getPart("file");
        String image="";
        boolean hasError = false;
        if(filePart!=null && filePart.getSize()>0){
            String filename=Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtention=filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
            if(!fileExtention.matches("jpg|jpeg")){
                request.setAttribute("fileerror", "Only jpg");
                hasError = true;
            }
            String uploadpath=getServletContext().getRealPath("/")+"images/news";
            File uploadDir=new File(uploadpath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            File file= new File(uploadDir, filename);
            try(InputStream fileContent=filePart.getInputStream();
                    FileOutputStream outputStream=new FileOutputStream(file)) {
                byte[] buffer=new byte[1024];
                int byteread;
                while((byteread=fileContent.read(buffer))!= -1){
                    outputStream.write(buffer, 0, byteread);
                }
            }
            image = "images/news/" + filename;           
        }
        String auther = request.getParameter("authorid");
        String date = request.getParameter("date");
        String category = request.getParameter("category");
        NewDAO ndao = new NewDAO();
        StaffDAO sdao = new StaffDAO();
        News anew = new News(title, content, source, category, image, sdao.getById(auther), date);
        try{
            if(title.trim().isEmpty()){
                request.setAttribute("titleerror", "Title is not empty");
                hasError = true;
            }if(content.trim().isEmpty()){
                request.setAttribute("contenterror", "Content is not empty");
                hasError = true;
            }if(source.trim().isEmpty()){
                request.setAttribute("sourceerror", "Source is not empty");
                hasError = true;
            }
            if(!CommonValidation.isValidNewsDate(date)){
                request.setAttribute("dateError", "Date need to later current date");
                hasError = true;
            }
        }catch(ParseException e){
            System.out.println(""+e);
        }
        if (hasError) {
            request.getRequestDispatcher("addnews.jsp").forward(request, response);
                return;
        }
        if (ndao.insertNews(anew)) {
            request.setAttribute("status", "true");
            request.setAttribute("message", "News added successfully!");
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Failed to add news.");
        }
        doGet(request, response);
        request.removeAttribute("error");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
