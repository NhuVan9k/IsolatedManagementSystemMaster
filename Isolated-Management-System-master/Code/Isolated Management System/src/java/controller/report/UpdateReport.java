/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.report;

import dao.ReportDAO;
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
public class UpdateReport extends HttpServlet {

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
        ReportDAO dao = new ReportDAO();
        if (request.getParameter("reportId") == null) {
            RequestDispatcher update = request.getRequestDispatcher("list");
            update.forward(request, response);
        }
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        request.setAttribute("report", dao.get(reportId));
        RequestDispatcher update = request.getRequestDispatcher("update.jsp");
        update.forward(request, response);
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
        ReportDAO dao = new ReportDAO();
        if (request.getParameter("reportId") == null) {
            RequestDispatcher update = request.getRequestDispatcher("list");
            update.forward(request, response);
        }
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        // get content
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
        // update
        if (dao.updateReport(reportId, content) > 0) {
            Notification noti = new Notification("Success", "Cập nhật report thành công.", "success");
            request.setAttribute("notify", noti);
        }
        RequestDispatcher update = request.getRequestDispatcher("list");
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
