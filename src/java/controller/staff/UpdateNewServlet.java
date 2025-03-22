/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import model.News;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdateNewServlet", urlPatterns = {"/update-news"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateNewServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateNewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNewServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        NewDAO ndao = new NewDAO();
        StaffDAO sdao = new StaffDAO();
        List<String> categories = ndao.getAllCategory();
        List<Staff> staffs = sdao.getAdminAndAdministrative();
        News news = ndao.getNewById(request.getParameter("id"));
        session.setAttribute("categories", categories);
        session.setAttribute("staffs", staffs);
        request.setAttribute("news", news);
        request.getRequestDispatcher("updatenews.jsp").forward(request, response);
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
        Util u= new Util();
        String id = request.getParameter("id");
        String title = u.stringNomalize(request.getParameter("title")) ;
        String content = u.stringNomalize(request.getParameter("content")) ;
        String source = u.stringNomalize(request.getParameter("source")) ;
        String date = request.getParameter("date");
        String category = request.getParameter("category");
        String auther = request.getParameter("auther");
//        Part filePart=request.getPart("file");
        NewDAO ndao = new NewDAO();
        News news = ndao.getNewById(id);
//        String image=news.getImage();
//        if(filePart!=null && filePart.getSize()>0){
//            String filename=Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//            String fileExtention=filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
//            if(!fileExtention.matches("jpg|jpeg")){
//                request.setAttribute("fileerror", "Only jpg");
//                request.getRequestDispatcher("updatenews.jsp").forward(request, response);
//                return;
//            }
//            String uploadpath=getServletContext().getRealPath("/")+"images/news";
//            File uploadDir=new File(uploadpath);
//            if(!uploadDir.exists()){
//                uploadDir.mkdirs();
//            }
//            File file= new File(uploadDir, filename);
//            try(InputStream fileContent=filePart.getInputStream();
//                    FileOutputStream outputStream=new FileOutputStream(file)) {
//                byte[] buffer=new byte[1024];
//                int byteread;
//                while((byteread=fileContent.read(buffer))!= -1){
//                    outputStream.write(buffer, 0, byteread);
//                }
//                
//            }
//            image = "images/news/" + filename;           
//        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ZoneId zone = ZoneId.systemDefault();
            Date dateAdd = format.parse(date);
            LocalDate inputDate = dateAdd.toInstant().atZone(zone).toLocalDate();
            LocalDate currentDate = format.parse(news.getDate()).toInstant().atZone(zone).toLocalDate();

            if (inputDate.isBefore(currentDate)) {
                request.setAttribute("dateError", "The time must be after the date of the update check.");
                request.setAttribute("news", news);
                request.getRequestDispatcher("updatenews.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            ;
        }
        StaffDAO sdao = new StaffDAO();
        News anew = new News(id,title, content,source,date,category,sdao.getById(auther));
        if (title.trim().isEmpty()) {
            request.setAttribute("titleerror", "Title can not be empty.");
            request.setAttribute("news", news);
            request.getRequestDispatcher("updatenews.jsp").forward(request, response);
            return;
        }
        if (content.trim().isEmpty()) {
            request.setAttribute("contenterror", "Content can not be empty.");
            request.setAttribute("news", news);
            request.getRequestDispatcher("updatenews.jsp").forward(request, response);
            return;
        }
        if(source.trim().isEmpty()){
            request.setAttribute("sourceerror", "Source can not be empty.");
            request.setAttribute("news", news);
            request.getRequestDispatcher("updatenews.jsp").forward(request, response);
            return;
        }
        else {
            if (ndao.updateNews(anew)) {
                request.setAttribute("status", "true");
                request.setAttribute("message", "News updated successfully!");
            } else {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Failed to update news.");
            }
        }
        news = ndao.getNewById(request.getParameter("id"));
        request.setAttribute("news", news);
        request.getRequestDispatcher("updatenews.jsp").forward(request, response);
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
