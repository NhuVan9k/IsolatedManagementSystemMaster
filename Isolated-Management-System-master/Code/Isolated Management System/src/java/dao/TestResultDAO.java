/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Patient;
import entity.TestResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author hoang
 */
public class TestResultDAO implements DAO<TestResult> {

     private final String SQL_INSERT = " Insert into test_result"
            + "(patient_id, test_name, result, create_date) values " + "(?, ?, ?, ?)";
    private final String SQL_UPDATE = "update test_result set test_result_id = ?, "+
            " test_name = ?, result = ?, create_date = ? "+
            "where test_result_id = ?";
        
    Connection conn = DBcontext.getConnection();
    PatientDAO patientDAO = new PatientDAO();
    
    @Override
    public List<TestResult> parse(String sql) {
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<TestResult> qq = new ArrayList<>();
            while (rs.next()) {
                TestResult p = new TestResult();
                p.setTestName(rs.getString("test_name"));
                p.setResult(rs.getString("result"));
                p.setCreateDate(rs.getTimestamp("create_date"));
                int patientId = rs.getInt("patient_id");
                p.setPatient(patientDAO.get(patientId));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public TestResult get(int id) {
        String sql = "select * from test_result where test_result_id = " + id;
        List<TestResult> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }
    
    public List<TestResult> findTestResultByPatientId(int id) {
        String sql = "select * from test_result where patient_id = " + id;
        List<TestResult> qq = new ArrayList<>();
        qq = parse(sql);
        return qq;
    }

    @Override
    public List<TestResult> getAll() {
        String sql = "select * from test_result";
        List<TestResult> qq = new ArrayList<>();
        qq = parse(sql);
        return qq;
    }

    @Override
    public void create(TestResult t) {
        try (
            PreparedStatement prep = conn.prepareStatement(SQL_INSERT)) {
            prep.setInt(1, t.getPatient().getPatientId());
            prep.setString(2, t.getTestName());
            prep.setString(3, t.getResult());
            prep.setTimestamp(4, (java.sql.Timestamp) t.getCreateDate());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TestResult t) {
        try {
            String sql = "delete from test_result where test_result_id = "+t.getTestResultId();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    @Override
    public void update(TestResult t, Hashtable<String, String> my_dict) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
        TestResultDAO testResultDAO = new TestResultDAO();
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.get(20);
        testResultDAO.create(new TestResult("Debug", "Die", null, patient));
    }
}
