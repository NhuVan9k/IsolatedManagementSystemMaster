/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Patient;

import dao.AreaDAO;
import dao.PatientDAO;
import dao.RoomDAO;
import entity.Area;
import entity.Nurse;
import entity.Patient;
import entity.Room;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Notification;
import utils.Utils;

/**
 *
 * @author CHUNG
 */
public class AddPatient extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullname");
        String suspicionLevel = request.getParameter("suspicionLevel");
        String age = request.getParameter("age");
        int age_int = Integer.parseInt(age);
        String gender = request.getParameter("gender");
        String passport = request.getParameter("passport");
        String region = request.getParameter("region");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int phone_int = Integer.parseInt(phone);
        String roomName = request.getParameter("roomName");
        RoomDAO roomDAO = new RoomDAO();
        AreaDAO areaDAO = new AreaDAO();

        Room phong = roomDAO.get(Integer.parseInt(roomName));
        String timeIn = Utils.getToday();
        Date TimeIn = null;
        try {
            TimeIn = Utils.DATE_FORMATER.parse(timeIn);
        } catch (ParseException ex) {
            TimeIn = new Date();
        }
        HttpSession ss = request.getSession();
        Nurse nurse = (Nurse) ss.getAttribute("nurse");
        Area area = areaDAO.get(nurse.getId_area());
        
        // Create Patient
        Patient patient = new Patient();
        patient.setPatientName(fullName);
        patient.setSuspicionLevel(suspicionLevel);
        patient.setPassport(passport);
        patient.setAddress(address);
        patient.setGender(gender);
        patient.setRoom(phong);
        patient.setRegion(region);
        patient.setPhoneNumber(phone_int);
        patient.setAge(age_int);
        patient.setTimeIn(new java.sql.Timestamp(TimeIn.getTime()));
        patient.setArea(area);

        PatientDAO dao = new PatientDAO();
        dao.create(patient);
        Notification noti = new Notification("Success", "Thêm người cách ly thành công", "success");
        request.setAttribute("notify", noti);
        RequestDispatcher r1 = request.getRequestDispatcher("/Patient/view-list");
        r1.forward(request, response);
//        PrintWriter out = response.getWriter();
//        out.print("Hello");
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
