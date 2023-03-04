/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ReportDAO implements DAO<Report> {

    PatientDAO patient = new PatientDAO();
    Connection conn = DBcontext.getConnection();

    @Override
    public List<Report> parse(String sql) {
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Report> qq = new ArrayList<>();
            while (rs.next()) {
                Report re = new Report();
                re.setId_report(rs.getInt("id_report"));
                re.setContent(rs.getString("content"));
                re.setCreateDate(rs.getTimestamp("create_date"));
//                re.setPatient(patient.get(rs.getInt("id_patient")));
                qq.add(re);
            }
            return qq;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return null;
    }

    @Override
    public Report get(int id) {
        String sql = "select * from report where id_report = " + id;
        List<Report> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    @Override
    public List<Report> getAll() {
        String sql = "select * from report order by create_date desc";
        List<Report> qq = new ArrayList<>();
        qq = parse(sql);
        return qq;
    }

    public List<Report> getAllById(int patientId) {
        String sql = "select * from report where id_patient = " + patientId + " order by create_date desc";
        List<Report> qq = new ArrayList<>();
        qq = parse(sql);
        return qq;
    }

    @Override
    public void create(Report t) {
        String SQL_INSERT = "INSERT INTO [dbo].[report]\n"
                + "           ([content]\n"
                + "           ,[create_date]\n"
                + "           ,[id_patient])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try (
                PreparedStatement prep = conn.prepareStatement(SQL_INSERT)) {
            prep.setString(1, t.getContent());
            prep.setTimestamp(2, t.getCreateDate());
            prep.setInt(3, t.getPatient().getPatientId());
            prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, SQL_INSERT, ex);
        }
    }

    @Override
    public void update(Report t, Hashtable<String, String> my_dict) {
        String sql_update = "Update report set ";
        if (!my_dict.isEmpty()) {
            try (
                    PreparedStatement prep = conn.prepareStatement(sql_update);) {
                String change = "";
                for (String key : my_dict.keySet()) {
                    String value = my_dict.get(key);
                    try {
                        int values = Integer.parseInt(value);
                        change += key + " = " + values + ",";
                    } catch (NumberFormatException e) {
                        change += key + " = '" + value + "',";
                    }
                }
                change = change.substring(0, change.length() - 1);
                sql_update += change + " where id_report = " + t.getId_report();
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql_update);
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, sql_update, ex);
            }
        }
    }

    @Override
    public void delete(Report t) {
        String sql = "delete from report where id_report = " + t.getId_report();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
    }

    public List getIndex(int index1, int index2, int id) {
        String sql = "SELECT * FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY id_report) AS RowNum\n"
                + "    FROM [dbo].[report] \n"
                + ") AS MyDerivedTable\n"
                + "WHERE MyDerivedTable.RowNum BETWEEN " + index1 + " AND " + index2;
        if (id > 0) {
            sql = "SELECT * FROM (\n"
                    + "    SELECT *, ROW_NUMBER() OVER (ORDER BY id_report) AS RowNum\n"
                    + "    FROM [dbo].[report] WHERE id_patient = " + id + "\n"
                    + ") AS MyDerivedTable\n"
                    + "WHERE MyDerivedTable.RowNum BETWEEN " + index1 + " AND " + index2;
        }
        List<Report> report = new ArrayList<>();
        report = parse(sql);
        return report;
    }

    public int getNoOfRecord(int id) {
        String sql = "SELECT COUNT(*) AS Num FROM [dbo].[report]";
        if (id > 0) {
            sql += " WHERE id_patient = " + id;
        }
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("Num");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return 0;
    }
    
    public int updateReport(int id, String content) {
        int res = 0;
        String sql = "UPDATE [dbo].[report] SET "
                + "content = ? "
                + "WHERE id_report = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, content);
            pre.setInt(2, id);
            res = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return res;
    }

    public List<Report> getAllByPatientId(Integer patientId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
