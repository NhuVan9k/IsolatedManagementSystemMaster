/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.room;

import dao.PatientDAO;
import dao.RoomDAO;
import entity.Patient;
import entity.Room;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ViewDetail extends HttpServlet {

    
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
        RoomDAO dao = new RoomDAO();
        PatientDAO daoPatient = new PatientDAO();
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Room room = dao.get(roomId);
        String areaName = room.getArea().getAreaName();
        List<Patient> list = daoPatient.parse("SELECT * FROM [dbo].[patient] WHERE room_id = "+roomId);
        
        request.setAttribute("areaName", areaName);
        request.setAttribute("roomId", room.getRoomId());
        request.setAttribute("roomName", room.getRoomName());
        request.setAttribute("bedNumber", room.getBedNumber());
        request.setAttribute("note", room.getNote());
        request.setAttribute("listPatient", list);
        request.setAttribute("patientNumber", list.size());
        RequestDispatcher view = request.getRequestDispatcher("viewDetail.jsp");
        view.forward(request, response);
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
