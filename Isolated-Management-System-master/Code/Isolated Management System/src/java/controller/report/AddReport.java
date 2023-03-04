/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.report;

import dao.ReportDAO;
import entity.Account;
import entity.Report;
import java.io.IOException;
import java.sql.Timestamp;
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
public class AddReport extends HttpServlet {

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
        String content = "";
        for (int i = 1; i < 10; i++) {
            String x = request.getParameter("s"+i);
            if (x!=null){
                content+=x+"; ";
            }
        }
        String x = request.getParameter("cother");
        if (x!=null){
            if(request.getParameter("other")!=null){
                content+=request.getParameter("other")+"; ";
            }
        }
        if ("".equals(content.trim())){
            content="Không có triệu trứng bệnh; ";
        }
        content+=request.getParameter("test");
        long millis = System.currentTimeMillis();
        Timestamp date = new Timestamp(millis);
        Report report = new Report();
        report.setContent(content);
        report.setCreateDate(date);
        
        HttpSession session = request.getSession();
        Account userLogin = (Account) session.getAttribute("userLogin");
        report.setPatient(userLogin.getPatient());
        ReportDAO dao = new ReportDAO();
        dao.create(report);
        Notification noti = new Notification("Success", "Thêm báo cáo thành công.", "success");
        request.setAttribute("notify", noti);
        RequestDispatcher add = request.getRequestDispatcher("list");
        add.forward(request, response);
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
