/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
 * @author hoang
 */
public class NurseDAO implements DAO<Nurse> {

    Connection conn = DBcontext.getConnection();

    private final String SQL_INSERT = "INSERT INTO [dbo].[nurse]\n"
            + "           ([name_nurse]\n"
            + "           ,[phone]\n"
            + "           ,[fullname]\n"
            + "           ,[id_account]\n"
            + "           ,[address]\n"
            + "           ,[id_area])\n"
            + "     VALUES\n"
            + "           (?,?,?,?,?,?)";

    @Override
    public List<Nurse> parse(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Nurse get(int account_id) {
        try {

            String sql = "SELECT * FROM dbo.nurse\n"
                    + "WHERE id_account = ?";
            PreparedStatement sttm = conn.prepareStatement(sql);
            sttm.setInt(1, account_id);
            ResultSet rs = sttm.executeQuery();
            Nurse nurse = new Nurse();
            while (rs.next()) {
                nurse.setId_nurse(rs.getInt("id_nurse"));
                nurse.setName_nurse(rs.getString("name_nurse"));
                nurse.setPhone(rs.getInt("phone"));
                nurse.setFullName(rs.getString("fullname"));
                nurse.setId_account(account_id);
                nurse.setAddress(rs.getString("address"));
                nurse.setId_area(rs.getInt("id_area"));
            }
            return nurse;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Nurse getByID(int id_nurse) {

        try {
            String sql = "SELECT * FROM dbo.nurse\n"
                    + "WHERE id_nurse = ?";
            PreparedStatement sttm = conn.prepareStatement(sql);
            sttm.setInt(1, id_nurse);
            ResultSet rs = sttm.executeQuery();
            Nurse nurse = new Nurse();
            while (rs.next()) {
                nurse.setId_nurse(rs.getInt("id_nurse"));
                nurse.setName_nurse(rs.getString("name_nurse"));
                nurse.setPhone(rs.getInt("phone"));
                nurse.setFullName(rs.getString("fullname"));
                nurse.setId_account(rs.getInt("id_account"));
                nurse.setAddress(rs.getString("address"));
                nurse.setId_area(rs.getInt("id_area"));
            }
            return nurse;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Nurse> getAllNurse(int pageIndex, int pageSize) {
        ArrayList<Nurse> lst = new ArrayList<>();
        try {
            String query = "SELECT * FROM nurse ORDER BY id_nurse "
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
                String fullName = rs.getString(7);
                int idAccount = rs.getInt(4);
                String address = rs.getString(5);
                int idArea = rs.getInt(6);
                lst.add(new Nurse(nID, nName, phone, fullName, idAccount, address, idArea));
            }
            return lst;

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countPage(int pageSize) {
        try {
            String query = "select Count(*) from nurse";

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
    public List<Nurse> getAll() {

        return null;
    }

    @Override
    public void create(Nurse t) {
        try (
                PreparedStatement prep = conn.prepareStatement(SQL_INSERT)) {
            prep.setString(1, t.getName_nurse());
            prep.setInt(2, t.getPhone());
            prep.setString(3, t.getFullName());
            prep.setInt(4, t.getId_account());
            prep.setString(5, t.getAddress());
            prep.setInt(6, t.getId_area());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Nurse t, Hashtable<String, String> my_dict) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Nurse t) {
        try {
            String sql = "delete from nurse where id_nurse = " + t.getId_nurse();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

}
