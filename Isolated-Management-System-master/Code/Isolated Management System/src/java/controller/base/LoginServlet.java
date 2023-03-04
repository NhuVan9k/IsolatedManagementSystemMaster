/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.base;

import dao.AccountDAO;
import dao.NurseDAO;
import dao.PatientDAO;
import entity.Account;
import entity.Nurse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Notification;
import static utils.Utils.md5;

public class LoginServlet extends HttpServlet {

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
        HttpSession ss = request.getSession();
        AccountDAO accountDAO = new AccountDAO();
        NurseDAO nurseDAO = new NurseDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // encrypt password to md5
        String passwordMd5 = md5(password);

        // find account match the given username and password
        Account account = accountDAO.findByUsernamePassword(username, passwordMd5);

        if (account == null) {
            Notification noti = new Notification("Error", "Tài khoản không đúng. Vui lòng kiểm tra lại.", "error");
            request.setAttribute("notify", noti);
            RequestDispatcher rt = request.getRequestDispatcher("login.jsp");

            rt.forward(request, response);
        } else {
            // if patient login
            PatientDAO patientDAO = new PatientDAO();
            account.setPatient(patientDAO.getPatientByAccountId(account.getAccountId()));
            ss.setAttribute("userLogin", account);
            
            // if nurse login
            if (account.getType().getAccountTypeId() == 2) {
                Nurse nurse = nurseDAO.get(account.getAccountId());
                ss.setAttribute("nurse", nurse);
                RequestDispatcher rt = request.getRequestDispatcher("home");
                rt.forward(request, response);
            } else {
                RequestDispatcher rt = request.getRequestDispatcher("home");
                rt.forward(request, response);
            }
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
