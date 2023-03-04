/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import entity.Nurse;
import entity.TypeAccount;
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
import utils.Utils;

public class AccountDAO implements DAO<Account> {

    private final String SQL_INSERT = "INSERT INTO dbo.account\n"
            + "(\n"
            + "    id_type,\n"
            + "    username,\n"
            + "    password,\n"
            + "    avatar,\n"
            + "    email\n"
            + ")\n"
            + "VALUES\n"
            + "(?,?,?,?,?)";

    private final Connection conn = DBcontext.getConnection();
    private final TypeDAO accountTypeDAO = new TypeDAO();
    private final PatientDAO patientDAO = new PatientDAO();
    private final AreaDAO areaDAO = new AreaDAO();
    private final Utils utils = new Utils();
    public final String NEW_PASSWORD = utils.randomPassword(10);
    private final String PASSWORD_HASH = utils.md5(NEW_PASSWORD);

    @Override
    public List<Account> parse(String sql) {
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Account> qq = new ArrayList<>();
            while (rs.next()) {
                Account p = new Account();
                p.setAccountId(rs.getInt("account_id"));
                p.setUserName(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                int accountTypeId = rs.getInt("id_type");
                p.setAvatar(rs.getString("avatar"));
                p.setEmail(rs.getString("email"));
                p.setType(accountTypeDAO.get(accountTypeId));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Account get(int id) {
        String sql = "Select * from account where account_id = " + id;
        List<Account> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    public Account find(String username) {
        String sql = "select * from account where username = '" + username + "'";
        List<Account> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    /**
     * Find account by username and password
     *
     * @param username
     * @param password
     * @return Account
     */
    public Account findByUsernamePassword(String username, String password) {
        String sql = "select * from account where username = '" + username + "' and password = '" + password + "';";
        List<Account> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    /**
     * Find Account by username
     *
     * @param username
     * @param email
     * @return Account
     */
    public Account findByUsernameEmail(String username, String email) {
        String sql = "select * from account where username = '" + username + "' and email ='" + email + "'";;
        List<Account> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    /**
     * Find Account by patient_id
     *
     * @param patientId
     * @return Account
     */
    public Account findAccountByPatientId(int patientId) {
        String sql = "select * from account where patient_id = " + patientId;
        List<Account> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    /**
     * Get Nurse by account_id
     *
     * @param account_id
     * @return Nurse
     */
    @Override
    public void create(Account t) {
        try (
                PreparedStatement prep = conn.prepareStatement(SQL_INSERT)) {
            prep.setInt(1, t.getType().getAccountTypeId());
            prep.setString(2, t.getUserName());
            prep.setString(3, t.getPassword());
            prep.setString(4, t.getAvatar());
            prep.setString(5, t.getEmail());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account t, Hashtable<String, String> my_dict) {

    }

    public void updateForgot(Account t) {
        try {
            String sql = "UPDATE dbo.account SET password = '" + PASSWORD_HASH + "' WHERE username = '" + t.getUserName() + "'";
            PreparedStatement pt = conn.prepareStatement(sql);
            pt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Account t) {
        try {
            String sql = "delete from account where account_id = " + t.getAccountId();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        String password = new Utils().md5("123456");
        System.out.println("Hello");
    }
}
