/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.base;

import dao.PatientDAO;
import dao.RoomDAO;
import entity.Account;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Notification;
import static utils.Utils.md5;

public class HomeServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        HttpSession ss = request.getSession();
        PatientDAO patientDAO = new PatientDAO();
        RoomDAO roomDAO = new RoomDAO();
        Account account = (Account) ss.getAttribute("userLogin");
        Notification noti = null;
        RequestDispatcher rt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate now = LocalDate.now();
        String[] dates = {"", "", "", "", "", ""};
        int index = 0;
        for (int i = 5; i >= 0; i--) {
            dates[index++] = formatter.format(now.minus(i, ChronoUnit.DAYS));
        }
        int[] arrPatient = new int[dates.length]; // New Patients in given date
        int newPatientWeek = patientDAO.getPatientsInDuration(7); // New Patients in 1 week
        for (int i = 0; i < dates.length; i++) {
            arrPatient[i] = patientDAO.getNuPatientsInDate(dates[i]);
        }
        int total_bed = roomDAO.getAllBed();
        int total_patient = patientDAO.getTotalPatients();
        int available_bed = total_bed - total_patient;
        request.setAttribute("totalPatient", total_patient);
        request.setAttribute("availableBed", available_bed);
        request.setAttribute("totalBed", total_bed);
        request.setAttribute("newWeek", newPatientWeek);
        request.setAttribute("dates", dates);
        request.setAttribute("new", arrPatient);
        int id = account.getType().getAccountTypeId();
        switch (id) {
            case 1: // Manager 
                noti = new Notification("Success", "Chào mừng bạn đến với hệ thống với vai trò là Manager", "success");
                request.setAttribute("notify", noti);
                rt = request.getRequestDispatcher("home.jsp");
                rt.forward(request, response);
                break;
            case 2: // Nurse
                noti = new Notification("Success", "Chào mừng bạn đến với hệ thống với vai trò là Nurse", "success");
                request.setAttribute("notify", noti);
                rt = request.getRequestDispatcher("home.jsp");
                rt.forward(request, response);
                break;
            case 3: // Patient
                noti = new Notification("Success", "Chào mừng bạn đến với hệ thống của chúng tôi", "success");
                request.setAttribute("notify", noti);
                rt = request.getRequestDispatcher("home.jsp");
                rt.forward(request, response);
                break;
            case 4: // Doctor
                noti = new Notification("Success", "Chào mừng bạn đến với hệ thống với vai trò là Doctor", "success");
                request.setAttribute("notify", noti);
                rt = request.getRequestDispatcher("home.jsp");
                rt.forward(request, response);
                break;
            default:

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
