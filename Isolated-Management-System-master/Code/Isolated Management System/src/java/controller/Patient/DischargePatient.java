/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Patient;

import dao.PatientDAO;
import entity.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Notification;


public class DischargePatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.get(Integer.parseInt(id));
        if (patient.getTimeOut() == null) {
            patientDAO.discharge(patient);
            Notification noti = new Notification("Success", "Xuất viện thành công", "success");
            request.setAttribute("notify", noti);
        } else {
            Notification noti = new Notification("Error", "Người này đã xuất viện rồi", "error");
            request.setAttribute("notify", noti);
        }
        RequestDispatcher r1 = request.getRequestDispatcher("view-detail?id=" + id);
        r1.forward(request, response);
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
