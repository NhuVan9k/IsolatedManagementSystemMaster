/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Doctor;
import entity.Nurse;
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
public class DoctorDAO implements DAO<Doctor>{

    private final String SQL_INSERT = "INSERT INTO [dbo].[doctor]\n" +
"           ([doctor_name]\n" +
"           ,[phone]\n" +
"           ,[fullname]\n" +
"           ,[id_account]\n" +
"           ,[address])\n" +
"     VALUES\n" +
"           (?,?,?,?,?)";
     Connection conn = DBcontext.getConnection();
     
      public List<Doctor> getAllDoctor(int pageIndex, int pageSize) {
        ArrayList<Doctor> lst = new ArrayList<>();
        try {
            String query = "SELECT * FROM doctor ORDER BY id_doctor "
                    + "OFFSET (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int nID = rs.getInt(1);
                String nName = rs.getString(2);
                int phone = rs.getInt(3);
                String fullName = rs.getString(4);
                int idAccount = rs.getInt(5);
                String address = rs.getString(6);
                
                lst.add(new Doctor(nID, nName, phone, fullName, idAccount, address));
            }
            return lst;

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public int countPage(int pageSize) {
        try {
            String query = "select Count(*) from doctor";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            int numOfPage = count / pageSize;
            if (count % pageSize != 0) {
                numOfPage++;
            }
            return numOfPage;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return 0;
    }
    @Override
    public List<Doctor> parse(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Doctor get(int id) {
        try {
            String sql = "SELECT * FROM dbo.doctor\n"
                + "WHERE id_doctor = ?" ;
            PreparedStatement sttm = conn.prepareStatement(sql);
            sttm.setInt(1,id);
            ResultSet rs = sttm.executeQuery();
            
            Doctor doctor = new Doctor();
            while (rs.next()) {
                doctor.setDoctor(rs.getInt("id_doctor"));
                doctor.setDoctorName(rs.getString("doctor_name"));
                doctor.setPhone(rs.getInt("phone"));
                doctor.setFullName(rs.getString("fullname"));
                doctor.setIdAccount(rs.getInt("id_account"));
                doctor.setAddress(rs.getString("address"));
            }
            return doctor;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Doctor t) {
        try (
            PreparedStatement prep = conn.prepareStatement(SQL_INSERT)) {
            prep.setString(1, t.getDoctorName());
            prep.setInt(2, t.getPhone());
            prep.setString(3, t.getFullName());
            prep.setInt(4, t.getIdAccount());
            prep.setString(5, t.getAddress());
            
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Doctor t, Hashtable<String, String> my_dict) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Doctor t) {
        try {
            String sql = "delete from doctor where id_doctor = " + t.getDoctor();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }
    
}
