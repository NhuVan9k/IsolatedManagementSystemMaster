/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Area;
import entity.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class AreaDAO implements DAO<Area> {

    Connection conn = DBcontext.getConnection();
     
    @Override
    public List<Area> parse(String sql) {
         try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Area> qq = new ArrayList<>();
            while (rs.next()) {
                Area p = new Area();
                p.setAreaId(rs.getInt("area_id"));
                p.setAreaName(rs.getString("area_name"));
                p.setAreaAddress(rs.getString("area_address"));
                p.setContact(rs.getString("contact"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Area get(int id) {
        String sql = "select * from area where area_id = " + id;
        List<Area> qq = new ArrayList<>();
        qq = parse(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }

    @Override
    public List<Area> getAll() {
        return null;
    }

    @Override
    public void create(Area t) {
        
    }

    @Override
    public void delete(Area t) {
        
    }

    @Override
    public void update(Area t, Hashtable<String, String> my_dict) {
        
    }

}

