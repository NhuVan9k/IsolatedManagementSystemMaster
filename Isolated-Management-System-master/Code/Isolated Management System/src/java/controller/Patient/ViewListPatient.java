/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Patient;

import dao.PatientDAO;
import entity.Account;
import entity.Area;
import entity.Nurse;
import entity.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CHUNG
 */
public class ViewListPatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int page = 1;
        int recordsPerPage = 10;
        HttpSession ss = request.getSession();
        Account user = (Account) ss.getAttribute("userLogin");
        PrintWriter out = response.getWriter();
        PatientDAO dao = new PatientDAO();

        // if nurse login
        if (user.getType().getAccountTypeId() == 2) {
            Nurse nurse = (Nurse) ss.getAttribute("nurse");
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Patient> list = dao.getList((page - 1) * recordsPerPage, recordsPerPage, nurse.getId_area());
            int noOfRecords = dao.getNoOfRecord(nurse.getId_area());
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("noOfRecords", noOfRecords);
            request.setAttribute("list", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            RequestDispatcher view = request.getRequestDispatcher("../Patient/list.jsp");
            view.forward(request, response);
            //  if manager login
        } else {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Integer> susList = new ArrayList<Integer>();
            susList.add(dao.getNuPatientsInSus("F0"));
            susList.add(dao.getNuPatientsInSus("F1"));
            susList.add(dao.getNuPatientsInSus("F2"));
            susList.add(dao.getNuPatientsInSus("F3"));
            susList.add(dao.getNuPatientsInSus("F4"));
            List<Patient> list = dao.getListAll((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = dao.getAll().size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("susList", susList);
            request.setAttribute("noOfRecords", noOfRecords);
            request.setAttribute("list", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            RequestDispatcher view = request.getRequestDispatcher("../Patient/list.jsp");
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
