/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.base;

import dao.AccountDAO;
import dao.PatientDAO;
import entity.Account;
import entity.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
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
public class UpdateAccount extends HttpServlet {

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
            out.println("<title>Servlet UpdateAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccount at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Hashtable<String, String> my_dict = new Hashtable<>();
        AccountDAO dao = new AccountDAO();
        PatientDAO patientDAO = new PatientDAO();
        HttpSession ss = request.getSession();
        Account account = (Account) ss.getAttribute("userLogin");
        Patient patient = patientDAO.getPatientByAccountId(account.getAccountId());
        String phone = request.getParameter("phone");
        if (phone.length() == 9 || phone.length() == 10) {  // check so dien thoai có sai ko
            //check tung thuoc tinh xem co thay doi khong

            if (!request.getParameter("full_name").equals(account.getPatient().getPatientName())) {
                my_dict.put("full_name", request.getParameter("full_name"));
            }
            if (Integer.parseInt(request.getParameter("phone")) != account.getPatient().getPhoneNumber()) {
                my_dict.put("phone", request.getParameter("phone"));
            }

            if (my_dict.isEmpty()) {  // Ko có gì thay doi
                Notification noti = new Notification("Warning", "Không có gì thay đổi", "warning");
                request.setAttribute("notify", noti);
                ss.setAttribute("userLogin", dao.get(account.getAccountId()));
                RequestDispatcher r1 = request.getRequestDispatcher("accountDetail.jsp");
                r1.forward(request, response);
            } else {
                //update
                patientDAO.update(patient, my_dict);
                Notification noti = new Notification("Success", "Cập nhật thành công", "success");
                request.setAttribute("notify", noti);
                ss.setAttribute("userLogin", dao.get(account.getAccountId()));
                RequestDispatcher r1 = request.getRequestDispatcher("login.jsp");
                r1.forward(request, response);
            }
        } else {
            Notification noti = new Notification("Error", "Nhập sai số điện thoại", "error");
            request.setAttribute("notify", noti);
            RequestDispatcher r1 = request.getRequestDispatcher("accountDetail.jsp");
            r1.forward(request, response);
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
