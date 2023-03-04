/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TypeAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class TypeDAO implements DAO<TypeAccount> {
    
    Connection conn = DBcontext.getConnection();

    @Override
    public List<TypeAccount> parse(String sql) {
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<TypeAccount> qq = new ArrayList<>();
            while (rs.next()) {
                TypeAccount p = new TypeAccount();
                p.setAccountTypeId(rs.getInt("account_type_id"));
                p.setAccountTypeName(rs.getString("type_name"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public TypeAccount get(int id) {
        String sql = "select * from account_type where account_type_id = " + id;
        List<TypeAccount> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    @Override
    public List<TypeAccount> getAll() {
        return null;
    }

    @Override
    public void create(TypeAccount t) {
        
    }

   
    @Override
    public void delete(TypeAccount t) {
        
    }

    @Override
    public void update(TypeAccount t, Hashtable<String, String> my_dict) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  

}
