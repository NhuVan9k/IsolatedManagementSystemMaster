/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.report;

import dao.ReportDAO;
import entity.Account;
import entity.Report;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ViewReport extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        int page = 1;
        int recordPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int id = 0;
        HttpSession session = request.getSession();
        Account userLogin = (Account) session.getAttribute("userLogin");
        if (userLogin.getType().getAccountTypeId() == 3) {// Patient
            id = userLogin.getPatient().getPatientId();
        }

        String fname = (String) request.getParameter("fname");
        String ffrom = (String) request.getParameter("ffrom");
        String fto = (String) request.getParameter("fto");
        String fsort = (String) request.getParameter("fsort");
        
        ReportDAO dao = new ReportDAO();
        List<Report> list = dao.getIndex((page - 1) * recordPerPage + 1, page * recordPerPage, id);
        int noOfRecord = dao.getNoOfRecord(id);
        int noOfPage = (int) ((noOfRecord + 9) / 10);
        request.setAttribute("noOfRecords", noOfRecord);
        request.setAttribute("listReport", list);
        request.setAttribute("noOfPages", noOfPage);
        request.setAttribute("currentPage", page);
        if (userLogin.getType().getAccountTypeId() == 3) {// List for Patient
            RequestDispatcher view = request.getRequestDispatcher("personalList.jsp");
            view.forward(request, response);
        } else {
            // List for Doctor
            request.setAttribute("fname", fname);
            request.setAttribute("ffrom", ffrom);
            request.setAttribute("fto",fto);
            request.setAttribute("fsort", fsort);
            RequestDispatcher view = request.getRequestDispatcher("list.jsp");
            view.forward(request, response);
        }
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
        doGet(request, response);
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
