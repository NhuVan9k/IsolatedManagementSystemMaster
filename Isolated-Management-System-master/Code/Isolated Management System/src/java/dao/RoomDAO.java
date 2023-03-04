/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Room;
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

public class RoomDAO implements DAO<Room> {

    Connection conn = DBcontext.getConnection();

    AreaDAO area = new AreaDAO();

    @Override
    public List<Room> parse(String sql) {
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Room> room = new ArrayList<>();
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt("room_id"));
                r.setRoomName(rs.getString("room_name"));
                r.setBedNumber(rs.getInt("bed_number"));
                r.setArea(area.get(rs.getInt("area_id")));
                r.setNote(rs.getString("note"));
                room.add(r);
            }
            return room;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return null;
    }

    @Override
    public Room get(int id) {
        String sql = "SELECT * FROM [dbo].[room] WHERE room_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt("room_id"));
                r.setRoomName(rs.getString("room_name"));
                r.setBedNumber(rs.getInt("bed_number"));
                r.setArea(area.get(rs.getInt("area_id")));
                r.setNote(rs.getString("note"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return null;
    }

    @Override
    public List getAll() {
        String sql = "SELECT * FROM [dbo].[room]";
        List<Room> room = new ArrayList<>();
        room = parse(sql);
        return room;
    }

    @Override
    public void create(Room r) {
        String sql = "INSERT INTO [dbo].[room]"
                + " ([room_name],[bed_number],[note],[area_id]) VALUES (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, r.getRoomName());
            pre.setInt(2, r.getBedNumber());
            pre.setString(3, r.getNote());
            pre.setInt(4, r.getArea().getAreaId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
    }

    @Override
    public void update(Room t, Hashtable<String, String> my_dict) {

    }

    @Override
    public void delete(Room r) {
        String sql = "DELETE FROM [dbo].[room] WHERE room_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, r.getRoomId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
    }

    public void updateRoom(Room r) {
        String sql = "UPDATE [dbo].[room] SET "
                + "room_name = ?, bed_number = ?, note = ?, area_id = ? "
                + "WHERE room_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, r.getRoomName());
            pre.setInt(2, r.getBedNumber());
            pre.setString(3, r.getNote());
            pre.setInt(4, r.getArea().getAreaId());
            pre.setInt(5, r.getRoomId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
    }

    public List getIndex(int index1, int index2, int areaId) {
        String sql = "SELECT * FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY room_id) AS RowNum\n"
                + "    FROM [dbo].[room] where area_id = " + areaId + "\n"
                + ") AS MyDerivedTable\n"
                + "WHERE MyDerivedTable.RowNum BETWEEN " + index1 + " AND " + index2;
        List<Room> room = new ArrayList<>();
        room = parse(sql);
        return room;
    }

    public int getNoOfRecord(int areaId) {
        String sql = "SELECT COUNT(*) AS Num FROM [dbo].[room] where area_id = " + areaId;
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("Num");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return 0;
    }

    public List<Room> getAllInArea(int area_id) {
        String sql = "select * from Room where area_id = " + area_id;
        List<Room> qq = new ArrayList<>();
        qq = parse(sql);
        return qq;
    }

    public Room getRoomByName(String name) {
        String sql = "SELECT * FROM [dbo].[room] WHERE room_name = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt("room_id"));
                r.setRoomName(rs.getString("room_name"));
                r.setBedNumber(rs.getInt("bed_number"));
                r.setArea(area.get(rs.getInt("area_id")));
                r.setNote(rs.getString("note"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return null;
    }
    
    /**
     * GET TOTAL BED IN THE SYSTEM
     * 
     * @return total bed
     */
    public int getAllBed() {
        String sql = "SELECT SUM(bed_number) as Num FROM dbo.room" ;
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("Num");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, sql, ex);
        }
        return 0;
    }
}
