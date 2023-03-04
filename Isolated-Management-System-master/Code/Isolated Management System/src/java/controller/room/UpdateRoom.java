/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.room;

import dao.AreaDAO;
import dao.RoomDAO;
import entity.Room;
import java.io.IOException;
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
public class UpdateRoom extends HttpServlet {

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
        RoomDAO dao = new RoomDAO();

        if (request.getParameter("roomId") == null) {
            RequestDispatcher view = request.getRequestDispatcher("list");
            view.forward(request, response);
        }
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String roomName = request.getParameter("roomName");
        String bedNumber = request.getParameter("bedNumber");
        String note = request.getParameter("note");
        String areaId = request.getParameter("areaId");

        if (roomName != null && bedNumber != null && areaId != null) {
            if (roomName.length() > 0 && bedNumber.length() > 0 && areaId.length() > 0) {
                if (note.length() == 0) {
                    note = "no notes";
                }
                AreaDAO daoArea = new AreaDAO();
                Room room = new Room();
                room.setRoomId(roomId);
                room.setRoomName(roomName);
                room.setNote(note);
                room.setBedNumber(Integer.parseInt(bedNumber));
                room.setArea(daoArea.get(Integer.parseInt(areaId)));
                dao.updateRoom(room);
                Notification noti = new Notification("Success", "Cập nhật phòng cách ly thành công.", "success");
                request.setAttribute("notify", noti);
                RequestDispatcher update = request.getRequestDispatcher("list");
                update.forward(request, response);
            }
        }
        if ((roomName != null && bedNumber != null && areaId != null)) {
            Notification noti = new Notification("Warning", "Hãy điền đủ tất cả thông tin.", "warning");
            request.setAttribute("notify", noti);
        }
        request.setAttribute("room", dao.get(roomId));
        RequestDispatcher update = request.getRequestDispatcher("update.jsp");
        update.forward(request, response);
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
