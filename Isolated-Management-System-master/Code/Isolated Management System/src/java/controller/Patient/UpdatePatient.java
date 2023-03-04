
package controller.Patient;

import dao.PatientDAO;
import entity.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Notification;


public class UpdatePatient extends HttpServlet {

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
        Hashtable<String, String> my_dict = new Hashtable<>();
        PatientDAO dao = new PatientDAO();
        Patient patient = dao.get(Integer.parseInt(request.getParameter("patientId")));

        //check tung thuoc tinh xem co thay doi khong
        
        if(!request.getParameter("fullname").equals(patient.getPatientName())){
            my_dict.put("full_name", request.getParameter("fullname"));
        }
        if(!request.getParameter("suspicionLevel").equals(patient.getSuspicionLevel())){
            my_dict.put("suspicion_level", request.getParameter("suspicionLevel"));
        }
        if(Integer.parseInt(request.getParameter("age")) != patient.getAge()){
            my_dict.put("age", request.getParameter("age"));
        }
        if(!request.getParameter("gender").equals(patient.getGender())){
            my_dict.put("gender", request.getParameter("gender"));
        }
        if(!request.getParameter("passport").equals(patient.getPassport())){
            my_dict.put("partport", request.getParameter("passport"));
        }
        if(!request.getParameter("region").equals(patient.getRegion())){
            my_dict.put("region", request.getParameter("region"));
        }
        if(!request.getParameter("address").equals(patient.getAddress())){
            my_dict.put("address", request.getParameter("address"));
        }
        if(Integer.parseInt(request.getParameter("phoneNumber")) != patient.getPhoneNumber()){
            my_dict.put("phone", request.getParameter("phoneNumber"));
        }
        if(Integer.parseInt(request.getParameter("roomName")) != patient.getRoom().getRoomId()){
            my_dict.put("room_id", request.getParameter("roomName"));
        }
        
        //update
        dao.update(patient, my_dict);
        Notification noti = new Notification("Success", "Cập nhật thành công", "success");
        request.setAttribute("notify", noti);
        RequestDispatcher r1 = request.getRequestDispatcher("/Patient/view-list");
        r1.forward(request, response);
        PrintWriter out = response.getWriter();
        out.print("Cập nhật thành công");
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

