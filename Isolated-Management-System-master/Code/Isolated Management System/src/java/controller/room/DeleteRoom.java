/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.room;

import dao.PatientDAO;
import dao.RoomDAO;
import entity.Patient;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Notification;

/**
 *
 * @author Admin
 */
public class DeleteRoom extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        PatientDAO daoPatient = new PatientDAO();
        List<Patient> list = daoPatient.parse("SELECT * FROM [dbo].[patient] WHERE room_id = "+roomId);
        if (list.isEmpty()){
            RoomDAO dao = new RoomDAO();
            dao.delete(dao.get(roomId));
            Notification noti = new Notification("Success","Xóa phòng cách ly thành công.","success");
            request.setAttribute("notify", noti);
            RequestDispatcher delete = request.getRequestDispatcher("list");
            delete.forward(request, response);
        }
        Notification noti = new Notification("Warning", "Không thể xóa phòng cách ly khi trong đó vẫn còn bệnh nhân.","warning");
        request.setAttribute("notify", noti);
        RequestDispatcher room = request.getRequestDispatcher("list");
        room.forward(request, response);
        
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
