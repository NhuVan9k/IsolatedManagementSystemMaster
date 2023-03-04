/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.base;

import dao.PatientDAO;
import entity.Account;
import entity.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Notification;

/**
 *
 * @author Admin
 */
public class SearchPatient extends HttpServlet {

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
            out.println("<title>Servlet SearchPatient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchPatient at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String key = request.getParameter("key");
        HttpSession ss = request.getSession();
        Account user = (Account) ss.getAttribute("userLogin");
        if (user != null) {
            if (key == null || "".equals(key)) {
                response.sendRedirect("home.jsp");
            } else {
                int page = 1;
                int recordsPerPage = 10;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                PatientDAO dao = new PatientDAO();
                List<Patient> list = dao.SearchByKey(key);
                List<Patient> list2 = new ArrayList<>();
                int noOfRecords = list.size();
                int t;
                if (noOfRecords > 10 * page) {
                    t = recordsPerPage;
                } else {
                    t = noOfRecords % recordsPerPage;
                }
                for (int i = 0; i < t; i++) {
                    list2.add(list.get(i + (page - 1) * recordsPerPage));
                }

                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("noOfRecords", noOfRecords);
                request.setAttribute("ngCachLyList", list2);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
                request.setAttribute("key", key);
                RequestDispatcher view = request.getRequestDispatcher("search.jsp");
                view.forward(request, response);

            }
        } else {
            Notification noti = new Notification("Warning", "Xin vui lòng đăng nhập để sử dụng chức năng", "warning");
            request.setAttribute("notify", noti);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }
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
